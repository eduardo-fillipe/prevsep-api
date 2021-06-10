package br.ufs.hu.prevsep.web.api.dto.usuario;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioRequestDTO {
    @CPF(message = "Not a valid CPF")
    @NotEmpty
    private String cpf;

    @NotEmpty(message = "Can not be empty")
    @Length(min = 1, max = 255, message = "Name length must be between 1 and 255.")
    private String nome;

    @NotEmpty(message = "E-mail can't be null")
    @Email(message = "Invalid e-mail")
    private String email;
    @NotEmpty(message = "Password can not be empty")
    @Length(min = 8, max = 32)
    @Pattern(regexp = "(.*([a-z]|[A-Z]).*)", message = "Must have at least 1 letter.")
    @Pattern(regexp = "(.*[@#$%^&+=].*)", message = "Must have at least 1 special character.")
    @Pattern(regexp = "(.*[0-9].*)", message = "Must have at least 1 number.")
    @Pattern(regexp = "(^[^\\s]*$)", message = "Cannot contain blank spaces.")
    private String senha;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public StatusUsuarioEnum getStatus() {
        return status;
    }

    public void setStatus(StatusUsuarioEnum status) {
        this.status = status;
    }
}
