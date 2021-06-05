package br.ufs.hu.prevsep.web.api.dto.usuario;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UsuarioRequestDTO {
    @CPF(message = "Not a valid CPF")
    @NotEmpty
    private String cpf;
    @NotEmpty(message = "E-mail can't be null")
    @Email(message = "Invalid e-mail")
    private String email;
    @NotEmpty(message = "Password can not be empty")
    @Length(min = 8, max = 32)
    private String senha;
    @NotNull
    private CargoEnum cargo;
    private StatusUsuarioEnum status;

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

    public CargoEnum getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnum cargo) {
        this.cargo = cargo;
    }

    public StatusUsuarioEnum getStatus() {
        return status;
    }

    public void setStatus(StatusUsuarioEnum status) {
        this.status = status;
    }
}
