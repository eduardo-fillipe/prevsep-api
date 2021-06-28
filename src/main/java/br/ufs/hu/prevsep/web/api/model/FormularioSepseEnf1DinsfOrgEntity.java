package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "formulario_sepse_enf1_dinsf_org", schema = "public")
public class FormularioSepseEnf1DinsfOrgEntity {
    private int idFormulario;
    private Boolean diurese;
    private Boolean hipotensao;
    private Boolean snlcConfAgtcComa;
    private Boolean saturacaoDispneia;

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
    @Column(name = "diurese", nullable = true)
    public Boolean getDiurese() {
        return diurese;
    }

    public void setDiurese(Boolean diurese) {
        this.diurese = diurese;
    }

    @Basic
    @Column(name = "hipotensao", nullable = true)
    public Boolean getHipotensao() {
        return hipotensao;
    }

    public void setHipotensao(Boolean hipotensao) {
        this.hipotensao = hipotensao;
    }

    @Basic
    @Column(name = "snlc_conf_agtc_coma", nullable = true)
    public Boolean getSnlcConfAgtcComa() {
        return snlcConfAgtcComa;
    }

    public void setSnlcConfAgtcComa(Boolean snlcConfAgtcComa) {
        this.snlcConfAgtcComa = snlcConfAgtcComa;
    }

    @Basic
    @Column(name = "saturacao_dispneia", nullable = true)
    public Boolean getSaturacaoDispneia() {
        return saturacaoDispneia;
    }

    public void setSaturacaoDispneia(Boolean saturacaoDispneia) {
        this.saturacaoDispneia = saturacaoDispneia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseEnf1DinsfOrgEntity that = (FormularioSepseEnf1DinsfOrgEntity) o;
        return idFormulario == that.idFormulario && Objects.equals(diurese, that.diurese) && Objects.equals(hipotensao, that.hipotensao) && Objects.equals(snlcConfAgtcComa, that.snlcConfAgtcComa) && Objects.equals(saturacaoDispneia, that.saturacaoDispneia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, diurese, hipotensao, snlcConfAgtcComa, saturacaoDispneia);
    }
}
