package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import java.sql.Timestamp;


public class FormularioSepseMedicoBundleDTO {
    private boolean iniciado;
    private Timestamp dtDisparo;
    private Timestamp lactoDtColeta;
    private Timestamp hemoculturaDtColeta;
    private Timestamp antibioticoAmploAspectro;
    private boolean cristaloides;
    private boolean vasopressores;
    private String justificativaNao;


    public boolean getIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }


    public Timestamp getDtDisparo() {
        return dtDisparo;
    }

    public void setDtDisparo(Timestamp dtDisparo) {
        this.dtDisparo = dtDisparo;
    }


    public Timestamp getLactoDtColeta() {
        return lactoDtColeta;
    }

    public void setLactoDtColeta(Timestamp lactoDtColeta) {
        this.lactoDtColeta = lactoDtColeta;
    }


    public Timestamp getHemoculturaDtColeta() {
        return hemoculturaDtColeta;
    }

    public void setHemoculturaDtColeta(Timestamp hemoculturaDtColeta) {
        this.hemoculturaDtColeta = hemoculturaDtColeta;
    }


    public Timestamp getAntibioticoAmploAspectro() {
        return antibioticoAmploAspectro;
    }

    public void setAntibioticoAmploAspectro(Timestamp antibioticoAmploAspectro) {
        this.antibioticoAmploAspectro = antibioticoAmploAspectro;
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
