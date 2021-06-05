package br.ufs.hu.prevsep.web.api.dto.manager;

import javax.validation.constraints.NotEmpty;

public class ManagerUpdateDTO {
    @NotEmpty(message = "Nome cant be empty")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
