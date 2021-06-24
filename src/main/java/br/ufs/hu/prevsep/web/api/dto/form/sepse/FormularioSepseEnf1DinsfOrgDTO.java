package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import javax.persistence.*;
import java.util.Objects;

public class FormularioSepseEnf1DinsfOrgDTO {
    private Boolean diurese;
    private Boolean hipotensao;
    private Boolean snlcConfAgtcComa;
    private Boolean saturacaoDispneia;

    public Boolean getDiurese() {
        return diurese;
    }

    public void setDiurese(Boolean diurese) {
        this.diurese = diurese;
    }

    public Boolean getHipotensao() {
        return hipotensao;
    }

    public void setHipotensao(Boolean hipotensao) {
        this.hipotensao = hipotensao;
    }

    public Boolean getSnlcConfAgtcComa() {
        return snlcConfAgtcComa;
    }

    public void setSnlcConfAgtcComa(Boolean snlcConfAgtcComa) {
        this.snlcConfAgtcComa = snlcConfAgtcComa;
    }

    public Boolean getSaturacaoDispneia() {
        return saturacaoDispneia;
    }

    public void setSaturacaoDispneia(Boolean saturacaoDispneia) {
        this.saturacaoDispneia = saturacaoDispneia;
    }
}
