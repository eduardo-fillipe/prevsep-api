package br.ufs.hu.prevsep.web.api.dto.form.sepse;


public class DoctorFormUpdateDTO {
    private FormularioSepseMedicoFocoInfecciosoDTO focoInfeccioso;
    private FormularioSepseMedicoCriterioExclusaoDTO criterioExclusao;
    private FormularioSepseMedicoBundleDTO bundleHora1;
    private FormularioSepseMedicoReavaliacoesSeriadasDTO reavaliacoesSeriadas;

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

    public FormularioSepseMedicoBundleDTO getBundleHora1() {
        return bundleHora1;
    }

    public void setBundleHora1(FormularioSepseMedicoBundleDTO bundleHora1) {
        this.bundleHora1 = bundleHora1;
    }

    public FormularioSepseMedicoReavaliacoesSeriadasDTO getReavaliacoesSeriadas() {
        return reavaliacoesSeriadas;
    }

    public void setReavaliacoesSeriadas(FormularioSepseMedicoReavaliacoesSeriadasDTO reavaliacoesSeriadas) {
        this.reavaliacoesSeriadas = reavaliacoesSeriadas;
    }
}
