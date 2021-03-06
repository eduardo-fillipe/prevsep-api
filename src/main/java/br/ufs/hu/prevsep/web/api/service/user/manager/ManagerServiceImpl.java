package br.ufs.hu.prevsep.web.api.service.user.manager;

import br.ufs.hu.prevsep.web.api.dto.user.UsuarioMapper;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.CargoEnum;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.ManagerEntity;
import br.ufs.hu.prevsep.web.api.repository.ManagerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public ManagerServiceImpl(ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ManagerDTO> getManagers() {
        List<ManagerDTO> managerDTOS = new ArrayList<>();

        managerRepository.findAll().forEach(managerEntity -> {
                if (!(StatusUsuarioEnum.fromId(managerEntity.getUserInfo().getStatus()) == StatusUsuarioEnum.DESATIVADO)
                        && !(managerEntity.getUserInfo().getStatus() == null))
                    managerDTOS.add(usuarioMapper.mapToManagerDto(managerEntity));
        });

        return managerDTOS;
    }


    @Override
    public Optional<ManagerFullDTO> getManager(String cpf) throws UserNotFoundException {
        if (cpf == null)
            return  Optional.empty();

        Optional<ManagerEntity> managerEntity = managerRepository.findById(cpf);

        if (managerEntity.isPresent()
                && StatusUsuarioEnum.fromId(managerEntity.get().getUserInfo().getStatus()) != StatusUsuarioEnum.DESATIVADO) {
            return Optional.ofNullable(usuarioMapper.mapToManagerFullDto(managerEntity.get()));
        }

        return Optional.empty();
    }

    @Override
    public ManagerDTO createManager(ManagerRequestDTO managerRequestDTO) throws CPFAlreadyRegistered {
        if (getManager(managerRequestDTO.getUserInfo().getCpf()).isPresent())
            throw new CPFAlreadyRegistered().withDetailedMessage("Um usu??rio j?? est?? registrado com esse CPF.");

        ManagerEntity managerEntity = usuarioMapper.mapToManagerEntity(managerRequestDTO);
        managerEntity.getUserInfo().setCargo(CargoEnum.GESTOR.getId());

        //Encodes password before saving
        managerEntity.getUserInfo().setSenha(passwordEncoder.encode(managerEntity.getUserInfo().getSenha()));

        ManagerEntity savedEntity = managerRepository.save(managerEntity);

        return usuarioMapper.mapToManagerDto(savedEntity);

    }

    @Override
    public ManagerDTO updateManager(String cpf, ManagerUpdateDTO managerUpdateDTO) {
        ManagerFullDTO medicoResponseDTO = getManager(cpf).orElseThrow(() -> new UserNotFoundException().withDetailedMessage("Medic not found."));

        ManagerEntity managerEntity = usuarioMapper.mapToManagerEntity(managerUpdateDTO);

        managerEntity.setCpf(cpf);

        return usuarioMapper.mapToManagerDto(managerRepository.save(managerEntity));
    }
}
