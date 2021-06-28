package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "formulario_sepse_enf1_sirs", schema = "public")
public class FormularioSepseEnf1SirsEntity {
    private int idFormulario;
    private Boolean febreHipotemia;
    private Boolean leucocitoseLeucopenia;
    private Boolean taquicardia;
    private Boolean taquipneia;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @Basic
    @Column(name = "febre_hipotemia", nullable = true)
    public Boolean getFebreHipotemia() {
        return febreHipotemia;
    }

    public void setFebreHipotemia(Boolean febreHipotemia) {
        this.febreHipotemia = febreHipotemia;
    }

    @Basic
    @Column(name = "leucocitose_leucopenia", nullable = true)
    public Boolean getLeucocitoseLeucopenia() {
        return leucocitoseLeucopenia;
    }

    public void setLeucocitoseLeucopenia(Boolean leucocitoseLeucopenia) {
        this.leucocitoseLeucopenia = leucocitoseLeucopenia;
    }

    @Basic
    @Column(name = "taquicardia", nullable = true)
    public Boolean getTaquicardia() {
        return taquicardia;
    }

    public void setTaquicardia(Boolean taquicardia) {
        this.taquicardia = taquicardia;
    }

    @Basic
    @Column(name = "taquipneia", nullable = true)
    public Boolean getTaquipneia() {
        return taquipneia;
    }

    public void setTaquipneia(Boolean taquipneia) {
        this.taquipneia = taquipneia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseEnf1SirsEntity that = (FormularioSepseEnf1SirsEntity) o;
        return idFormulario == that.idFormulario && Objects.equals(febreHipotemia, that.febreHipotemia) && Objects.equals(leucocitoseLeucopenia, that.leucocitoseLeucopenia) && Objects.equals(taquicardia, that.taquicardia) && Objects.equals(taquipneia, that.taquipneia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, febreHipotemia, leucocitoseLeucopenia, taquicardia, taquipneia);
    }
}
