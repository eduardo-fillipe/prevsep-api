package br.ufs.hu.prevsep.web.api.service.security;

import br.ufs.hu.prevsep.web.api.dto.security.*;
import net.sf.jasperreports.engine.JRException;
import java.sql.SQLException;

public interface UsuarioLogService {

    /**
     * Creates a login log in the system
     * @param loginLogCreateDTO The log to be created
     * @return The resulting log after inserted in the system
     */
    UsuarioLoginLogDTO createLog(UsuarioLoginLogCreateDTO loginLogCreateDTO);

    /**
     * Returns the logs in the system for a give request
     * @param pageable The request filter
     * @return The list of logs in the form of a page
     */
    PageUsuarioLoginLogDTO getLoginLogs(PageableUsuarioLoginLogDTO pageable);

    byte[] getLoginReport(LoginReportRequest request) throws JRException, SQLException;

    byte[] getEventAccessReport(UsuarioEventAccessRequest request) throws JRException, SQLException;

}
