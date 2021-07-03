package br.ufs.hu.prevsep.web.api.service.security;

import br.ufs.hu.prevsep.web.api.dto.security.UsuarioLogMapper;
import br.ufs.hu.prevsep.web.api.dto.security.UsuarioLoginLogCreateDTO;
import br.ufs.hu.prevsep.web.api.dto.security.UsuarioLoginLogDTO;
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
    public UsuarioLoginLogDTO createLog(UsuarioLoginLogCreateDTO loginLogCreateDTO) {
        UsuarioLoginLogEntity entity = usuarioLogMapper.mapToUsuarioLoginLogEntity(loginLogCreateDTO);
        entity.setId(UUID.randomUUID().toString());

        return usuarioLogMapper.mapToUsuarioLoginLogDto(usuarioLoginLogRepository.save(entity));
    }
}
