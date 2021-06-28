package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "formulario_sepse_enf1", schema = "public")
public class FormularioSepseEnf1Entity implements Serializable {
    private int idFormulario;
    private PacienteEntity paciente;
    private int creEnfermeiro;
    private int crmMedico;
    private int procedencia;
    private Date dtAcMedico;
    private Date dtCriacao;
    private int status;
    private FormularioSepseEnf1SirsEntity sirs;
    private FormularioSepseEnf1DinsfOrgEntity disfOrganica;
    private FormularioSepseEnf2Entity formularioSepseEnf2;
    private FormularioSepseMedicoEntity formularioSepseMedico;

    @Id
    @Column(name = "id_formulario", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    @Column(name = "cre_enfermeiro")
    public int getCreEnfermeiro() {
        return creEnfermeiro;
    }

    public void setCreEnfermeiro(int creEnfermeiro) {
        this.creEnfermeiro = creEnfermeiro;
    }

    @Column(name = "crm_medico")
    public int getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(int crmMedico) {
        this.crmMedico = crmMedico;
    }

    @Basic
    @Column(name = "procedencia", nullable = true, length = -1)
    public Integer getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(int procedencia) {
        this.procedencia = procedencia;
    }

    @Basic
    @Column(name = "dt_ac_medico", nullable = true)
    public Date getDtAcMedico() {
        return dtAcMedico;
    }

    public void setDtAcMedico(Date dtAcMedico) {
        this.dtAcMedico = dtAcMedico;
    }

    @Basic
    @Column(name = "dt_criacao", nullable = false)
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
    @JoinColumn(name = "id_formulario", referencedColumnName = "id_formulario")
    public FormularioSepseEnf2Entity getFormularioSepseEnf2() {
        return formularioSepseEnf2;
    }

    public void setFormularioSepseEnf2(FormularioSepseEnf2Entity formularioSepseEnf2) {
        this.formularioSepseEnf2 = formularioSepseEnf2;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formulario", referencedColumnName = "id_formulario")
    public FormularioSepseMedicoEntity getFormularioSepseMedico() {
        return formularioSepseMedico;
    }

    public void setFormularioSepseMedico(FormularioSepseMedicoEntity formularioSepseMedico) {
        this.formularioSepseMedico = formularioSepseMedico;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sirs", referencedColumnName = "id", nullable = false)
    public FormularioSepseEnf1SirsEntity getSirs() {
        return sirs;
    }

    public void setSirs(FormularioSepseEnf1SirsEntity sirs) {
        this.sirs = sirs;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dinsf_org", referencedColumnName = "id", nullable = false)
    public FormularioSepseEnf1DinsfOrgEntity getDisfOrganica() {
        return disfOrganica;
    }

    public void setDisfOrganica(FormularioSepseEnf1DinsfOrgEntity disfOrganica) {
        this.disfOrganica = disfOrganica;
    }
}