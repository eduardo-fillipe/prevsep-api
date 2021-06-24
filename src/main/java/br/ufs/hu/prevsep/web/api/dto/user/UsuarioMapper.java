package br.ufs.hu.prevsep.web.api.dto.user;

import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.manager.ManagerUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.*;
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
            @Mapping(target = "cpf", source = "dto.userInfo.cpf"),
            @Mapping(target = "formularioSepseMedicoEntities", ignore = true)
    })
    MedicoEntity mapToDoctorEntity(DoctorRequestDTO dto);

    @Mappings({
            @Mapping(target = "userInfo", ignore = true),
            @Mapping(target = "cpf", ignore = true),
            @Mapping(target = "formularioSepseMedicoEntities", ignore = true)
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
            @Mapping(target = "cpf", ignore = true),
            @Mapping(target = "formularioSepseEnf1Entities", ignore = true),
            @Mapping(target = "formularioSepseEnf2Entities", ignore = true)
    })
    EnfermeiroEntity mapToEnfermeiroEntity(NurseUpdateDTO dto);
    @Mappings({
            @Mapping(target = "cpf", source = "dto.userInfo.cpf"),
            @Mapping(target = "formularioSepseEnf1Entities", ignore = true),
            @Mapping(target = "formularioSepseEnf2Entities", ignore = true)
    })
    EnfermeiroEntity mapToEnfermeiroEntity(NurseRequestDTO dto);

}
