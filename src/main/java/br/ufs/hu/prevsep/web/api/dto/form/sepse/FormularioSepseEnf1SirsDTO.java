package br.ufs.hu.prevsep.web.api.dto.form.sepse;

public class FormularioSepseEnf1SirsDTO {
    private Boolean febreHipotemia;
    private Boolean leucocitoseLeucopenia;
    private Boolean taquicardia;
    private Boolean taquipneia;

    public Boolean getFebreHipotemia() {
        return febreHipotemia;
    }

    public void setFebreHipotemia(Boolean febreHipotemia) {
        this.febreHipotemia = febreHipotemia;
    }

    public Boolean getLeucocitoseLeucopenia() {
        return leucocitoseLeucopenia;
    }

    public void setLeucocitoseLeucopenia(Boolean leucocitoseLeucopenia) {
        this.leucocitoseLeucopenia = leucocitoseLeucopenia;
    }

    public Boolean getTaquicardia() {
        return taquicardia;
    }

    public void setTaquicardia(Boolean taquicardia) {
        this.taquicardia = taquicardia;
    }

    public Boolean getTaquipneia() {
        return taquipneia;
    }

    public void setTaquipneia(Boolean taquipneia) {
        this.taquipneia = taquipneia;
    }
}
