package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NurseForm1DTO {
    private int idFormulario;
    private PatientDTO paciente;
    private Integer crmMedico;
    private String procedencia;
    private String sirs;
    private String disfOrganica;
    private LocalDate dtAcMedico;
    private LocalDate dtCriacao;
    private FormStatus status;

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public PatientDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PatientDTO paciente) {
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

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }
}
