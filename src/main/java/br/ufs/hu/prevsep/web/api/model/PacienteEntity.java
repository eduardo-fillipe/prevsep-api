package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "paciente", schema = "public")
public class PacienteEntity implements Serializable {
    private int idPaciente;
    private String nome;
    private Integer idade;
    private String sexo;
    private String leito;
    private String nrAtendimento;
    private String registro;
    private String cpf;
    private List<FormularioSepseEnf1Entity> formularios;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_paciente", nullable = false)
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Basic
    @Column(name = "idade", nullable = true)
    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Basic
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "sexo", nullable = true, length = -1)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Basic
    @Column(name = "leito", nullable = true, length = -1)
    public String getLeito() {
        return leito;
    }

    public void setLeito(String leito) {
        this.leito = leito;
    }

    @Basic
    @Column(name = "nr_atendimento", nullable = true, length = -1)
    public String getNrAtendimento() {
        return nrAtendimento;
    }

    public void setNrAtendimento(String nrAtendimento) {
        this.nrAtendimento = nrAtendimento;
    }

    @Basic
    @Column(name = "registro", nullable = true, length = -1)
    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Basic
    @Column(name = "cpf", nullable = true, length = -1)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    public List<FormularioSepseEnf1Entity> getFormularios() {
        return formularios;
    }

    public void setFormularios(List<FormularioSepseEnf1Entity> formularios) {
        this.formularios = formularios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacienteEntity that = (PacienteEntity) o;
        return idPaciente == that.idPaciente && Objects.equals(idade, that.idade) && Objects.equals(sexo, that.sexo) && Objects.equals(leito, that.leito) && Objects.equals(nrAtendimento, that.nrAtendimento) && Objects.equals(registro, that.registro) && Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaciente, idade, sexo, leito, nrAtendimento, registro, cpf);
    }
}
