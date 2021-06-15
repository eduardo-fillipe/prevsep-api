package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientCreateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseEnf1Entity;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseEnf2Entity;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseMedicoEntity;
import br.ufs.hu.prevsep.web.api.model.PacienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.sql.Date;

@Mapper
public interface FormSepseMapper {
    FormSepseMapper INSTANCE = Mappers.getMapper(FormSepseMapper.class);

    @Mappings({
            @Mapping(target = "formularios", ignore = true),
            @Mapping(target = "idPaciente", ignore = true)
    })
    PacienteEntity mapToPacienteEntity(PatientCreateDTO dto);

    PatientDTO mapToPatientDto(PacienteEntity entity);

    NurseForm1DTO mapNurseForm1Dto(FormularioSepseEnf1Entity entity);

    @Mappings({
            @Mapping(target = "creEnfermeiro", ignore = true),
            @Mapping(target = "crmMedico", source = "crmMedico"),
            @Mapping(target = "formularioSepseEnf2", ignore = true),
            @Mapping(target = "formularioSepseMedico", ignore = true),
            @Mapping(target = "idFormulario", ignore = true),
            @Mapping(target = "status", ignore = true)
    })
    FormularioSepseEnf1Entity mapToFormularioSepseEnf1Entity(NurseForm1CreateDTO dto);

    @Mappings({
            @Mapping(target = "crmMedico", ignore = true),
            @Mapping(target = "idFormulario", ignore = true),
            @Mapping(target = "idPaciente", ignore = true),
            @Mapping(target = "dtCriacao", ignore = true)
    })
    FormularioSepseMedicoEntity mapToFormularioSepseMedicoEntity(DoctorFormUpdateDTO dto);

    @Mappings({
            @Mapping(target = "paciente", ignore = true),
            @Mapping(target = "paciente.idPaciente", source = "idPaciente")
    })
    DoctorFormDTO mapToDoctorFormDto(FormularioSepseMedicoEntity entity);

    @Mappings({
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "idFormulario", ignore = true),
            @Mapping(target = "dtCriacao", ignore = true),
            @Mapping(target = "creEnfermeiro", ignore = true)
    })
    FormularioSepseEnf2Entity mapToFormularioSepseEnf2Entity(NurseForm2UpdateDTO dto);

    @Mappings({
            @Mapping(target = "patientDTO", ignore = true)
    })
    NurseForm2DTO mapToNurseForm2Dto(FormularioSepseEnf2Entity entity);

    default FormStatus map(Integer value) {
        return FormStatus.fromValue(value);
    }

    default int map(FormStatus status) {
        if (status != null)
            return status.getValue();
        return 0;
    }

    default Date map(LocalDate localDate) {
        return Date.valueOf(localDate);
    }
}
