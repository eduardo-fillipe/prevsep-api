package br.ufs.hu.prevsep.web.api.dto.security;

import br.ufs.hu.prevsep.web.api.dto.user.usuario.CargoEnum;
import br.ufs.hu.prevsep.web.api.model.UsuarioLoginLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioLogMapper {

    UsuarioLogMapper INSTANCE = Mappers.getMapper(UsuarioLogMapper.class);

    @Mappings({
            @Mapping(target = "nomeUsuario", source = "usuario.nome")
    })
    UsuarioLoginLogDTO mapToUsuarioLoginLogDto(UsuarioLoginLogEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "usuario", ignore = true)
    })
    UsuarioLoginLogEntity mapToUsuarioLoginLogEntity(UsuarioLoginLogCreateDTO dto);

    default CargoEnum map(Integer value) {
        return CargoEnum.fromId(value);
    }

    default Integer map(CargoEnum cargoEnum) {
        if (cargoEnum != null)
            return cargoEnum.getId();
        return null;
    }
}
