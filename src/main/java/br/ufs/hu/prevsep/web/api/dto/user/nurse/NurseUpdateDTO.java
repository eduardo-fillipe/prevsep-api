package br.ufs.hu.prevsep.web.api.dto.user.nurse;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class NurseUpdateDTO {
    @NotNull(message = "CRE can not be null")
    @Range(min = 1, message = "Minimum value is 1")
    private Integer cre;

    public Integer getCre() {
        return cre;
    }

    public void setCre(Integer cre) {
        this.cre = cre;
    }

}
