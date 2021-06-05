package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "enfermeiro")
public class EnfermeiroEntity {

    @Id
    @Column(name="cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cre")
    private Integer cre;

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

    public Integer getCre() {
        return cre;
    }

    public void setCre(Integer cre) {
        this.cre = cre;
    }

    public UsuarioEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UsuarioEntity userInfo) {
        this.userInfo = userInfo;
    }
}
