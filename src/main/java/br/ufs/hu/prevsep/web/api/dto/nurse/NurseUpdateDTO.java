package br.ufs.hu.prevsep.web.api.dto.nurse;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class NurseUpdateDTO {
    @NotEmpty(message = "Name can not be null")
    private String nome;
    @NotEmpty(message = "CRE can not be null")
    private Integer cre;
    @NotEmpty
    @Length(min = 11, max = 11)
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCre() {
        return cre;
    }

    public void setCre(Integer cre) {
        this.cre = cre;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
