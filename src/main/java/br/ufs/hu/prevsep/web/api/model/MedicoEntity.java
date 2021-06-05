package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "medico")
public class MedicoEntity {
    @Id
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "nome")
    private String nome;
    @Column(name = "crm")
    private Integer crm;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private UsuarioEntity userInfo;

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

    public UsuarioEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioEntity userInfo) {
        this.userInfo = userInfo;
    }
}
