package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;

import java.time.LocalDate;

public class NurseForm2DTO {
    private int idFormulario;
    private int creEnfermeiro;
    private PatientDTO patientDTO;
    private LocalDate dtUti;
    private LocalDate dtAlta;
    private LocalDate dtObito;
    private LocalDate dtCriacao;
    private FormStatus status;

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public int getCreEnfermeiro() {
        return creEnfermeiro;
    }

    public void setCreEnfermeiro(int creEnfermeiro) {
        this.creEnfermeiro = creEnfermeiro;
    }

    public PatientDTO getPatientDTO() {
        return patientDTO;
    }

    public void setPatientDTO(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

    public LocalDate getDtUti() {
        return dtUti;
    }

    public void setDtUti(LocalDate dtUti) {
        this.dtUti = dtUti;
    }

    public LocalDate getDtAlta() {
        return dtAlta;
    }

    public void setDtAlta(LocalDate dtAlta) {
        this.dtAlta = dtAlta;
    }

    public LocalDate getDtObito() {
        return dtObito;
    }

    public void setDtObito(LocalDate dtObito) {
        this.dtObito = dtObito;
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
