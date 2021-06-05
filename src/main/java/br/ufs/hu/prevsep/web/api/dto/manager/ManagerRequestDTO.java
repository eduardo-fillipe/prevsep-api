package br.ufs.hu.prevsep.web.api.dto.manager;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ManagerRequestDTO {
    @NotEmpty(message = "Nome cant be empty.")
    private String nome;
    @Valid
    @NotNull
    private UsuarioRequestDTO userInfo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioRequestDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioRequestDTO userInfo) {
        this.userInfo = userInfo;
    }
}
