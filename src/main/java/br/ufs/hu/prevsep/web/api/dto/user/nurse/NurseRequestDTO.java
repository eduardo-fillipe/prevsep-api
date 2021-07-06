package br.ufs.hu.prevsep.web.api.dto.user.nurse;

import br.ufs.hu.prevsep.web.api.dto.user.usuario.UsuarioRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NurseRequestDTO {
    @NotNull(message = "CRE não pode ser nulo")
    private Integer cre;
    @NotNull
    @Valid
    private UsuarioRequestDTO userInfo;

    public Integer getCre() {
        return cre;
    }

    public void setCre(Integer cre) {
        this.cre = cre;
    }

    public UsuarioRequestDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioRequestDTO userInfo) {
        this.userInfo = userInfo;
    }
}
