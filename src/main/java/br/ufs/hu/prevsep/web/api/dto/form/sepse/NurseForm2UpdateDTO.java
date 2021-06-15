package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import java.time.LocalDate;

public class NurseForm2UpdateDTO {
    private LocalDate dtUti;
    private LocalDate dtAlta;
    private LocalDate dtObito;

    public LocalDate getDtUti() {
        return dtUti;
    }

    public void setDtUti(LocalDate dtUti) {
        this.dtUti = dtUti;
    }

    public LocalDate getDtAlta() {
        return dtAlta;
    }

    public void setDtAlta(LocalDate dtAlta) {
        this.dtAlta = dtAlta;
    }

    public LocalDate getDtObito() {
        return dtObito;
    }

    public void setDtObito(LocalDate dtObito) {
        this.dtObito = dtObito;
    }
}
