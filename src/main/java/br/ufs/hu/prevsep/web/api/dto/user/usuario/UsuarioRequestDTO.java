package br.ufs.hu.prevsep.web.api.dto.user.usuario;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UsuarioRequestDTO {
    @CPF(message = "CPF inválido")
    @NotEmpty
    private String cpf;

    @NotEmpty(message = "Não pode ser vazio")
    @Length(min = 1, max = 255, message = "O nome deve ter tamanho entre 1 e 255.")
    private String nome;

    @NotEmpty(message = "E-mail não pode ser nulo")
    @Email(message = "E-mail inválido")
    private String email;
    @NotEmpty(message = "Senha não pode ser vazia")
    @Length(min = 8, max = 32)
    @Pattern(regexp = "(.*([a-z]|[A-Z]).*)", message = "Deve ter no mínimo 1 letra.")
    @Pattern(regexp = "(.*[@#$%^&+=].*)", message = "Deve ter no mínimo 1 caractere especial.")
    @Pattern(regexp = "(.*[0-9].*)", message = "Deve ter no mínimo 1 número.")
    @Pattern(regexp = "(^[^\\s]*$)", message = "Não pode contem espaços em branco.")
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
