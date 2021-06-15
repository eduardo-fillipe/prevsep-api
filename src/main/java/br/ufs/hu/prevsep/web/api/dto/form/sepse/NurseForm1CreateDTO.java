package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.PatientCreateDTO;

import javax.validation.Valid;
import java.time.LocalDate;

public class NurseForm1CreateDTO {

    @Valid
    private PatientCreateDTO paciente;
    private Integer crmMedico;
    private String procedencia;
    private String sirs;
    private String disfOrganica;
    private LocalDate dtAcMedico;
    private LocalDate dtCriacao;
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

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getSirs() {
        return sirs;
    }

    public void setSirs(String sirs) {
        this.sirs = sirs;
    }

    public String getDisfOrganica() {
        return disfOrganica;
    }

    public void setDisfOrganica(String disfOrganica) {
        this.disfOrganica = disfOrganica;
    }

    public LocalDate getDtAcMedico() {
        return dtAcMedico;
    }

    public void setDtAcMedico(LocalDate dtAcMedico) {
        this.dtAcMedico = dtAcMedico;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDate dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }
}
