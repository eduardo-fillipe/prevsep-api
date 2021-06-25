package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import java.util.Objects;

public class FormularioSepseMedicoReavaliacoesSeriadasDTO {
    private boolean qSofa;
    private boolean pas100Mmghg;
    private boolean fr22Rpm;
    private boolean rebaixamentoNivelConsiencia;
    private boolean lactoInicialmenteAlto;
    private String outros;
    private String justificativaNao;
    private boolean aplicada;

    public boolean getqSofa() {
        return qSofa;
    }

    public void setqSofa(boolean qSofa) {
        this.qSofa = qSofa;
    }


    public boolean getPas100Mmghg() {
        return pas100Mmghg;
    }

    public void setPas100Mmghg(boolean pas100Mmghg) {
        this.pas100Mmghg = pas100Mmghg;
    }

    public boolean getFr22Rpm() {
        return fr22Rpm;
    }

    public void setFr22Rpm(boolean fr22Rpm) {
        this.fr22Rpm = fr22Rpm;
    }

    public boolean getRebaixamentoNivelConsiencia() {
        return rebaixamentoNivelConsiencia;
    }

    public void setRebaixamentoNivelConsiencia(boolean rebaixamentoNivelConsiencia) {
        this.rebaixamentoNivelConsiencia = rebaixamentoNivelConsiencia;
    }

    public boolean getLactoInicialmenteAlto() {
        return lactoInicialmenteAlto;
    }

    public void setLactoInicialmenteAlto(boolean lactoInicialmenteAlto) {
        this.lactoInicialmenteAlto = lactoInicialmenteAlto;
    }


    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }


    public String getJustificativaNao() {
        return justificativaNao;
    }

    public void setJustificativaNao(String justificativaNao) {
        this.justificativaNao = justificativaNao;
    }


    public boolean getAplicada() {
        return aplicada;
    }

    public void setAplicada(boolean aplicada) {
        this.aplicada = aplicada;
    }
}
