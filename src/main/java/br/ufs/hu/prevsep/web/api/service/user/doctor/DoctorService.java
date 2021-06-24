package br.ufs.hu.prevsep.web.api.service.user.doctor;

import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    List<DoctorResponseDTO> getMedics();

    Optional<DoctorResponseFullDTO> getMedicByCRM(Integer CRM);

    Optional<DoctorResponseFullDTO> getMedic(String cpf) throws UserNotFoundException;

    DoctorResponseDTO createMedic(DoctorRequestDTO medico) throws CPFAlreadyRegistered;

    DoctorResponseDTO updateMedico(String cpf, DoctorUpdateDTO medico) throws UserNotFoundException;
}
