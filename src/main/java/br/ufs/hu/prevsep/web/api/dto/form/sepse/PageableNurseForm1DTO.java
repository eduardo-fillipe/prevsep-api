package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.PageableRequest;
import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.model.QFormularioSepseEnf1Entity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

public class PageableNurseForm1DTO extends PageableRequest<QFormularioSepseEnf1Entity> {
    private Integer idFormulario;
    @CPF
    private String cpfPaciente;
    private Integer crmMedico;
    private Integer creEnfermeiro;
    private FormStatus status;
    private ProcedenciaDTO procedencia;
    private LocalDate dtCriacaoBegin;
    private LocalDate dtCriacaoEnd;

    private static final Map<String, ComparableExpressionBase<?>> ENTITY_RELATIONSHIP = Map.of(
            "idFormulario", QFormularioSepseEnf1Entity.formularioSepseEnf1Entity.idFormulario,
            "cpfPaciente", QFormularioSepseEnf1Entity.formularioSepseEnf1Entity.paciente.cpf,
            "crmMedico", QFormularioSepseEnf1Entity.formularioSepseEnf1Entity.crmMedico,
            "creEnfermeiro", QFormularioSepseEnf1Entity.formularioSepseEnf1Entity.creEnfermeiro,
            "status", QFormularioSepseEnf1Entity.formularioSepseEnf1Entity.status,
            "dtCriacao", QFormularioSepseEnf1Entity.formularioSepseEnf1Entity.dtCriacao,
            "procedencia", QFormularioSepseEnf1Entity.formularioSepseEnf1Entity.procedencia
    );

    public Integer getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Integer idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Integer getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(Integer crmMedico) {
        this.crmMedico = crmMedico;
    }

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }

    public LocalDate getDtCriacaoBegin() {
        return dtCriacaoBegin;
    }

    public void setDtCriacaoBegin(LocalDate dtCriacaoBegin) {
        this.dtCriacaoBegin = dtCriacaoBegin;
    }

    public LocalDate getDtCriacaoEnd() {
        return dtCriacaoEnd;
    }

    public void setDtCriacaoEnd(LocalDate dtCriacaoEnd) {
        this.dtCriacaoEnd = dtCriacaoEnd;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String idPaciente) {
        this.cpfPaciente = idPaciente;
    }

    public Integer getCreEnfermeiro() {
        return creEnfermeiro;
    }

    public void setCreEnfermeiro(Integer creEnfermeiro) {
        this.creEnfermeiro = creEnfermeiro;
    }

    public ProcedenciaDTO getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(ProcedenciaDTO procedencia) {
        this.procedencia = procedencia;
    }

    @Override
    public Predicate getQueryPredicate(QFormularioSepseEnf1Entity qEntity) {
        BooleanBuilder filter = new BooleanBuilder();
        if (this.idFormulario != null)
            filter.and(qEntity.idFormulario.eq(this.idFormulario));
        if (this.cpfPaciente != null)
            filter.and(qEntity.paciente.cpf.eq(cpfPaciente));
        if (this.creEnfermeiro != null)
            filter.and(qEntity.creEnfermeiro.eq(creEnfermeiro));
        if (this.crmMedico != null)
            filter.and(qEntity.crmMedico.eq(this.crmMedico));
        if (this.status != null)
            filter.and(qEntity.status.eq(status.getValue()));
        if (this.procedencia != null)
            filter.and(qEntity.procedencia.eq(this.procedencia.getValue()));
        if (this.dtCriacaoBegin != null)
            filter.and(qEntity.dtCriacao.goe(Date.valueOf(this.dtCriacaoBegin)));
        if (this.dtCriacaoEnd != null)
            filter.and(qEntity.dtCriacao.loe(Date.valueOf(this.dtCriacaoEnd)));

        return filter;
    }

    @Override
    public Map<String, ComparableExpressionBase<?>> getSortFieldEntityMapping() {
        return ENTITY_RELATIONSHIP;
    }
}
