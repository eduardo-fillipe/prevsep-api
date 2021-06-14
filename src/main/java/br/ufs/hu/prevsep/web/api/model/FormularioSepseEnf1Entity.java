package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "formularioSepseEnf1", schema = "public")
public class FormularioSepseEnf1Entity implements Serializable {
    private int idFormulario;
    private PacienteEntity paciente;
    private EnfermeiroEntity enfermeiro;
    private MedicoEntity medico;
    private String procedencia;
    private String sirs;
    private String disfOrganica;
    private Date dtAcMedico;
    private Date dtCriacao;
    private int status;
    private FormularioSepseEnf2Entity formularioSepseEnf2;
    private FormularioSepseMedicoEntity formularioSepseMedico;

    @Id
    @Column(name = "idFormulario", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "creEnfermeiro", referencedColumnName = "cre")
    public EnfermeiroEntity getEnfermeiro() {
        return enfermeiro;
    }

    public void setEnfermeiro(EnfermeiroEntity enfermeiro) {
        this.enfermeiro = enfermeiro;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "crmMedico", referencedColumnName = "crm")
    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    @Basic
    @Column(name = "procedencia", nullable = true, length = -1)
    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    @Basic
    @Column(name = "sirs", nullable = true, length = -1)
    public String getSirs() {
        return sirs;
    }

    public void setSirs(String sirs) {
        this.sirs = sirs;
    }

    @Basic
    @Column(name = "disfOrganica", nullable = true, length = -1)
    public String getDisfOrganica() {
        return disfOrganica;
    }

    public void setDisfOrganica(String disfOrganica) {
        this.disfOrganica = disfOrganica;
    }

    @Basic
    @Column(name = "dtAcMedico", nullable = true)
    public Date getDtAcMedico() {
        return dtAcMedico;
    }

    public void setDtAcMedico(Date dtAcMedico) {
        this.dtAcMedico = dtAcMedico;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFormulario", referencedColumnName = "idFormulario")
    public FormularioSepseEnf2Entity getFormularioSepseEnf2() {
        return formularioSepseEnf2;
    }

    public void setFormularioSepseEnf2(FormularioSepseEnf2Entity formularioSepseEnf2) {
        this.formularioSepseEnf2 = formularioSepseEnf2;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFormulario", referencedColumnName = "idFormulario")
    public FormularioSepseMedicoEntity getFormularioSepseMedico() {
        return formularioSepseMedico;
    }

    public void setFormularioSepseMedico(FormularioSepseMedicoEntity formularioSepseMedico) {
        this.formularioSepseMedico = formularioSepseMedico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseEnf1Entity that = (FormularioSepseEnf1Entity) o;
        return idFormulario == that.idFormulario && status == that.status && Objects.equals(procedencia, that.procedencia) && Objects.equals(sirs, that.sirs) && Objects.equals(disfOrganica, that.disfOrganica) && Objects.equals(dtAcMedico, that.dtAcMedico) && Objects.equals(dtCriacao, that.dtCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, procedencia, sirs, disfOrganica, dtAcMedico, dtCriacao, status);
    }
}
