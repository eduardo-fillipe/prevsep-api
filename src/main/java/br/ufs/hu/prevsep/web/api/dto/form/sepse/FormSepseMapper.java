package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientCreateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;
import br.ufs.hu.prevsep.web.api.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.LocalDate;

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

    FormularioSepseEnf1DinsfOrgDTO mapToFormularioSepseEnf1DinsfOrgDto(FormularioSepseEnf1DinsfOrgEntity entity);

    default ProcedenciaDTO mapToProcedenciaDto(Integer value) {
        return ProcedenciaDTO.fromValue(value);
    }

    default Integer mapToValue(ProcedenciaDTO procedenciaDTO) {
        return ProcedenciaDTO.toValue(procedenciaDTO);
    }

    @Mappings({
            @Mapping(target = "idFormulario", ignore = true)
    })
    FormularioSepseEnf1DinsfOrgEntity mapToFormularioSepseEnf1DinsfOrgEntity(FormularioSepseEnf1DinsfOrgDTO dto);

    FormularioSepseEnf1SirsDTO mapToFormularioSepseEnf1SirsDto(FormularioSepseEnf1SirsEntity entity);

    @Mappings({
            @Mapping(target = "idFormulario", ignore = true)
    })
    FormularioSepseEnf1SirsEntity mapToFormularioSepseEnf1SirsEntity(FormularioSepseEnf1SirsDTO dto);

    @Mappings({
            @Mapping(target = "creEnfermeiro", ignore = true),
            @Mapping(target = "crmMedico", source = "crmMedico"),
            @Mapping(target = "formularioSepseEnf2", ignore = true),
            @Mapping(target = "formularioSepseMedico", ignore = true),
            @Mapping(target = "idFormulario", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "dtAcMedico", ignore = true),
            @Mapping(target = "dtCriacao", ignore = true)
    })
    FormularioSepseEnf1Entity mapToFormularioSepseEnf1Entity(NurseForm1CreateDTO dto);

    @Mappings({
            @Mapping(target = "creEnfermeiro", ignore = true),
            @Mapping(target = "formularioSepseEnf2", ignore = true),
            @Mapping(target = "formularioSepseMedico", ignore = true),
            @Mapping(target = "idFormulario", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "dtAcMedico", ignore = true),
            @Mapping(target = "dtCriacao", ignore = true)
    })
    FormularioSepseEnf1Entity mapToFormularioSepseEnf1Entity(NurseForm1UpdateDTO dto);

    @Mappings({
            @Mapping(target = "crmMedico", ignore = true),
            @Mapping(target = "idFormulario", ignore = true),
            @Mapping(target = "idPaciente", ignore = true),
            @Mapping(target = "dtCriacao", ignore = true),
            @Mapping(target = "status", ignore = true)
    })
    FormularioSepseMedicoEntity mapToFormularioSepseMedicoEntity(DoctorFormUpdateDTO dto);


    DoctorFormDTO mapToDoctorFormDto(FormularioSepseMedicoEntity entity);

    @Mappings({
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "idFormulario", ignore = true),
            @Mapping(target = "dtCriacao", ignore = true),
            @Mapping(target = "creEnfermeiro", ignore = true)
    })
    FormularioSepseEnf2Entity mapToFormularioSepseEnf2Entity(NurseForm2UpdateDTO dto);

    FormularioSepseMedicoBundleDTO mapToFormularioSepseMedicoBundleDto(FormularioSepseMedicoBundleEntity entity);

    @Mappings({
            @Mapping(target = "idFormulario", ignore = true)
    })
    FormularioSepseMedicoBundleEntity mapToFormularioSepseMedicoBundleEntity(FormularioSepseMedicoBundleDTO dto);

    FormularioSepseMedicoCriterioExclusaoDTO mapToFormularioSepseMedicoCrierioExclusaoDto(FormularioSepseMedicoCriterioExclusaoEntity entity);

    @Mappings({
            @Mapping(target = "idFormulario", ignore = true)
    })
    FormularioSepseMedicoFocoInfecciosoEntity mapToFormularioSepseMedicoFocoInfecciosoEntity(FormularioSepseMedicoFocoInfecciosoDTO dto);

    FormularioSepseMedicoFocoInfecciosoDTO mapToFormularioSepseMedicoFocoInfecciosoDto(FormularioSepseMedicoFocoInfecciosoEntity entity);

    @Mappings({
            @Mapping(target = "idFormulario", ignore = true)
    })
    FormularioSepseMedicoReavaliacoesSeriadasEntity mapToFormularioSepseMedicoReavaliacoesSeriadasEntity(FormularioSepseMedicoReavaliacoesSeriadasDTO dto);

    FormularioSepseMedicoReavaliacoesSeriadasDTO mapToFormularioSepseMedicoReavaliacoesSeriadasDto(FormularioSepseMedicoReavaliacoesSeriadasEntity entity);

    @Mappings({
            @Mapping(target = "idFormulario", ignore = true)
    })
    FormularioSepseMedicoCriterioExclusaoEntity mapToFormularioSepseMedicoCriterioExclusaoEntity(FormularioSepseMedicoCriterioExclusaoDTO dto);


    @Mappings({
            @Mapping(target = "patientDTO", ignore = true)
    })
    NurseForm2DTO mapToNurseForm2Dto(FormularioSepseEnf2Entity entity);

    NurseForm1DTO mapToNurseForm1Dto(FormularioSepseEnf1Entity entity);

    default FormStatus map(Integer value) {
        return FormStatus.fromValue(value);
    }

    default int map(FormStatus status) {
        if (status != null)
            return status.getValue();
        return 0;
    }

    default Date map(LocalDate localDate) {
        return localDate == null ? null : Date.valueOf(localDate);
    }
}
