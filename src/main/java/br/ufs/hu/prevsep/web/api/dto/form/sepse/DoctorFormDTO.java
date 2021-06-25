package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;

import java.time.LocalDate;

public class DoctorFormDTO {
    private int idFormulario;
    private PatientDTO paciente;
    private int crmMedico;
    private LocalDate dtCriacao;
    private FormStatus status;
    private FormularioSepseMedicoFocoInfecciosoDTO focoInfeccioso;
    private FormularioSepseMedicoCriterioExclusaoDTO criterioExclusao;
    private FormularioSepseMedicoBundleDTO bundleHora1;
    private FormularioSepseMedicoReavaliacoesSeriadasDTO reavaliacoesSeriadas;

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

    public int getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(int crmMedico) {
        this.crmMedico = crmMedico;
    }

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDate dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public FormularioSepseMedicoBundleDTO getBundleHora1() {
        return bundleHora1;
    }

    public void setBundleHora1(FormularioSepseMedicoBundleDTO bundleHora1) {
        this.bundleHora1 = bundleHora1;
    }

    public FormularioSepseMedicoFocoInfecciosoDTO getFocoInfeccioso() {
        return focoInfeccioso;
    }

    public void setFocoInfeccioso(FormularioSepseMedicoFocoInfecciosoDTO focoInfeccioso) {
        this.focoInfeccioso = focoInfeccioso;
    }

    public FormularioSepseMedicoCriterioExclusaoDTO getCriterioExclusao() {
        return criterioExclusao;
    }

    public void setCriterioExclusao(FormularioSepseMedicoCriterioExclusaoDTO criterioExclusao) {
        this.criterioExclusao = criterioExclusao;
    }

    public FormularioSepseMedicoReavaliacoesSeriadasDTO getReavaliacoesSeriadas() {
        return reavaliacoesSeriadas;
    }

    public void setReavaliacoesSeriadas(FormularioSepseMedicoReavaliacoesSeriadasDTO reavaliacoesSeriadas) {
        this.reavaliacoesSeriadas = reavaliacoesSeriadas;
    }
}
