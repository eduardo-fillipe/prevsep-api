package br.ufs.hu.prevsep.web.api.service.nurse;

import br.ufs.hu.prevsep.web.api.dto.nurse.NurseDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface NurseService {

    List<NurseDTO> getNurses();

    Optional<NurseFullDTO> getNurseByCRE(Integer CRE);

    Optional<NurseDTO> getNurse(String cpf) throws UserNotFoundException;

    NurseDTO createNurse(NurseRequestDTO nurse) throws CPFAlreadyRegistered;

    NurseDTO updateNurse(String cpf, NurseUpdateDTO medico) throws UserNotFoundException;
}
