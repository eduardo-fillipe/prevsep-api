package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import java.sql.Timestamp;
import java.time.LocalDate;

public class NurseForm2UpdateDTO {
    private Timestamp dtUti;
    private Timestamp dtAlta;
    private Timestamp dtObito;

    public Timestamp getDtUti() {
        return dtUti;
    }

    public void setDtUti(Timestamp dtUti) {
        this.dtUti = dtUti;
    }

    public Timestamp getDtAlta() {
        return dtAlta;
    }

    public void setDtAlta(Timestamp dtAlta) {
        this.dtAlta = dtAlta;
    }

    public Timestamp getDtObito() {
        return dtObito;
    }

    public void setDtObito(Timestamp dtObito) {
        this.dtObito = dtObito;
    }
}
