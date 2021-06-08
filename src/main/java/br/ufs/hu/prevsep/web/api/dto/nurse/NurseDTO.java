package br.ufs.hu.prevsep.web.api.dto.nurse;

import br.ufs.hu.prevsep.web.api.dto.usuario.StatusUsuarioEnum;

public class NurseDTO {

    private String cpf;
    private String nome;
    private Integer cre;
    private StatusUsuarioEnum statusUsuario;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public StatusUsuarioEnum getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(StatusUsuarioEnum statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
}
