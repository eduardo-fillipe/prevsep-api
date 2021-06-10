package br.ufs.hu.prevsep.web.api.dto.doctor;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorRequestDTO {
    @NotNull(message = "CRM can not be null")
    private Integer crm;
    @NotNull
    @Valid
    private UsuarioRequestDTO userInfo;

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public UsuarioRequestDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioRequestDTO userInfo) {
        this.userInfo = userInfo;
    }
}
