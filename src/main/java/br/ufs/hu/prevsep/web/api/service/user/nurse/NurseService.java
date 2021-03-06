package br.ufs.hu.prevsep.web.api.service.user.nurse;

import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface NurseService {

    List<NurseDTO> getNurses();

    Optional<NurseFullDTO> getNurseByCRE(Integer CRE);

    Optional<NurseFullDTO> getNurse(String cpf) throws UserNotFoundException;

    NurseDTO createNurse(NurseRequestDTO nurse) throws CPFAlreadyRegistered;

    NurseDTO updateNurse(String cpf, NurseUpdateDTO medico) throws UserNotFoundException;
}
