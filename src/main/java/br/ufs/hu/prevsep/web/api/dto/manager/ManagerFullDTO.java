package br.ufs.hu.prevsep.web.api.dto.manager;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;

public class ManagerFullDTO {
    private String nome;
    private UsuarioResponseDTO userInfo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioResponseDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioResponseDTO userInfo) {
        this.userInfo = userInfo;
    }
}
