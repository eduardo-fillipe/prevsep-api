package br.ufs.hu.prevsep.web.api.dto.form;

public class PatientDTO {

    private Integer idPaciente;
    private String nome;
    private Integer idade;
    private String sexo;
    private String leito;
    private String nrAtendimento;
    private String registro;
    private String cpf;

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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
