package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "formulario_sepse_enf2", schema = "public")
public class FormularioSepseEnf2Entity implements Serializable {
    private int idFormulario;
    private int creEnfermeiro;
    private LocalDateTime dtUti;
    private LocalDateTime dtAlta;
    private LocalDateTime dtObito;
    private LocalDateTime dtCriacao;
    private int status;

    @Id
    @Column(name = "id_formulario", nullable = false)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @Column(name = "cre_enfermeiro")
    public int getCreEnfermeiro() {
        return creEnfermeiro;
    }

    public void setCreEnfermeiro(int creEnfermeiro) {
        this.creEnfermeiro = creEnfermeiro;
    }

    @Basic
    @Column(name = "dt_uti", nullable = true)
    public LocalDateTime getDtUti() {
        return dtUti;
    }

    public void setDtUti(LocalDateTime dtUti) {
        this.dtUti = dtUti;
    }

    @Basic
    @Column(name = "dt_alta", nullable = true)
    public LocalDateTime getDtAlta() {
        return dtAlta;
    }

    public void setDtAlta(LocalDateTime dtAlta) {
        this.dtAlta = dtAlta;
    }

    @Basic
    @Column(name = "dt_obito", nullable = true)
    public LocalDateTime getDtObito() {
        return dtObito;
    }

    public void setDtObito(LocalDateTime dtObito) {
        this.dtObito = dtObito;
    }

    @Basic
    @Column(name = "dt_criacao", nullable = false)
    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseEnf2Entity that = (FormularioSepseEnf2Entity) o;
        return idFormulario == that.idFormulario && status == that.status && Objects.equals(dtUti, that.dtUti) && Objects.equals(dtAlta, that.dtAlta) && Objects.equals(dtObito, that.dtObito) && Objects.equals(dtCriacao, that.dtCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, dtUti, dtAlta, dtObito, dtCriacao, status);
    }
}
