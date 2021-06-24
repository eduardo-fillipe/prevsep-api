package br.ufs.hu.prevsep.web.api.service.user.nurse;

import br.ufs.hu.prevsep.web.api.dto.user.UsuarioMapper;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.CargoEnum;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.exception.CREAlreadyRegisteredException;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.EnfermeiroEntity;
import br.ufs.hu.prevsep.web.api.repository.EnfermeiroRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NurseServiceImpl implements NurseService {

    private final EnfermeiroRepository nurseRepository;
    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public NurseServiceImpl(EnfermeiroRepository nurseRepository, PasswordEncoder passwordEncoder) {
        this.nurseRepository = nurseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<NurseDTO> getNurses() {
        List<NurseDTO> nurseDTOS = new ArrayList<>();

        nurseRepository.findAll().forEach(entity -> {
                if (!(StatusUsuarioEnum.fromId(entity.getUserInfo().getStatus()) == StatusUsuarioEnum.DESATIVADO)
                        && !(entity.getUserInfo().getStatus() == null))
                    nurseDTOS.add(usuarioMapper.mapToNurseDto(entity));
        });

        return nurseDTOS;
    }


    @Override
    public Optional<NurseFullDTO> getNurse(String cpf) throws UserNotFoundException{
        if (cpf == null)
            return  Optional.empty();

        Optional<EnfermeiroEntity> nurse = nurseRepository.findById(cpf);

        if (nurse.isPresent()
                && StatusUsuarioEnum.fromId(nurse.get().getUserInfo().getStatus()) != StatusUsuarioEnum.DESATIVADO) {
            return Optional.ofNullable(usuarioMapper.mapToNurseFullDto(nurse.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<NurseFullDTO> getNurseByCRE(Integer cre) throws UserNotFoundException{
        if (cre == null)
            return  Optional.empty();

        Optional<EnfermeiroEntity> nurse = Optional.ofNullable(nurseRepository.findFirstByCre(cre));

        if (nurse.isPresent()
                && StatusUsuarioEnum.fromId(nurse.get().getUserInfo().getStatus()) != StatusUsuarioEnum.DESATIVADO) {
            return Optional.ofNullable(usuarioMapper.mapToNurseFullDto(nurse.get()));
        }

        return Optional.empty();
    }

    @Override
    public NurseDTO createNurse(NurseRequestDTO nurse) throws CPFAlreadyRegistered {
        if (getNurse(nurse.getUserInfo().getCpf()).isPresent())
            throw new CPFAlreadyRegistered().withDetailedMessage("An user is already registered under this CPF.");

        if (getNurseByCRE(nurse.getCre()).isPresent())
            throw new CREAlreadyRegisteredException()
                    .withDetailedMessage("A nurse with CRE '"+nurse.getCre()+"' already exists in the system's database.");

        EnfermeiroEntity enfermeiroEntity = usuarioMapper.mapToEnfermeiroEntity(nurse);
        enfermeiroEntity.getUserInfo().setCargo(CargoEnum.ENFERMEIRO.getId());
        enfermeiroEntity.getUserInfo().setSenha(passwordEncoder.encode(enfermeiroEntity.getUserInfo().getSenha()));

        EnfermeiroEntity savedEntity = nurseRepository.save(enfermeiroEntity);

        return usuarioMapper.mapToNurseDto(savedEntity);
    }

    @Override
    public NurseDTO updateNurse(String cpf, NurseUpdateDTO nurseUpdateDTO) {
        getNurse(cpf).orElseThrow(() -> new UserNotFoundException().withDetailedMessage("Nurse not found."));

        EnfermeiroEntity enfermeiroEntity = usuarioMapper.mapToEnfermeiroEntity(nurseUpdateDTO);
        enfermeiroEntity.setCpf(cpf);

        return usuarioMapper.mapToNurseDto(nurseRepository.save(enfermeiroEntity));
    }
}
