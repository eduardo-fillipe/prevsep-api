package br.ufs.hu.prevsep.web.api.dto.manager;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ManagerRequestDTO {
    @Valid
    @NotNull
    private UsuarioRequestDTO userInfo;

    public UsuarioRequestDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioRequestDTO userInfo) {
        this.userInfo = userInfo;
    }
}
