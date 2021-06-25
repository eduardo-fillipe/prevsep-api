package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;

import java.sql.Timestamp;
import java.time.LocalDate;

public class NurseForm2DTO {
    private int idFormulario;
    private int creEnfermeiro;
    private PatientDTO patientDTO;
    private Timestamp dtUti;
    private Timestamp dtAlta;
    private Timestamp dtObito;
    private Timestamp dtCriacao;
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

    public Timestamp getDtUti() {
        return dtUti;
    }

    public void setDtUti(Timestamp dtUti) {
        this.dtUti = dtUti;
    }

    public Timestamp getDtAlta() {
        return dtAlta;
    }

    public void setDtAlta(Timestamp dtAlta) {
        this.dtAlta = dtAlta;
    }

    public Timestamp getDtObito() {
        return dtObito;
    }

    public void setDtObito(Timestamp dtObito) {
        this.dtObito = dtObito;
    }

    public Timestamp getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Timestamp dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }
}
