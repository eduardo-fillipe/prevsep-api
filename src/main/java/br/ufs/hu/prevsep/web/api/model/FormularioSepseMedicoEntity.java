package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "formularioSepseMedico", schema = "public")
public class FormularioSepseMedicoEntity implements Serializable {
    private PacienteEntity paciente;
    private int crmMedico;
    private int idFormulario;
    private String focoInfeccioso;
    private String critExclusao;
    private String bundleHora1;
    private Date dtDispProtocolo;
    private Date dtColetaLactato;
    private Date dtColetaHemocult;
    private Date dtPrimeiraDose;
    private Date dtCriacao;
    private int status;
    private String reavaliacoesSeriadas;
    private FormularioSepseEnf2Entity formularioSepseEnf2ByIdFormulario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    @Id
    @Column(name = "idFormulario", nullable = false)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @Basic
    @Column(name = "focoInfeccioso", nullable = true, length = -1)
    public String getFocoInfeccioso() {
        return focoInfeccioso;
    }

    public void setFocoInfeccioso(String focoInfeccioso) {
        this.focoInfeccioso = focoInfeccioso;
    }

    @Basic
    @Column(name = "crmMedico", nullable = false)
    public int getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(int crmMedico) {
        this.crmMedico = crmMedico;
    }

    @Basic
    @Column(name = "critExclusao", nullable = true, length = -1)
    public String getCritExclusao() {
        return critExclusao;
    }

    public void setCritExclusao(String critExclusao) {
        this.critExclusao = critExclusao;
    }

    @Basic
    @Column(name = "bundleHora1", nullable = true, length = -1)
    public String getBundleHora1() {
        return bundleHora1;
    }

    public void setBundleHora1(String bundleHora1) {
        this.bundleHora1 = bundleHora1;
    }

    @Basic
    @Column(name = "dtDispProtocolo", nullable = true)
    public Date getDtDispProtocolo() {
        return dtDispProtocolo;
    }

    public void setDtDispProtocolo(Date dtDispProtocolo) {
        this.dtDispProtocolo = dtDispProtocolo;
    }

    @Basic
    @Column(name = "dtColetaLactato", nullable = true)
    public Date getDtColetaLactato() {
        return dtColetaLactato;
    }

    public void setDtColetaLactato(Date dtColetaLactato) {
        this.dtColetaLactato = dtColetaLactato;
    }

    @Basic
    @Column(name = "dtColetaHemocult", nullable = true)
    public Date getDtColetaHemocult() {
        return dtColetaHemocult;
    }

    public void setDtColetaHemocult(Date dtColetaHemocult) {
        this.dtColetaHemocult = dtColetaHemocult;
    }

    @Basic
    @Column(name = "dtPrimeiraDose", nullable = true)
    public Date getDtPrimeiraDose() {
        return dtPrimeiraDose;
    }

    public void setDtPrimeiraDose(Date dtPrimeiraDose) {
        this.dtPrimeiraDose = dtPrimeiraDose;
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

    @Basic
    @Column(name = "reavaliacoesSeriadas", nullable = true, length = -1)
    public String getReavaliacoesSeriadas() {
        return reavaliacoesSeriadas;
    }

    public void setReavaliacoesSeriadas(String reavaliacoesSeriadas) {
        this.reavaliacoesSeriadas = reavaliacoesSeriadas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseMedicoEntity that = (FormularioSepseMedicoEntity) o;
        return idFormulario == that.idFormulario && status == that.status && Objects.equals(focoInfeccioso, that.focoInfeccioso) && Objects.equals(critExclusao, that.critExclusao) && Objects.equals(bundleHora1, that.bundleHora1) && Objects.equals(dtDispProtocolo, that.dtDispProtocolo) && Objects.equals(dtColetaLactato, that.dtColetaLactato) && Objects.equals(dtColetaHemocult, that.dtColetaHemocult) && Objects.equals(dtPrimeiraDose, that.dtPrimeiraDose) && Objects.equals(dtCriacao, that.dtCriacao) && Objects.equals(reavaliacoesSeriadas, that.reavaliacoesSeriadas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, focoInfeccioso, critExclusao, bundleHora1, dtDispProtocolo, dtColetaLactato, dtColetaHemocult, dtPrimeiraDose, dtCriacao, status, reavaliacoesSeriadas);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFormulario", referencedColumnName = "idFormulario")
    public FormularioSepseEnf2Entity getFormularioSepseEnf2ByIdFormulario() {
        return formularioSepseEnf2ByIdFormulario;
    }

    public void setFormularioSepseEnf2ByIdFormulario(FormularioSepseEnf2Entity formularioSepseEnf2ByIdFormulario) {
        this.formularioSepseEnf2ByIdFormulario = formularioSepseEnf2ByIdFormulario;
    }
}
