package br.ufs.hu.prevsep.web.api.dto.user.nurse;

import br.ufs.hu.prevsep.web.api.dto.user.usuario.UsuarioDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NurseFullDTO {
    @NotNull(message = "CRE não pode ser nulo")
    private Integer cre;
    @NotNull
    @Valid
    private UsuarioDTO userInfo;

    public Integer getCre() {
        return cre;
    }

    public void setCre(Integer cre) {
        this.cre = cre;
    }

    public UsuarioDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioDTO userInfo) {
        this.userInfo = userInfo;
    }
}
