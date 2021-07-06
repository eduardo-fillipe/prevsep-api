package br.ufs.hu.prevsep.web.api.service.security;

import br.ufs.hu.prevsep.web.api.dto.security.*;
import br.ufs.hu.prevsep.web.api.model.QUsuarioLoginLogEntity;
import br.ufs.hu.prevsep.web.api.model.UsuarioLoginLogEntity;
import br.ufs.hu.prevsep.web.api.repository.UsuarioLoginLogRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Service
public class UsuarioLogServiceImpl implements UsuarioLogService {

    private final UsuarioLoginLogRepository usuarioLoginLogRepository;
    private final UsuarioLogMapper usuarioLogMapper;
    private final DataSource dataSource;
    private final JasperReport reportLogin;
    private final JasperReport eventAccessReport;

    public UsuarioLogServiceImpl(UsuarioLoginLogRepository usuarioLoginLogRepository, DataSource dataSource) throws JRException {
        this.usuarioLoginLogRepository = usuarioLoginLogRepository;
        this.dataSource = dataSource;

        reportLogin =  JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream("reports/UsuarioLogins.jrxml"));
        eventAccessReport = JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream("reports/EventoAcessoReport.jrxml"));
        usuarioLogMapper = UsuarioLogMapper.INSTANCE;
    }

    @Override
    public PageUsuarioLoginLogDTO getLoginLogs(PageableUsuarioLoginLogDTO request) {
        if (request == null)
            request = new PageableUsuarioLoginLogDTO();

        return PageUsuarioLoginLogDTO.of(usuarioLoginLogRepository.findAll(
                request.getQueryPredicate(QUsuarioLoginLogEntity.usuarioLoginLogEntity),
                request).map(usuarioLogMapper::mapToUsuarioLoginLogDto));
    }

    @Override
    @Validated
    public byte[] getLoginReport(LoginReportRequest request) throws JRException, SQLException {
        try (Connection con = dataSource.getConnection()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            if (request.getCpfList() == null)
                request.setCpfList(new ArrayList<>());

            Map<String, Object> params = new HashMap<>();
            params.put("dt_inicio", java.sql.Timestamp.valueOf(request.getDtLoginBegin()));
            params.put("dt_fim", java.sql.Timestamp.valueOf(request.getDtLoginEnd()));
            params.put("ids_usuarios", request.getCpfList());

            JasperPrint jp = JasperFillManager.fillReport(reportLogin, params, con);
            JasperExportManager.exportReportToPdfStream(jp, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }

    @Override
    public UsuarioLoginLogDTO createLog(UsuarioLoginLogCreateDTO loginLogCreateDTO) {
        UsuarioLoginLogEntity entity = usuarioLogMapper.mapToUsuarioLoginLogEntity(loginLogCreateDTO);
        entity.setId(UUID.randomUUID().toString());

        return usuarioLogMapper.mapToUsuarioLoginLogDto(usuarioLoginLogRepository.save(entity));
    }

    @Override
    public byte[] getEventAccessReport(UsuarioEventAccessRequest request) throws JRException, SQLException {
        try (Connection con = dataSource.getConnection()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            if (request.getCpfList() == null)
                request.setCpfList(new ArrayList<>());

            Map<String, Object> params = new HashMap<>();
            params.put("dt_requisicao_inicio", java.sql.Timestamp.valueOf(request.getDtLoginBegin()));
            params.put("dt_requisicao_fim", java.sql.Timestamp.valueOf(request.getDtLoginEnd()));
            params.put("cpf_list", request.getCpfList());

            JasperPrint jp = JasperFillManager.fillReport(eventAccessReport, params, con);
            JasperExportManager.exportReportToPdfStream(jp, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
