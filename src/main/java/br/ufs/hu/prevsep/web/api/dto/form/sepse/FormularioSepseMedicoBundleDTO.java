package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import java.time.LocalDateTime;


public class FormularioSepseMedicoBundleDTO {
    private boolean iniciado;
    private LocalDateTime dtDisparo;
    private LocalDateTime lactoDtColeta;
    private LocalDateTime hemoculturaDtColeta;
    private LocalDateTime antibioticoAmploAspectro;
    private boolean cristaloides;
    private boolean vasopressores;
    private String justificativaNao;


    public boolean getIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }


    public boolean isIniciado() {
        return iniciado;
    }

    public LocalDateTime getDtDisparo() {
        return dtDisparo;
    }

    public void setDtDisparo(LocalDateTime dtDisparo) {
        this.dtDisparo = dtDisparo;
    }

    public LocalDateTime getLactoDtColeta() {
        return lactoDtColeta;
    }

    public void setLactoDtColeta(LocalDateTime lactoDtColeta) {
        this.lactoDtColeta = lactoDtColeta;
    }

    public LocalDateTime getHemoculturaDtColeta() {
        return hemoculturaDtColeta;
    }

    public void setHemoculturaDtColeta(LocalDateTime hemoculturaDtColeta) {
        this.hemoculturaDtColeta = hemoculturaDtColeta;
    }

    public LocalDateTime getAntibioticoAmploAspectro() {
        return antibioticoAmploAspectro;
    }

    public void setAntibioticoAmploAspectro(LocalDateTime antibioticoAmploAspectro) {
        this.antibioticoAmploAspectro = antibioticoAmploAspectro;
    }

    public boolean isCristaloides() {
        return cristaloides;
    }

    public boolean isVasopressores() {
        return vasopressores;
    }

    public boolean getCristaloides() {
        return cristaloides;
    }

    public void setCristaloides(boolean cristaloides) {
        this.cristaloides = cristaloides;
    }


    public boolean getVasopressores() {
        return vasopressores;
    }

    public void setVasopressores(boolean vasopressores) {
        this.vasopressores = vasopressores;
    }


    public String getJustificativaNao() {
        return justificativaNao;
    }

    public void setJustificativaNao(String justificativaNao) {
        this.justificativaNao = justificativaNao;
    }
}
