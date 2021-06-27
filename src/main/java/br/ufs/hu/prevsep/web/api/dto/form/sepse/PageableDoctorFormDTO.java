package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.PageableRequest;
import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.model.QFormularioSepseMedicoEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

public class PageableDoctorFormDTO extends PageableRequest<QFormularioSepseMedicoEntity> {
    private Integer crmMedico;
    private FormStatus status;
    private LocalDate dtCriacaoBegin;
    private LocalDate dtCriacaoEnd;

    private static final Map<String, ComparableExpressionBase<?>> ENTITY_RELATIONSHIP = Map.of(
            "crmMedico", QFormularioSepseMedicoEntity.formularioSepseMedicoEntity.crmMedico,
            "status", QFormularioSepseMedicoEntity.formularioSepseMedicoEntity.status,
            "dtCriacao", QFormularioSepseMedicoEntity.formularioSepseMedicoEntity.dtCriacao
    );

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

    @Override
    public Predicate getQueryPredicate(QFormularioSepseMedicoEntity qEntity) {
        BooleanBuilder filter = new BooleanBuilder();

        if (this.crmMedico != null)
            filter.and(qEntity.crmMedico.eq(this.crmMedico));
        if (this.status != null)
            filter.and(qEntity.status.eq(status.getValue()));
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
