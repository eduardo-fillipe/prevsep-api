package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "formulario_sepse_medico", schema = "public")
public class FormularioSepseMedicoEntity implements Serializable {
    private int idPaciente;
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

    @Id
    @Column(name = "id_formulario", nullable = false)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @Basic
    @Column(name = "id_paciente")
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Basic
    @Column(name = "foco_infeccioso", nullable = true, length = -1)
    public String getFocoInfeccioso() {
        return focoInfeccioso;
    }

    public void setFocoInfeccioso(String focoInfeccioso) {
        this.focoInfeccioso = focoInfeccioso;
    }

    @Basic
    @Column(name = "crm_medico", nullable = false)
    public int getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(int crmMedico) {
        this.crmMedico = crmMedico;
    }

    @Basic
    @Column(name = "crit_exclusao", nullable = true, length = -1)
    public String getCritExclusao() {
        return critExclusao;
    }

    public void setCritExclusao(String critExclusao) {
        this.critExclusao = critExclusao;
    }

    @Basic
    @Column(name = "bundle_hora1", nullable = true, length = -1)
    public String getBundleHora1() {
        return bundleHora1;
    }

    public void setBundleHora1(String bundleHora1) {
        this.bundleHora1 = bundleHora1;
    }

    @Basic
    @Column(name = "dt_disp_protocolo", nullable = true)
    public Date getDtDispProtocolo() {
        return dtDispProtocolo;
    }

    public void setDtDispProtocolo(Date dtDispProtocolo) {
        this.dtDispProtocolo = dtDispProtocolo;
    }

    @Basic
    @Column(name = "dt_coleta_lactato", nullable = true)
    public Date getDtColetaLactato() {
        return dtColetaLactato;
    }

    public void setDtColetaLactato(Date dtColetaLactato) {
        this.dtColetaLactato = dtColetaLactato;
    }

    @Basic
    @Column(name = "dt_coleta_hemocult", nullable = true)
    public Date getDtColetaHemocult() {
        return dtColetaHemocult;
    }

    public void setDtColetaHemocult(Date dtColetaHemocult) {
        this.dtColetaHemocult = dtColetaHemocult;
    }

    @Basic
    @Column(name = "dt_primeira_dose", nullable = true)
    public Date getDtPrimeiraDose() {
        return dtPrimeiraDose;
    }

    public void setDtPrimeiraDose(Date dtPrimeiraDose) {
        this.dtPrimeiraDose = dtPrimeiraDose;
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

    @Basic
    @Column(name = "reavaliacoes_seriadas", nullable = true, length = -1)
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
}
