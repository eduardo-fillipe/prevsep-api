package br.ufs.hu.prevsep.web.api.dto.mapper;

import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerFullDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.*;
import br.ufs.hu.prevsep.web.api.model.EnfermeiroEntity;
import br.ufs.hu.prevsep.web.api.model.ManagerEntity;
import br.ufs.hu.prevsep.web.api.model.MedicoEntity;
import br.ufs.hu.prevsep.web.api.model.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO mapToUsuarioResponseDto(UsuarioEntity entity);

    default Integer mapStatus(StatusUsuarioEnum value) {
        return value == null ? null : value.getStatus();
    }

    default Integer mapCargo(CargoEnum value) {
        return value == null ? null : value.getId();
    }

    default StatusUsuarioEnum mapStatus(Integer value) {
        return StatusUsuarioEnum.fromId(value);
    }

    default CargoEnum mapCargo(Integer value) {
        return CargoEnum.fromId(value);
    }

    @Mappings({
            @Mapping(target = "statusUsuario", source = "entity.userInfo.status"),
            @Mapping(target = "nome", source = "entity.userInfo.nome")
    })
    DoctorResponseDTO mapToDoctorResponseDto(MedicoEntity entity);

    DoctorResponseFullDTO mapToDoctorResponseFullDto(MedicoEntity entity);

    @Mapping(target = "cargo", ignore = true)
    UsuarioEntity mapToUsuarioEntity(UsuarioRequestDTO usuarioRequestDTO);

    @Mapping(target = "cpf", ignore = true)
    UsuarioEntity mapToUsuarioEntity(UsuarioUpdateDTO usuarioRequestDTO);

    @Mappings({
            @Mapping(target = "cpf", source = "dto.userInfo.cpf")
    })
    MedicoEntity mapToDoctorEntity(DoctorRequestDTO dto);

    @Mappings({
            @Mapping(target = "userInfo", ignore = true),
            @Mapping(target = "cpf", ignore = true)
    })
    MedicoEntity mapToDoctorEntity(DoctorUpdateDTO doctorUpdateDTO);

    @Mappings({
            @Mapping(target = "statusUsuario", source = "entity.userInfo.status"),
            @Mapping(target = "nome", source = "entity.userInfo.nome")
    })
    ManagerDTO mapToManagerDto(ManagerEntity entity);

    ManagerFullDTO mapToManagerFullDto(ManagerEntity entity);

    @Mappings({
            @Mapping(target = "cpf", source = "dto.userInfo.cpf")
    })
    ManagerEntity mapToManagerEntity(ManagerRequestDTO dto);
    @Mappings({
            @Mapping(target = "userInfo", ignore = true),
            @Mapping(target = "cpf", ignore = true)
    })
    ManagerEntity mapToManagerEntity(ManagerUpdateDTO dto);

    @Mappings({
            @Mapping(target = "statusUsuario", source = "entity.userInfo.status"),
            @Mapping(target = "nome", source = "entity.userInfo.nome")
    })
    NurseDTO mapToNurseDto(EnfermeiroEntity entity);

    NurseFullDTO mapToNurseFullDto(EnfermeiroEntity entity);

    @Mappings({
            @Mapping(target = "userInfo", ignore = true),
            @Mapping(target = "cpf", ignore = true)
    })
    EnfermeiroEntity mapToEnfermeiroEntity(NurseUpdateDTO dto);
    @Mappings({
            @Mapping(target = "cpf", source = "dto.userInfo.cpf")
    })
    EnfermeiroEntity mapToEnfermeiroEntity(NurseRequestDTO dto);

}
