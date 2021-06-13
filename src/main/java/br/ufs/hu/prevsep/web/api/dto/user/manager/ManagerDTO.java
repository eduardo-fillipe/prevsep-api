package br.ufs.hu.prevsep.web.api.dto.user.manager;

import br.ufs.hu.prevsep.web.api.dto.user.usuario.StatusUsuarioEnum;

public class ManagerDTO {
    private String nome;
    private String cpf;
    private StatusUsuarioEnum statusUsuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public StatusUsuarioEnum getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(StatusUsuarioEnum statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
}
