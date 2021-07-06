package br.ufs.hu.prevsep.web.api.service.security;

import br.ufs.hu.prevsep.web.api.dto.security.*;
import net.sf.jasperreports.engine.JRException;
import java.sql.SQLException;

public interface UsuarioLogService {

    /**
     * Cria um registro de login no sistema
     * @param loginLogCreateDTO O registro a ser criado
     * @return O registro resultante após a inserção no sistema
     */
    UsuarioLoginLogDTO createLog(UsuarioLoginLogCreateDTO loginLogCreateDTO);

    /**
     * Retorna o registro presente no sistema de uma dado solicitação
     * @param pageable O filtro de solicitação
     * @return A lista de registros na forma de uma página
     */
    PageUsuarioLoginLogDTO getLoginLogs(PageableUsuarioLoginLogDTO pageable);

    byte[] getLoginReport(LoginReportRequest request) throws JRException, SQLException;

    byte[] getEventAccessReport(UsuarioEventAccessRequest request) throws JRException, SQLException;

}
