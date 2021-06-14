package br.ufs.hu.prevsep.web.api.dto.form;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class PatientCreateDTO {
    @Min(value = 1, message = "Min age: 1")
    private Integer idade;
    @NotEmpty
    private String sexo;
    @NotEmpty
    private String leito;
    @NotEmpty
    private String nrAtendimento;
    @NotEmpty
    private String registro;
    @CPF
    @NotEmpty
    private String cpf;

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLeito() {
        return leito;
    }

    public void setLeito(String leito) {
        this.leito = leito;
    }

    public String getNrAtendimento() {
        return nrAtendimento;
    }

    public void setNrAtendimento(String nrAtendimento) {
        this.nrAtendimento = nrAtendimento;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
