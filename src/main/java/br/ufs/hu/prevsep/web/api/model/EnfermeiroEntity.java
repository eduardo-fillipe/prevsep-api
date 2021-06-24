package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(schema = "public", name = "enfermeiro")
public class EnfermeiroEntity implements Serializable {

    @Id
    @Column(name="cpf")
    private String cpf;

    @Column(name = "cre")
    private Integer cre;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private UsuarioEntity userInfo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cre_enfermeiro", referencedColumnName = "cre")
    private List<FormularioSepseEnf1Entity> formularioSepseEnf1Entities;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cre_enfermeiro", referencedColumnName = "cre")
    private List<FormularioSepseEnf2Entity> formularioSepseEnf2Entities;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public List<FormularioSepseEnf1Entity> getFormularioSepseEnf1Entities() {
        return formularioSepseEnf1Entities;
    }

    public void setFormularioSepseEnf1Entities(List<FormularioSepseEnf1Entity> formularioSepseEnf1Entities) {
        this.formularioSepseEnf1Entities = formularioSepseEnf1Entities;
    }

    public List<FormularioSepseEnf2Entity> getFormularioSepseEnf2Entities() {
        return formularioSepseEnf2Entities;
    }

    public void setFormularioSepseEnf2Entities(List<FormularioSepseEnf2Entity> formularioSepseEnf2Entities) {
        this.formularioSepseEnf2Entities = formularioSepseEnf2Entities;
    }
}
