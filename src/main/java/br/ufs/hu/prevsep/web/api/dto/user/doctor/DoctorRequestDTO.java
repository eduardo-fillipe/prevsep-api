package br.ufs.hu.prevsep.web.api.dto.user.doctor;

import br.ufs.hu.prevsep.web.api.dto.user.usuario.UsuarioRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DoctorRequestDTO {
    @NotNull(message = "CRM não pode ser nulo")
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
