package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientDTO;

import java.time.LocalDate;

public class DoctorFormDTO {
    private int idFormulario;
    private PatientDTO paciente;
    private int crmMedico;
    private String focoInfeccioso;
    private String critExclusao;
    private String bundleHora1;
    private LocalDate dtDispProtocolo;
    private LocalDate dtColetaLactato;
    private LocalDate dtColetaHemocult;
    private LocalDate dtPrimeiraDose;
    private LocalDate dtCriacao;
    private FormStatus status;
    private String reavaliacoesSeriadas;

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public PatientDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PatientDTO paciente) {
        this.paciente = paciente;
    }

    public int getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(int crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getFocoInfeccioso() {
        return focoInfeccioso;
    }

    public void setFocoInfeccioso(String focoInfeccioso) {
        this.focoInfeccioso = focoInfeccioso;
    }

    public String getCritExclusao() {
        return critExclusao;
    }

    public void setCritExclusao(String critExclusao) {
        this.critExclusao = critExclusao;
    }

    public String getBundleHora1() {
        return bundleHora1;
    }

    public void setBundleHora1(String bundleHora1) {
        this.bundleHora1 = bundleHora1;
    }

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }

    public String getReavaliacoesSeriadas() {
        return reavaliacoesSeriadas;
    }

    public void setReavaliacoesSeriadas(String reavaliacoesSeriadas) {
        this.reavaliacoesSeriadas = reavaliacoesSeriadas;
    }

    public LocalDate getDtDispProtocolo() {
        return dtDispProtocolo;
    }

    public void setDtDispProtocolo(LocalDate dtDispProtocolo) {
        this.dtDispProtocolo = dtDispProtocolo;
    }

    public LocalDate getDtColetaLactato() {
        return dtColetaLactato;
    }

    public void setDtColetaLactato(LocalDate dtColetaLactato) {
        this.dtColetaLactato = dtColetaLactato;
    }

    public LocalDate getDtColetaHemocult() {
        return dtColetaHemocult;
    }

    public void setDtColetaHemocult(LocalDate dtColetaHemocult) {
        this.dtColetaHemocult = dtColetaHemocult;
    }

    public LocalDate getDtPrimeiraDose() {
        return dtPrimeiraDose;
    }

    public void setDtPrimeiraDose(LocalDate dtPrimeiraDose) {
        this.dtPrimeiraDose = dtPrimeiraDose;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDate dtCriacao) {
        this.dtCriacao = dtCriacao;
    }
}