package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "formulario_sepse_medico_reavaliacoes_seriadas", schema = "public")
public class FormularioSepseMedicoReavaliacoesSeriadasEntity {
    private int idFormulario;
    private boolean qSofa;
    private boolean pas100Mmghg;
    private boolean fr22Rpm;
    private boolean rebaixamentoNivelConsiencia;
    private boolean lactoInicialmenteAlto;
    private String outros;
    private String justificativaNao;
    private boolean aplicada;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @Basic
    @Column(name = "q_sofa", nullable = true)
    public boolean getqSofa() {
        return qSofa;
    }

    public void setqSofa(boolean qSofa) {
        this.qSofa = qSofa;
    }

    @Basic
    @Column(name = "pas_100_mmghg", nullable = true)
    public boolean getPas100Mmghg() {
        return pas100Mmghg;
    }

    public void setPas100Mmghg(boolean pas100Mmghg) {
        this.pas100Mmghg = pas100Mmghg;
    }

    @Basic
    @Column(name = "fr_22_rpm", nullable = true)
    public boolean getFr22Rpm() {
        return fr22Rpm;
    }

    public void setFr22Rpm(boolean fr22Rpm) {
        this.fr22Rpm = fr22Rpm;
    }

    @Basic
    @Column(name = "rebaixamento_nivel_consiencia", nullable = true)
    public boolean getRebaixamentoNivelConsiencia() {
        return rebaixamentoNivelConsiencia;
    }

    public void setRebaixamentoNivelConsiencia(boolean rebaixamentoNivelConsiencia) {
        this.rebaixamentoNivelConsiencia = rebaixamentoNivelConsiencia;
    }

    @Basic
    @Column(name = "lacto_inicialmente_alto", nullable = true)
    public boolean getLactoInicialmenteAlto() {
        return lactoInicialmenteAlto;
    }

    public void setLactoInicialmenteAlto(boolean lactoInicialmenteAlto) {
        this.lactoInicialmenteAlto = lactoInicialmenteAlto;
    }

    @Basic
    @Column(name = "outros", nullable = true, length = -1)
    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }

    @Basic
    @Column(name = "justificativa_nao", nullable = true, length = -1)
    public String getJustificativaNao() {
        return justificativaNao;
    }

    public void setJustificativaNao(String justificativaNao) {
        this.justificativaNao = justificativaNao;
    }

    @Basic
    @Column(name = "aplicada", nullable = true)
    public boolean getAplicada() {
        return aplicada;
    }

    public void setAplicada(boolean aplicada) {
        this.aplicada = aplicada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseMedicoReavaliacoesSeriadasEntity that = (FormularioSepseMedicoReavaliacoesSeriadasEntity) o;
        return idFormulario == that.idFormulario && Objects.equals(qSofa, that.qSofa) && Objects.equals(pas100Mmghg, that.pas100Mmghg) && Objects.equals(fr22Rpm, that.fr22Rpm) && Objects.equals(rebaixamentoNivelConsiencia, that.rebaixamentoNivelConsiencia) && Objects.equals(lactoInicialmenteAlto, that.lactoInicialmenteAlto) && Objects.equals(outros, that.outros) && Objects.equals(justificativaNao, that.justificativaNao) && Objects.equals(aplicada, that.aplicada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, qSofa, pas100Mmghg, fr22Rpm, rebaixamentoNivelConsiencia, lactoInicialmenteAlto, outros, justificativaNao, aplicada);
    }
}
