package br.ufs.hu.prevsep.web.api.dto.security;

import br.ufs.hu.prevsep.web.api.model.UsuarioLoginLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioLogMapper {

    UsuarioLogMapper INSTANCE = Mappers.getMapper(UsuarioLogMapper.class);


    UsuarioLoginLogDTO mapToUsuarioLoginLogDto(UsuarioLoginLogEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    UsuarioLoginLogEntity mapToUsuarioLoginLogEntity(UsuarioLoginLogCreateDTO dto);
}
