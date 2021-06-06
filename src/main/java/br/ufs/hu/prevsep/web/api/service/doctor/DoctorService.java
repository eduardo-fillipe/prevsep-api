package br.ufs.hu.prevsep.web.api.service.doctor;

import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    List<DoctorResponseDTO> getMedics();

    Optional<DoctorResponseFullDTO> getMedicByCRM(Integer CRM);

    Optional<DoctorResponseDTO> getMedic(String cpf) throws UserNotFoundException;

    DoctorResponseDTO createMedic(DoctorRequestDTO medico) throws CPFAlreadyRegistered;

    DoctorResponseDTO updateMedico(String cpf, DoctorUpdateDTO medico) throws UserNotFoundException;
}
