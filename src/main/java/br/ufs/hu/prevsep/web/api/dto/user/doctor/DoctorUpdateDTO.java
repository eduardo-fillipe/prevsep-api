package br.ufs.hu.prevsep.web.api.dto.user.doctor;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class DoctorUpdateDTO {
    @NotNull(message = "CRM não pode ser nulo")
    @Range(min = 1, message = "Valor mínimo é de 1")
    private Integer crm;

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }
}
