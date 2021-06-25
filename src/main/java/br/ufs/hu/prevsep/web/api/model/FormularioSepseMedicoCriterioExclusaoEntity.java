package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "formulario_sepse_medico_criterio_exclusao", schema = "public")
public class FormularioSepseMedicoCriterioExclusaoEntity {
    private int idFormulario;
    private boolean apresentaCriterioExclusao;
    private boolean fimDeVida;
    private boolean doencaAtipica;
    private boolean probabilidadeSepseBaixa;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @Basic
    @Column(name = "apresenta_criterio_exclusao", nullable = true)
    public boolean getApresentaCriterioExclusao() {
        return apresentaCriterioExclusao;
    }

    public void setApresentaCriterioExclusao(boolean apresentaCriterioExclusao) {
        this.apresentaCriterioExclusao = apresentaCriterioExclusao;
    }

    @Basic
    @Column(name = "fim_de_vida", nullable = true)
    public boolean getFimDeVida() {
        return fimDeVida;
    }

    public void setFimDeVida(boolean fimDeVida) {
        this.fimDeVida = fimDeVida;
    }

    @Basic
    @Column(name = "doenca_atipica", nullable = true)
    public boolean getDoencaAtipica() {
        return doencaAtipica;
    }

    public void setDoencaAtipica(boolean doencaAtipica) {
        this.doencaAtipica = doencaAtipica;
    }

    @Basic
    @Column(name = "probabilidade_sepse_baixa", nullable = true)
    public boolean getProbabilidadeSepseBaixa() {
        return probabilidadeSepseBaixa;
    }

    public void setProbabilidadeSepseBaixa(boolean probabilidadeSepseBaixa) {
        this.probabilidadeSepseBaixa = probabilidadeSepseBaixa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseMedicoCriterioExclusaoEntity that = (FormularioSepseMedicoCriterioExclusaoEntity) o;
        return idFormulario == that.idFormulario && Objects.equals(apresentaCriterioExclusao, that.apresentaCriterioExclusao) && Objects.equals(fimDeVida, that.fimDeVida) && Objects.equals(doencaAtipica, that.doencaAtipica) && Objects.equals(probabilidadeSepseBaixa, that.probabilidadeSepseBaixa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, apresentaCriterioExclusao, fimDeVida, doencaAtipica, probabilidadeSepseBaixa);
    }
}
