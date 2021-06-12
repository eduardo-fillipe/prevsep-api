package br.ufs.hu.prevsep.web.api.dto.manager;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioDTO;

public class ManagerFullDTO {
    private UsuarioDTO userInfo;

    public UsuarioDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioDTO userInfo) {
        this.userInfo = userInfo;
    }
}
