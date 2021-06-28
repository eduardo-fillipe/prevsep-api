package br.ufs.hu.prevsep.web.api.dto.form.sepse;

public class FormularioSepseMedicoCriterioExclusaoDTO {
    private boolean apresentaCriterioExclusao;
    private boolean fimDeVida;
    private boolean doencaAtipica;
    private boolean probabilidadeSepseBaixa;

    public boolean getApresentaCriterioExclusao() {
        return apresentaCriterioExclusao;
    }

    public void setApresentaCriterioExclusao(boolean apresentaCriterioExclusao) {
        this.apresentaCriterioExclusao = apresentaCriterioExclusao;
    }


    public boolean getFimDeVida() {
        return fimDeVida;
    }

    public void setFimDeVida(boolean fimDeVida) {
        this.fimDeVida = fimDeVida;
    }


    public boolean getDoencaAtipica() {
        return doencaAtipica;
    }

    public void setDoencaAtipica(boolean doencaAtipica) {
        this.doencaAtipica = doencaAtipica;
    }


    public boolean getProbabilidadeSepseBaixa() {
        return probabilidadeSepseBaixa;
    }

    public void setProbabilidadeSepseBaixa(boolean probabilidadeSepseBaixa) {
        this.probabilidadeSepseBaixa = probabilidadeSepseBaixa;
    }
}
