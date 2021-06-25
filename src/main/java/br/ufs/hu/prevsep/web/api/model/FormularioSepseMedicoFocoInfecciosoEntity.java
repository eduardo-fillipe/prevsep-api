package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "formulario_sepse_medico_foco_infeccioso", schema = "public")
public class FormularioSepseMedicoFocoInfecciosoEntity {
    private int idFormulario;
    private boolean pneumoniaEmpema;
    private boolean infeccaoUrinaria;
    private boolean infeccaoAbdominal;
    private boolean menigite;
    private boolean endocardite;
    private boolean pelePartesMoles;
    private boolean infeccaoProtese;
    private boolean infeccaoOssea;
    private boolean infeccaoFeridaOperatoria;
    private boolean infeccaoSanguineaCateter;
    private boolean semFocoDefinido;
    private String outrasInfeccoes;

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
    @Column(name = "pneumonia_empema", nullable = true)
    public boolean getPneumoniaEmpema() {
        return pneumoniaEmpema;
    }

    public void setPneumoniaEmpema(boolean pneumoniaEmpema) {
        this.pneumoniaEmpema = pneumoniaEmpema;
    }

    @Basic
    @Column(name = "infeccao_urinaria", nullable = true)
    public boolean getInfeccaoUrinaria() {
        return infeccaoUrinaria;
    }

    public void setInfeccaoUrinaria(boolean infeccaoUrinaria) {
        this.infeccaoUrinaria = infeccaoUrinaria;
    }

    @Basic
    @Column(name = "infeccao_abdominal", nullable = true)
    public boolean getInfeccaoAbdominal() {
        return infeccaoAbdominal;
    }

    public void setInfeccaoAbdominal(boolean infeccaoAbdominal) {
        this.infeccaoAbdominal = infeccaoAbdominal;
    }

    @Basic
    @Column(name = "menigite", nullable = true)
    public boolean getMenigite() {
        return menigite;
    }

    public void setMenigite(boolean menigite) {
        this.menigite = menigite;
    }

    @Basic
    @Column(name = "endocardite", nullable = true)
    public boolean getEndocardite() {
        return endocardite;
    }

    public void setEndocardite(boolean endocardite) {
        this.endocardite = endocardite;
    }

    @Basic
    @Column(name = "pele_partes_moles", nullable = true)
    public boolean getPelePartesMoles() {
        return pelePartesMoles;
    }

    public void setPelePartesMoles(boolean pelePartesMoles) {
        this.pelePartesMoles = pelePartesMoles;
    }

    @Basic
    @Column(name = "infeccao_protese", nullable = true)
    public boolean getInfeccaoProtese() {
        return infeccaoProtese;
    }

    public void setInfeccaoProtese(boolean infeccaoProtese) {
        this.infeccaoProtese = infeccaoProtese;
    }

    @Basic
    @Column(name = "infeccao_ossea", nullable = true)
    public boolean getInfeccaoOssea() {
        return infeccaoOssea;
    }

    public void setInfeccaoOssea(boolean infeccaoOssea) {
        this.infeccaoOssea = infeccaoOssea;
    }

    @Basic
    @Column(name = "infeccao_ferida_operatoria", nullable = true)
    public boolean getInfeccaoFeridaOperatoria() {
        return infeccaoFeridaOperatoria;
    }

    public void setInfeccaoFeridaOperatoria(boolean infeccaoFeridaOperatoria) {
        this.infeccaoFeridaOperatoria = infeccaoFeridaOperatoria;
    }

    @Basic
    @Column(name = "infeccao_sanguinea_cateter", nullable = true)
    public boolean getInfeccaoSanguineaCateter() {
        return infeccaoSanguineaCateter;
    }

    public void setInfeccaoSanguineaCateter(boolean infeccaoSanguineaCateter) {
        this.infeccaoSanguineaCateter = infeccaoSanguineaCateter;
    }

    @Basic
    @Column(name = "sem_foco_definido", nullable = true)
    public boolean getSemFocoDefinido() {
        return semFocoDefinido;
    }

    public void setSemFocoDefinido(boolean semFocoDefinido) {
        this.semFocoDefinido = semFocoDefinido;
    }

    @Basic
    @Column(name = "outras_infeccoes", nullable = true, length = -1)
    public String getOutrasInfeccoes() {
        return outrasInfeccoes;
    }

    public void setOutrasInfeccoes(String outrasInfeccoes) {
        this.outrasInfeccoes = outrasInfeccoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseMedicoFocoInfecciosoEntity that = (FormularioSepseMedicoFocoInfecciosoEntity) o;
        return idFormulario == that.idFormulario && Objects.equals(pneumoniaEmpema, that.pneumoniaEmpema) && Objects.equals(infeccaoUrinaria, that.infeccaoUrinaria) && Objects.equals(infeccaoAbdominal, that.infeccaoAbdominal) && Objects.equals(menigite, that.menigite) && Objects.equals(endocardite, that.endocardite) && Objects.equals(pelePartesMoles, that.pelePartesMoles) && Objects.equals(infeccaoProtese, that.infeccaoProtese) && Objects.equals(infeccaoOssea, that.infeccaoOssea) && Objects.equals(infeccaoFeridaOperatoria, that.infeccaoFeridaOperatoria) && Objects.equals(infeccaoSanguineaCateter, that.infeccaoSanguineaCateter) && Objects.equals(semFocoDefinido, that.semFocoDefinido) && Objects.equals(outrasInfeccoes, that.outrasInfeccoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, pneumoniaEmpema, infeccaoUrinaria, infeccaoAbdominal, menigite, endocardite, pelePartesMoles, infeccaoProtese, infeccaoOssea, infeccaoFeridaOperatoria, infeccaoSanguineaCateter, semFocoDefinido, outrasInfeccoes);
    }
}
