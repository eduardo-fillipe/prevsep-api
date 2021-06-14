package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "formularioSepseEnf2", schema = "public")
public class FormularioSepseEnf2Entity implements Serializable {
    private int idFormulario;
    private int creEnfermeiro;
    private Date dtUti;
    private Date dtAlta;
    private Date dtObito;
    private Date dtCriacao;
    private int status;

    @Id
    @Column(name = "idFormulario", nullable = false)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @Column(name = "creEnfermeiro")
    public int getCreEnfermeiro() {
        return creEnfermeiro;
    }

    public void setCreEnfermeiro(int creEnfermeiro) {
        this.creEnfermeiro = creEnfermeiro;
    }

    @Basic
    @Column(name = "dtUTI", nullable = true)
    public Date getDtUti() {
        return dtUti;
    }

    public void setDtUti(Date dtUti) {
        this.dtUti = dtUti;
    }

    @Basic
    @Column(name = "dtAlta", nullable = true)
    public Date getDtAlta() {
        return dtAlta;
    }

    public void setDtAlta(Date dtAlta) {
        this.dtAlta = dtAlta;
    }

    @Basic
    @Column(name = "dtObito", nullable = true)
    public Date getDtObito() {
        return dtObito;
    }

    public void setDtObito(Date dtObito) {
        this.dtObito = dtObito;
    }

    @Basic
    @Column(name = "dtCriacao", nullable = false)
    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
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
