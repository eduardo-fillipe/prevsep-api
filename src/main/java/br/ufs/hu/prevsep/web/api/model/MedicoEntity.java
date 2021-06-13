package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "medico")
public class MedicoEntity {
    @Id
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "crm")
    private Integer crm;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private UsuarioEntity userInfo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "crmMedico", referencedColumnName = "crm")
    private List<FormularioSepseMedicoEntity> formularioSepseMedicoEntities;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public List<FormularioSepseMedicoEntity> getFormularioSepseMedicoEntities() {
        return formularioSepseMedicoEntities;
    }

    public void setFormularioSepseMedicoEntities(List<FormularioSepseMedicoEntity> formularioSepseMedicoEntities) {
        this.formularioSepseMedicoEntities = formularioSepseMedicoEntities;
    }
}
