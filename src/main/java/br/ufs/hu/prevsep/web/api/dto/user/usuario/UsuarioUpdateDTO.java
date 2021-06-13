package br.ufs.hu.prevsep.web.api.dto.user.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioUpdateDTO {
    @Email(message = "Invalid e-mail")
    private String email;

    @Size(min = 1, max = 255)
    private String nome;

    @Size(min = 8, max = 32)
    @Pattern(regexp = "(.*([a-z]|[A-Z]).*)", message = "Must have at least 1 letter.")
    @Pattern(regexp = "(.*[@#$%^&+=].*)", message = "Must have at least 1 special character.")
    @Pattern(regexp = "(.*[0-9].*)", message = "Must have at least 1 number.")
    @Pattern(regexp = "(^[^\\s]*$)", message = "Cannot contain blank spaces.")
    private String senha;

    private CargoEnum cargo;
    private StatusUsuarioEnum status;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(StatusUsuarioEnum status) {
        this.status = status;
    }
}
