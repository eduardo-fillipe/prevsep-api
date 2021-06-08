package br.ufs.hu.prevsep.web.api.dto.nurse;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NurseUpdateDTO {
    @NotEmpty(message = "Name can not be null")
    private String nome;
    @NotNull(message = "CRE can not be null")
    @Range(min = 1, message = "Minimum value is 1")
    private Integer cre;

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

}
