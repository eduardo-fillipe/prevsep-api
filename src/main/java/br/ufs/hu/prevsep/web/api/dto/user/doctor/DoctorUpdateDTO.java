package br.ufs.hu.prevsep.web.api.dto.user.doctor;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class DoctorUpdateDTO {
    @NotNull(message = "CRM can not be null")
    @Range(min = 1, message = "Minimum value is 1")
    private Integer crm;

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }
}
