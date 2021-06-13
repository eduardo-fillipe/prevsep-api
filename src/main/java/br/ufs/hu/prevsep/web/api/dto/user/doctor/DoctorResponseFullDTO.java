package br.ufs.hu.prevsep.web.api.dto.user.doctor;

import br.ufs.hu.prevsep.web.api.dto.user.usuario.UsuarioDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DoctorResponseFullDTO {
    @NotNull(message = "CRM can not be null")
    private Integer crm;
    @NotNull
    @Valid
    private UsuarioDTO userInfo;

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public UsuarioDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioDTO userInfo) {
        this.userInfo = userInfo;
    }
}
