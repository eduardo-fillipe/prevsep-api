package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "formulario_sepse_medico", schema = "public")
public class FormularioSepseMedicoEntity implements Serializable {
    private int idPaciente;
    private PacienteEntity pacienteEntity;
    private int crmMedico;
    private int idFormulario;
    private Date dtCriacao;
    private int status;
    private FormularioSepseMedicoFocoInfecciosoEntity focoInfeccioso;
    private FormularioSepseMedicoCriterioExclusaoEntity criterioExclusao;
    private FormularioSepseMedicoBundleEntity bundleHora1;
    private FormularioSepseMedicoReavaliacoesSeriadasEntity reavaliacoesSeriadas;

    @Id
    @Column(name = "id_formulario", nullable = false)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente", insertable = false, updatable = false)
    public PacienteEntity getPacienteEntity() {
        return pacienteEntity;
    }

    public void setPacienteEntity(PacienteEntity pacienteEntity) {
        this.pacienteEntity = pacienteEntity;
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
    @Column(name = "crm_medico", nullable = false)
    public int getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(int crmMedico) {
        this.crmMedico = crmMedico;
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "foco_infeccioso_id", referencedColumnName = "id")
    public FormularioSepseMedicoFocoInfecciosoEntity getFocoInfeccioso() {
        return focoInfeccioso;
    }

    public void setFocoInfeccioso(FormularioSepseMedicoFocoInfecciosoEntity focoInfeccioso) {
        this.focoInfeccioso = focoInfeccioso;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "crit_exclusao_id", referencedColumnName = "id")
    public FormularioSepseMedicoCriterioExclusaoEntity getCriterioExclusao() {
        return criterioExclusao;
    }

    public void setCriterioExclusao(FormularioSepseMedicoCriterioExclusaoEntity criterioExclusao) {
        this.criterioExclusao = criterioExclusao;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bundle_id", referencedColumnName = "id")
    public FormularioSepseMedicoBundleEntity getBundleHora1() {
        return bundleHora1;
    }

    public void setBundleHora1(FormularioSepseMedicoBundleEntity bundleHora1) {
        this.bundleHora1 = bundleHora1;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "reavaliacao_seriada_id", referencedColumnName = "id")
    public FormularioSepseMedicoReavaliacoesSeriadasEntity getReavaliacoesSeriadas() {
        return reavaliacoesSeriadas;
    }

    public void setReavaliacoesSeriadas(FormularioSepseMedicoReavaliacoesSeriadasEntity reavaliacoesSeriadas) {
        this.reavaliacoesSeriadas = reavaliacoesSeriadas;
    }
}
