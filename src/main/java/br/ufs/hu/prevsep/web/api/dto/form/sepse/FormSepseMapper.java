package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.PatientCreateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseEnf1Entity;
import br.ufs.hu.prevsep.web.api.model.PacienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Mapper
public interface FormSepseMapper {
    FormSepseMapper INSTANCE = Mappers.getMapper(FormSepseMapper.class);

    @Mappings({
            @Mapping(target = "formularios", ignore = true),
            @Mapping(target = "idPaciente", ignore = true)
    })
    PacienteEntity mapToPacienteEntity(PatientCreateDTO dto);

    PatientDTO mapToPatientDto(PacienteEntity entity);

    @Mapping(target = "crmMedico", source = "medico.crm")
    NurseForm1DTO mapNurseForm1Dto(FormularioSepseEnf1Entity entity);

    @Mappings({
            @Mapping(target = "enfermeiro", ignore = true),
            @Mapping(target = "medico.crm", source = "crmMedico"),
            @Mapping(target = "medico", ignore = true),
            @Mapping(target = "formularioSepseEnf2", ignore = true),
            @Mapping(target = "formularioSepseMedico", ignore = true),
            @Mapping(target = "idFormulario", ignore = true),
            @Mapping(target = "status", ignore = true)
    })
    FormularioSepseEnf1Entity mapToFormularioSepseEnf1Entity(NurseForm1CreateDTO dto);

    default Date map(LocalDateTime localDateTime) {
        return new Date(localDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli());
    }
}
