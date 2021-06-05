package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "gestor")
public class ManagerEntity {

    @Column(name = "nome")
    private String nome;

    @Id
    @Column(name = "cpf")
    private String cpf;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private UsuarioEntity userInfo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UsuarioEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioEntity userInfo) {
        this.userInfo = userInfo;
    }
}
