package br.ufs.hu.prevsep.web.api.service.medic;

import br.ufs.hu.prevsep.web.api.dto.medic.MedicoRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MedicService {

    List<MedicoResponseDTO> getMedics();

    Optional<MedicoResponseFullDTO> getMedicByCRM(Integer CRM);

    Optional<MedicoResponseDTO> getMedic(String cpf) throws UserNotFoundException;

    MedicoResponseDTO createMedic(MedicoRequestDTO medico) throws CPFAlreadyRegistered;

    MedicoResponseDTO updateMedico(String cpf, MedicoUpdateDTO medico) throws UserNotFoundException;
}
