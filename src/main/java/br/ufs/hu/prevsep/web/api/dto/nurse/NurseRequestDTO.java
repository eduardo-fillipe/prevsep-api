package br.ufs.hu.prevsep.web.api.dto.nurse;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NurseRequestDTO {
    @NotEmpty(message = "Name can not be null")
    private String nome;
    @NotNull(message = "CRE can not be null")
    private Integer cre;
    @NotNull
    @Valid
    private UsuarioRequestDTO userInfo;

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

    public UsuarioRequestDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioRequestDTO userInfo) {
        this.userInfo = userInfo;
    }
}
