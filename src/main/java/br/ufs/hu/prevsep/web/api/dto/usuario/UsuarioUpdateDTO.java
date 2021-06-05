package br.ufs.hu.prevsep.web.api.dto.usuario;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UsuarioUpdateDTO {
    @Nullable
    @Email(message = "Invalid e-mail")
    private String email;
    @Nullable
    @Size(min = 8, max = 32)
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

    public void setStatus(StatusUsuarioEnum status) {
        this.status = status;
    }
}
