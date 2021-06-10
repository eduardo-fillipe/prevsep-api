package br.ufs.hu.prevsep.web.api.dto.nurse;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NurseFullDTO {
    @NotNull(message = "CRE can not be null")
    private Integer cre;
    @NotNull
    @Valid
    private UsuarioResponseDTO userInfo;

    public Integer getCre() {
        return cre;
    }

    public void setCre(Integer cre) {
        this.cre = cre;
    }

    public UsuarioResponseDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioResponseDTO userInfo) {
        this.userInfo = userInfo;
    }
}
