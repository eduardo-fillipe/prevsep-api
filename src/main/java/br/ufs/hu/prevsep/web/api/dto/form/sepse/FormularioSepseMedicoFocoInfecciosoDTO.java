package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import javax.persistence.*;
import java.util.Objects;

public class FormularioSepseMedicoFocoInfecciosoDTO {
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

    public boolean getPneumoniaEmpema() {
        return pneumoniaEmpema;
    }

    public void setPneumoniaEmpema(boolean pneumoniaEmpema) {
        this.pneumoniaEmpema = pneumoniaEmpema;
    }


    public boolean getInfeccaoUrinaria() {
        return infeccaoUrinaria;
    }

    public void setInfeccaoUrinaria(boolean infeccaoUrinaria) {
        this.infeccaoUrinaria = infeccaoUrinaria;
    }


    public boolean getInfeccaoAbdominal() {
        return infeccaoAbdominal;
    }

    public void setInfeccaoAbdominal(boolean infeccaoAbdominal) {
        this.infeccaoAbdominal = infeccaoAbdominal;
    }


    public boolean getMenigite() {
        return menigite;
    }

    public void setMenigite(boolean menigite) {
        this.menigite = menigite;
    }


    public boolean getEndocardite() {
        return endocardite;
    }

    public void setEndocardite(boolean endocardite) {
        this.endocardite = endocardite;
    }


    public boolean getPelePartesMoles() {
        return pelePartesMoles;
    }

    public void setPelePartesMoles(boolean pelePartesMoles) {
        this.pelePartesMoles = pelePartesMoles;
    }


    public boolean getInfeccaoProtese() {
        return infeccaoProtese;
    }

    public void setInfeccaoProtese(boolean infeccaoProtese) {
        this.infeccaoProtese = infeccaoProtese;
    }


    public boolean getInfeccaoOssea() {
        return infeccaoOssea;
    }

    public void setInfeccaoOssea(boolean infeccaoOssea) {
        this.infeccaoOssea = infeccaoOssea;
    }


    public boolean getInfeccaoFeridaOperatoria() {
        return infeccaoFeridaOperatoria;
    }

    public void setInfeccaoFeridaOperatoria(boolean infeccaoFeridaOperatoria) {
        this.infeccaoFeridaOperatoria = infeccaoFeridaOperatoria;
    }


    public boolean getInfeccaoSanguineaCateter() {
        return infeccaoSanguineaCateter;
    }

    public void setInfeccaoSanguineaCateter(boolean infeccaoSanguineaCateter) {
        this.infeccaoSanguineaCateter = infeccaoSanguineaCateter;
    }


    public boolean getSemFocoDefinido() {
        return semFocoDefinido;
    }

    public void setSemFocoDefinido(boolean semFocoDefinido) {
        this.semFocoDefinido = semFocoDefinido;
    }


    public String getOutrasInfeccoes() {
        return outrasInfeccoes;
    }

    public void setOutrasInfeccoes(String outrasInfeccoes) {
        this.outrasInfeccoes = outrasInfeccoes;
    }
}
