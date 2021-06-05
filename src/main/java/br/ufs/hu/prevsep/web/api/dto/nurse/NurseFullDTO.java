package br.ufs.hu.prevsep.web.api.dto.nurse;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NurseFullDTO {
    @NotEmpty(message = "Name can not be null")
    private String nome;
    @NotNull(message = "CRE can not be null")
    private Integer cre;
    @NotNull
    @Valid
    private UsuarioResponseDTO userInfo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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
