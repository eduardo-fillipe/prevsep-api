package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "usuario")
public class UsuarioEntity implements Serializable {
    @Id
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cargo")
    private Integer cargo;

    @Column(name = "status")
    private Integer status;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCargo() {
        return cargo;
    }

    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public Integer getStatus() {
        return status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
