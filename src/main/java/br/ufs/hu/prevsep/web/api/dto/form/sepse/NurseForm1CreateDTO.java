package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.PatientCreateDTO;

import javax.validation.Valid;

public class NurseForm1CreateDTO {

    @Valid
    private PatientCreateDTO paciente;
    private Integer crmMedico;
    private ProcedenciaDTO procedencia;
    private FormularioSepseEnf1SirsDTO sirs;
    private FormularioSepseEnf1DinsfOrgDTO disfOrganica;
    private Boolean finalizado;


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

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }
}
