package br.ufs.hu.prevsep.web.api.dto.mapper;

import br.ufs.hu.prevsep.web.api.dto.manager.ManagerDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerFullDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.medic.MedicoUpdateDTO;
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

    UsuarioResponseDTO mapToUsuarioResponseDto(UsuarioEntity entity);

    default int mapStatus(StatusUsuarioEnum value) {
        return value.getStatus();
    }

    default int mapCargo(CargoEnum value) {
        return value.getId();
    }

    default StatusUsuarioEnum mapStatus(Integer value) {
        return StatusUsuarioEnum.fromId(value);
    }

    default CargoEnum mapCargo(Integer value) {
        return CargoEnum.fromId(value);
    }

    @Mapping(target = "statusUsuario", source = "entity.userInfo.status")
    MedicoResponseDTO mapToMedicoResponseDto(MedicoEntity entity);

    MedicoResponseFullDTO mapToMedicoResponseFullDto(MedicoEntity entity);

    UsuarioEntity mapToUsuarioEntity(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioEntity mapToUsuarioEntity(UsuarioUpdateDTO usuarioRequestDTO);

    UsuarioUpdateDTO mapUsuarioUpdateDto(UsuarioResponseDTO dto);

    @Mappings({
            @Mapping(target = "cpf", source = "medicoRequestDTO.userInfo.cpf")
    })
    MedicoEntity mapToMedicoEntity(MedicoRequestDTO medicoRequestDTO);

    @Mappings({
            @Mapping(target = "userInfo", ignore = true)
    })
    MedicoEntity mapToMedicoEntity(MedicoUpdateDTO medicoUpdateDTO);

    @Mapping(target = "statusUsuario", source = "entity.userInfo.status")
    ManagerDTO mapToManagerDto(ManagerEntity entity);

    ManagerFullDTO mapToManagerFullDto(ManagerEntity entity);

    ManagerRequestDTO mapToManagerRequestDto(ManagerEntity entity);

    ManagerUpdateDTO mapToManagerUpdateDto(ManagerEntity entity);
    @Mappings({
            @Mapping(target = "cpf", source = "dto.userInfo.cpf")
    })
    ManagerEntity mapToManagerEntity(ManagerRequestDTO dto);
    @Mappings({
            @Mapping(target = "userInfo", ignore = true)
    })
    ManagerEntity mapToManagerEntity(ManagerUpdateDTO dto);

    @Mapping(target = "statusUsuario", source = "entity.userInfo.status")
    NurseDTO mapToNurseDto(EnfermeiroEntity entity);

    NurseFullDTO mapToNurseFullDto(EnfermeiroEntity entity);

    NurseRequestDTO mapToNurseRequestDto(EnfermeiroEntity entity);

    NurseUpdateDTO mapToNurseUpdateDto(EnfermeiroEntity entity);
    @Mappings({
            @Mapping(target = "userInfo", ignore = true)
    })
    EnfermeiroEntity mapToEnfermeiroEntity(NurseUpdateDTO dto);
    @Mappings({
            @Mapping(target = "cpf", source = "dto.userInfo.cpf")
    })
    EnfermeiroEntity mapToEnfermeiroEntity(NurseRequestDTO dto);

}
