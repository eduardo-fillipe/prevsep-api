package br.ufs.hu.prevsep.web.api.dto.doctor;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorUpdateDTO {
    @NotEmpty(message = "Name can not be null")
    private String nome;
    @NotNull(message = "CRM can not be null")
    @Range(min = 1, message = "Minimum value is 1")
    private Integer crm;
    @NotEmpty
    @Length(min = 11, max = 11)
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
