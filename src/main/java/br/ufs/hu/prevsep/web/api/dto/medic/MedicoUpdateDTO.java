package br.ufs.hu.prevsep.web.api.dto.medic;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class MedicoUpdateDTO {
    @NotEmpty(message = "Name can not be null")
    private String nome;
    @NotEmpty(message = "CRM can not be null")
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
