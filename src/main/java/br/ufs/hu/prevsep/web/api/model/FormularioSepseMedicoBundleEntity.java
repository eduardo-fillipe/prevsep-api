package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "formulario_sepse_medico_bundle", schema = "public")
public class FormularioSepseMedicoBundleEntity {
    private int idFormulario;
    private boolean iniciado;
    private LocalDateTime dtDisparo;
    private LocalDateTime lactoDtColeta;
    private LocalDateTime hemoculturaDtColeta;
    private LocalDateTime antibioticoAmploAspectro;
    private boolean cristaloides;
    private boolean vasopressores;
    private String justificativaNao;

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
    @Column(name = "iniciado")
    public boolean getIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }

    @Basic
    @Column(name = "dt_disparo")
    public LocalDateTime getDtDisparo() {
        return dtDisparo;
    }

    public void setDtDisparo(LocalDateTime dtDisparo) {
        this.dtDisparo = dtDisparo;
    }

    @Basic
    @Column(name = "lacto_dt_coleta")
    public LocalDateTime getLactoDtColeta() {
        return lactoDtColeta;
    }

    public void setLactoDtColeta(LocalDateTime lactoDtColeta) {
        this.lactoDtColeta = lactoDtColeta;
    }

    @Basic
    @Column(name = "hemocultura_dt_coleta")
    public LocalDateTime getHemoculturaDtColeta() {
        return hemoculturaDtColeta;
    }

    public void setHemoculturaDtColeta(LocalDateTime hemoculturaDtColeta) {
        this.hemoculturaDtColeta = hemoculturaDtColeta;
    }

    @Basic
    @Column(name = "antibiotico_amplo_aspectro")
    public LocalDateTime getAntibioticoAmploAspectro() {
        return antibioticoAmploAspectro;
    }

    public void setAntibioticoAmploAspectro(LocalDateTime antibioticoAmploAspectro) {
        this.antibioticoAmploAspectro = antibioticoAmploAspectro;
    }

    @Basic
    @Column(name = "cristaloides")
    public boolean getCristaloides() {
        return cristaloides;
    }

    public void setCristaloides(boolean cristaloides) {
        this.cristaloides = cristaloides;
    }

    @Basic
    @Column(name = "vasopressores")
    public boolean getVasopressores() {
        return vasopressores;
    }

    public void setVasopressores(boolean vasopressores) {
        this.vasopressores = vasopressores;
    }

    @Basic
    @Column(name = "justificativa_nao", length = -1)
    public String getJustificativaNao() {
        return justificativaNao;
    }

    public void setJustificativaNao(String justificativaNao) {
        this.justificativaNao = justificativaNao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormularioSepseMedicoBundleEntity that = (FormularioSepseMedicoBundleEntity) o;
        return idFormulario == that.idFormulario && Objects.equals(iniciado, that.iniciado) && Objects.equals(dtDisparo, that.dtDisparo) && Objects.equals(lactoDtColeta, that.lactoDtColeta) && Objects.equals(hemoculturaDtColeta, that.hemoculturaDtColeta) && Objects.equals(antibioticoAmploAspectro, that.antibioticoAmploAspectro) && Objects.equals(cristaloides, that.cristaloides) && Objects.equals(vasopressores, that.vasopressores) && Objects.equals(justificativaNao, that.justificativaNao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, iniciado, dtDisparo, lactoDtColeta, hemoculturaDtColeta, antibioticoAmploAspectro, cristaloides, vasopressores, justificativaNao);
    }
}
