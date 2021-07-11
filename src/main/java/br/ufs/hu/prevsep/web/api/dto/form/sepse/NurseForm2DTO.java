package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class NurseForm2DTO {
    private int idFormulario;
    private PatientDTO paciente;
    private int creEnfermeiro;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtUti;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtAlta;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtObito;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtCriacao;
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

    public int getCreEnfermeiro() {
        return creEnfermeiro;
    }

    public void setCreEnfermeiro(int creEnfermeiro) {
        this.creEnfermeiro = creEnfermeiro;
    }

    public LocalDateTime getDtUti() {
        return dtUti;
    }

    public void setDtUti(LocalDateTime dtUti) {
        this.dtUti = dtUti;
    }

    public LocalDateTime getDtAlta() {
        return dtAlta;
    }

    public void setDtAlta(LocalDateTime dtAlta) {
        this.dtAlta = dtAlta;
    }

    public LocalDateTime getDtObito() {
        return dtObito;
    }

    public void setDtObito(LocalDateTime dtObito) {
        this.dtObito = dtObito;
    }

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }
}
