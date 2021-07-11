package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.PatientCreateDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NurseForm1UpdateDTO {

    @NotNull
    @Valid
    private PatientCreateDTO paciente;
    @Min(1)
    @NotNull
    private Integer crmMedico;
    @Valid
    private ProcedenciaDTO procedencia;
    @Valid
    private FormularioSepseEnf1SirsDTO sirs;
    @Valid
    private FormularioSepseEnf1DinsfOrgDTO disfOrganica;


    public PatientCreateDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PatientCreateDTO paciente) {
        this.paciente = paciente;
    }

    public Integer getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(Integer crmMedico) {
        this.crmMedico = crmMedico;
    }

    public ProcedenciaDTO getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(ProcedenciaDTO procedencia) {
        this.procedencia = procedencia;
    }

    public FormularioSepseEnf1SirsDTO getSirs() {
        return sirs;
    }

    public void setSirs(FormularioSepseEnf1SirsDTO sirs) {
        this.sirs = sirs;
    }

    public FormularioSepseEnf1DinsfOrgDTO getDisfOrganica() {
        return disfOrganica;
    }

    public void setDisfOrganica(FormularioSepseEnf1DinsfOrgDTO disfOrganica) {
        this.disfOrganica = disfOrganica;
    }

}
