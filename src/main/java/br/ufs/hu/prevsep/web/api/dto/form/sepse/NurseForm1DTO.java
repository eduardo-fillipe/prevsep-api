package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;

import java.time.LocalDate;

public class NurseForm1DTO {
    private int idFormulario;
    private PatientDTO paciente;
    private Integer crmMedico;
    private Integer creEnfermeiro;
    private ProcedenciaDTO procedencia;
    private FormularioSepseEnf1SirsDTO sirs;
    private FormularioSepseEnf1DinsfOrgDTO disfOrganica;
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

    public Integer getCreEnfermeiro() {
        return creEnfermeiro;
    }

    public void setCreEnfermeiro(Integer creEnfermeiro) {
        this.creEnfermeiro = creEnfermeiro;
    }
}
