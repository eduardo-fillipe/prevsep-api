package br.ufs.hu.prevsep.web.api.service.doctor;

import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.mapper.UsuarioMapper;
import br.ufs.hu.prevsep.web.api.dto.usuario.CargoEnum;
import br.ufs.hu.prevsep.web.api.dto.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.MedicoEntity;
import br.ufs.hu.prevsep.web.api.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public DoctorServiceImpl(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<DoctorResponseDTO> getMedics() {
        List<DoctorResponseDTO> doctorResponseDTOS = new ArrayList<>();

        doctorRepository.findAll().forEach(medicoEntity -> {
                if (!(StatusUsuarioEnum.fromId(medicoEntity.getUserInfo().getStatus()) == StatusUsuarioEnum.DESATIVADO)
                        && !(medicoEntity.getUserInfo().getStatus() == null))
                    doctorResponseDTOS.add(usuarioMapper.mapToDoctorResponseDto(medicoEntity));
        });

        return doctorResponseDTOS;
    }


    @Override
    public Optional<DoctorResponseDTO> getMedic(String cpf) throws UserNotFoundException{
        if (cpf == null)
            return  Optional.empty();

        Optional<MedicoEntity> medico = doctorRepository.findById(cpf);

        if (medico.isPresent()
                && StatusUsuarioEnum.fromId(medico.get().getUserInfo().getStatus()) != StatusUsuarioEnum.DESATIVADO) {
            return Optional.ofNullable(usuarioMapper.mapToDoctorResponseDto(medico.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<DoctorResponseFullDTO> getMedicByCRM(Integer crm) throws UserNotFoundException{
        if (crm == null)
            return  Optional.empty();

        Optional<MedicoEntity> medico = Optional.ofNullable(doctorRepository.findFirstByCrm(crm));

        if (medico.isPresent()
                && StatusUsuarioEnum.fromId(medico.get().getUserInfo().getStatus()) != StatusUsuarioEnum.DESATIVADO) {
            return Optional.ofNullable(usuarioMapper.mapToDoctorResponseFullDto(medico.get()));
        }

        return Optional.empty();
    }

    @Override
    public DoctorResponseDTO createMedic(DoctorRequestDTO medico) throws CPFAlreadyRegistered {
        if (getMedic(medico.getUserInfo().getCpf()).isPresent())
            throw new CPFAlreadyRegistered().withDetailedMessage("An user is already registered under this CPF.");

        MedicoEntity medicoEntity = usuarioMapper.mapToDoctorEntity(medico);

        medicoEntity.getUserInfo().setSenha(passwordEncoder.encode(medicoEntity.getUserInfo().getSenha()));
        medicoEntity.getUserInfo().setCargo(CargoEnum.MEDICO.getId());

        MedicoEntity savedEntity = doctorRepository.save(medicoEntity);

        return usuarioMapper.mapToDoctorResponseDto(savedEntity);
    }

    @Override
    public DoctorResponseDTO updateMedico(String cpf, DoctorUpdateDTO medico) {
        DoctorResponseDTO doctorResponseDTO = getMedic(cpf).orElseThrow(() -> new UserNotFoundException().withDetailedMessage("Medic not found."));

        MedicoEntity medicoEntity = usuarioMapper.mapToDoctorEntity(medico);
        medicoEntity.setCpf(cpf);

        return usuarioMapper.mapToDoctorResponseDto(doctorRepository.save(medicoEntity));
    }
}
