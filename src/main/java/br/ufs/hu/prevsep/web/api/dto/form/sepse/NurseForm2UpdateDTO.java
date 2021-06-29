package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import java.time.LocalDateTime;

public class NurseForm2UpdateDTO {
    private LocalDateTime dtUti;
    private LocalDateTime dtAlta;
    private LocalDateTime dtObito;

    public LocalDateTime getDtUti() {
        return dtUti;
    }

    public void setDtUti(LocalDateTime dtUti) {
        this.dtUti = dtUti;
    }

    public LocalDateTime getDtAlta() {
        return dtAlta;
    }

    public void setDtAlta(LocalDateTime dtAlta) {
        this.dtAlta = dtAlta;
    }

    public LocalDateTime getDtObito() {
        return dtObito;
    }

    public void setDtObito(LocalDateTime dtObito) {
        this.dtObito = dtObito;
    }
}
