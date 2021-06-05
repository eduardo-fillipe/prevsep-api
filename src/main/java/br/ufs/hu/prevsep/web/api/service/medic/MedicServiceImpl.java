package br.ufs.hu.prevsep.web.api.service.medic;

import br.ufs.hu.prevsep.web.api.dto.medic.MedicoRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.*;
import br.ufs.hu.prevsep.web.api.dto.mapper.UsuarioMapper;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.MedicoEntity;
import br.ufs.hu.prevsep.web.api.repository.usuario.MedicoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicServiceImpl implements MedicService {

    private final MedicoRepository medicoRepository;
    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public MedicServiceImpl(MedicoRepository medicoRepository, PasswordEncoder passwordEncoder) {
        this.medicoRepository = medicoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<MedicoResponseDTO> getMedics() {
        List<MedicoResponseDTO> medicoResponseDTOS = new ArrayList<>();

        medicoRepository.findAll().forEach(medicoEntity -> {
                if (!(StatusUsuarioEnum.fromId(medicoEntity.getUserInfo().getStatus()) == StatusUsuarioEnum.DESATIVADO)
                        && !(medicoEntity.getUserInfo().getStatus() == null))
                    medicoResponseDTOS.add(usuarioMapper.mapToMedicoResponseDto(medicoEntity));
        });

        return medicoResponseDTOS;
    }


    @Override
    public Optional<MedicoResponseDTO> getMedic(String cpf) throws UserNotFoundException{
        if (cpf == null)
            return  Optional.empty();

        Optional<MedicoEntity> medico = medicoRepository.findById(cpf);

        if (medico.isPresent()
                && StatusUsuarioEnum.fromId(medico.get().getUserInfo().getStatus()) != StatusUsuarioEnum.DESATIVADO) {
            return Optional.ofNullable(usuarioMapper.mapToMedicoResponseDto(medico.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<MedicoResponseFullDTO> getMedicByCRM(Integer crm) throws UserNotFoundException{
        if (crm == null)
            return  Optional.empty();

        Optional<MedicoEntity> medico = Optional.ofNullable(medicoRepository.findFirstByCrm(crm));

        if (medico.isPresent()
                && StatusUsuarioEnum.fromId(medico.get().getUserInfo().getStatus()) != StatusUsuarioEnum.DESATIVADO) {
            return Optional.ofNullable(usuarioMapper.mapToMedicoResponseFullDto(medico.get()));
        }

        return Optional.empty();
    }

    @Override
    public MedicoResponseDTO createMedic(MedicoRequestDTO medico) throws CPFAlreadyRegistered {
        if (getMedic(medico.getUserInfo().getCpf()).isPresent())
            throw new CPFAlreadyRegistered().withDetailedMessage("An user is already registered under this CPF.");

        MedicoEntity medicoEntity = usuarioMapper.mapToMedicoEntity(medico);

        medicoEntity.getUserInfo().setSenha(passwordEncoder.encode(medicoEntity.getUserInfo().getSenha()));

        MedicoEntity savedEntity = medicoRepository.save(medicoEntity);

        return usuarioMapper.mapToMedicoResponseDto(savedEntity);
    }

    @Override
    public MedicoResponseDTO updateMedico(String cpf, MedicoUpdateDTO medico) {
        MedicoResponseDTO medicoResponseDTO = getMedic(cpf).orElseThrow(() -> new UserNotFoundException().withDetailedMessage("Medic not found."));

        MedicoEntity medicoEntity = usuarioMapper.mapToMedicoEntity(medico);
        medicoEntity.setCpf(cpf);

        return usuarioMapper.mapToMedicoResponseDto(medicoRepository.save(medicoEntity));
    }
}
