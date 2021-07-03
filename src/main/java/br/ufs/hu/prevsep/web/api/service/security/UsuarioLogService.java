package br.ufs.hu.prevsep.web.api.service.security;

import br.ufs.hu.prevsep.web.api.dto.security.UsuarioLoginLogCreateDTO;
import br.ufs.hu.prevsep.web.api.dto.security.UsuarioLoginLogDTO;

public interface UsuarioLogService {

    /**
     * Creates a login log in the system
     * @param loginLogCreateDTO The log to be created
     * @return The resulting log after inserted in the system
     */
    UsuarioLoginLogDTO createLog(UsuarioLoginLogCreateDTO loginLogCreateDTO);
}
