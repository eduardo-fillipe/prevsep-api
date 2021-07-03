package br.ufs.hu.prevsep.web.api.service.security;

import br.ufs.hu.prevsep.web.api.dto.security.*;
import br.ufs.hu.prevsep.web.api.model.QUsuarioLoginLogEntity;
import br.ufs.hu.prevsep.web.api.model.UsuarioLoginLogEntity;
import br.ufs.hu.prevsep.web.api.repository.UsuarioLoginLogRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioLogServiceImpl implements UsuarioLogService {

    private final UsuarioLoginLogRepository usuarioLoginLogRepository;
    private final UsuarioLogMapper usuarioLogMapper;

    public UsuarioLogServiceImpl(UsuarioLoginLogRepository usuarioLoginLogRepository) {
        this.usuarioLoginLogRepository = usuarioLoginLogRepository;
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
    public UsuarioLoginLogDTO createLog(UsuarioLoginLogCreateDTO loginLogCreateDTO) {
        UsuarioLoginLogEntity entity = usuarioLogMapper.mapToUsuarioLoginLogEntity(loginLogCreateDTO);
        entity.setId(UUID.randomUUID().toString());

        return usuarioLogMapper.mapToUsuarioLoginLogDto(usuarioLoginLogRepository.save(entity));
    }
}
