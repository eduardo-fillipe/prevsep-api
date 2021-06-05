package br.ufs.hu.prevsep.web.api.dto.medic;

import br.ufs.hu.prevsep.web.api.dto.usuario.StatusUsuarioEnum;

public class MedicoResponseDTO {

    private String cpf;
    private String nome;
    private Integer crm;
    private StatusUsuarioEnum statusUsuario;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public StatusUsuarioEnum getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(StatusUsuarioEnum statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
}
