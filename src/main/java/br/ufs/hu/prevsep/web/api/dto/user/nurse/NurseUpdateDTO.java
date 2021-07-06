package br.ufs.hu.prevsep.web.api.dto.user.nurse;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class NurseUpdateDTO {
    @NotNull(message = "CRE não pode ser nulo")
    @Range(min = 1, message = "Valo mínimo é de 1")
    private Integer cre;

    public Integer getCre() {
        return cre;
    }

    public void setCre(Integer cre) {
        this.cre = cre;
    }

}
