package br.ufs.hu.prevsep.web.api.dto.user.usuario;

public class UsuarioDTO {
    private String cpf;
    private String nome;
    private String email;
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
