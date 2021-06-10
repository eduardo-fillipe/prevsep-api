package br.ufs.hu.prevsep.web.api.dto.doctor;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorResponseFullDTO {
    @NotNull(message = "CRM can not be null")
    private Integer crm;
    @NotNull
    @Valid
    private UsuarioResponseDTO userInfo;

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public UsuarioResponseDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioResponseDTO userInfo) {
        this.userInfo = userInfo;
    }
}
