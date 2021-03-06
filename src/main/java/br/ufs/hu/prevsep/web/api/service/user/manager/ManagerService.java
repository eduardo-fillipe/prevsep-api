package br.ufs.hu.prevsep.web.api.service.user.manager;

import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ManagerService {

    List<ManagerDTO> getManagers();

    Optional<ManagerFullDTO> getManager(String cpf) throws UserNotFoundException;

    ManagerDTO createManager(ManagerRequestDTO manager) throws CPFAlreadyRegistered;

    ManagerDTO updateManager(String cpf, ManagerUpdateDTO manager) throws UserNotFoundException;
}
