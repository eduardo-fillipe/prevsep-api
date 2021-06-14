package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "gestor")
public class ManagerEntity implements Serializable {

    @Id
    @Column(name = "cpf")
    private String cpf;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private UsuarioEntity userInfo;

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
