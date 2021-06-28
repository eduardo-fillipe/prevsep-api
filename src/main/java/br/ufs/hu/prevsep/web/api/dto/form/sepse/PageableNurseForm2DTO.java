package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.PageableRequest;
import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.model.QFormularioSepseEnf2Entity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

public class PageableNurseForm2DTO extends PageableRequest<QFormularioSepseEnf2Entity> {

    private Integer idFormulario;
    private Integer creEnfermeiro;
    private LocalDateTime dtUtiBegin;
    private LocalDateTime dtUtiEnd;
    private LocalDateTime dtAltaBegin;
    private LocalDateTime dtAltaEnd;
    private LocalDateTime dtObitoBegin;
    private LocalDateTime dtObitoEnd;
    private LocalDateTime dtCriacaoBegin;
    private LocalDateTime dtCriacaoEnd;
    private FormStatus status;

    private static final Map<String, ComparableExpressionBase<?>> ENTITY_RELATIONSHIP = Map.of(
            "idFormulario", QFormularioSepseEnf2Entity.formularioSepseEnf2Entity.idFormulario,
            "creEnfermeiro", QFormularioSepseEnf2Entity.formularioSepseEnf2Entity.creEnfermeiro,
            "dtUti", QFormularioSepseEnf2Entity.formularioSepseEnf2Entity.dtUti,
            "dtAlta", QFormularioSepseEnf2Entity.formularioSepseEnf2Entity.dtAlta,
            "dtObito", QFormularioSepseEnf2Entity.formularioSepseEnf2Entity.dtObito,
            "status", QFormularioSepseEnf2Entity.formularioSepseEnf2Entity.status,
            "dtCriacao", QFormularioSepseEnf2Entity.formularioSepseEnf2Entity.dtCriacao
    );

    public Integer getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Integer idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Integer getCreEnfermeiro() {
        return creEnfermeiro;
    }

    public void setCreEnfermeiro(Integer creEnfermeiro) {
        this.creEnfermeiro = creEnfermeiro;
    }

    public LocalDateTime getDtUtiBegin() {
        return dtUtiBegin;
    }

    public void setDtUtiBegin(LocalDateTime dtUtiBegin) {
        this.dtUtiBegin = dtUtiBegin;
    }

    public LocalDateTime getDtUtiEnd() {
        return dtUtiEnd;
    }

    public void setDtUtiEnd(LocalDateTime dtUtiEnd) {
        this.dtUtiEnd = dtUtiEnd;
    }

    public LocalDateTime getDtAltaBegin() {
        return dtAltaBegin;
    }

    public void setDtAltaBegin(LocalDateTime dtAltaBegin) {
        this.dtAltaBegin = dtAltaBegin;
    }

    public LocalDateTime getDtAltaEnd() {
        return dtAltaEnd;
    }

    public void setDtAltaEnd(LocalDateTime dtAltaEnd) {
        this.dtAltaEnd = dtAltaEnd;
    }

    public LocalDateTime getDtObitoBegin() {
        return dtObitoBegin;
    }

    public void setDtObitoBegin(LocalDateTime dtObitoBegin) {
        this.dtObitoBegin = dtObitoBegin;
    }

    public LocalDateTime getDtObitoEnd() {
        return dtObitoEnd;
    }

    public void setDtObitoEnd(LocalDateTime dtObitoEnd) {
        this.dtObitoEnd = dtObitoEnd;
    }

    public LocalDateTime getDtCriacaoBegin() {
        return dtCriacaoBegin;
    }

    public void setDtCriacaoBegin(LocalDateTime dtCriacaoBegin) {
        this.dtCriacaoBegin = dtCriacaoBegin;
    }

    public LocalDateTime getDtCriacaoEnd() {
        return dtCriacaoEnd;
    }

    public void setDtCriacaoEnd(LocalDateTime dtCriacaoEnd) {
        this.dtCriacaoEnd = dtCriacaoEnd;
    }

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }

    @Override
    public Predicate getQueryPredicate(QFormularioSepseEnf2Entity qEntity) {
        BooleanBuilder filter = new BooleanBuilder();

        if (this.idFormulario != null)
            filter.and(qEntity.idFormulario.eq(this.idFormulario));
        if (this.creEnfermeiro != null)
            filter.and(qEntity.creEnfermeiro.eq(this.creEnfermeiro));
        if (this.status != null)
            filter.and(qEntity.status.eq(status.getValue()));

        if (this.dtCriacaoBegin != null)
            filter.and(qEntity.dtCriacao.goe(Timestamp.valueOf(this.dtCriacaoBegin)));
        if (this.dtCriacaoEnd != null)
            filter.and(qEntity.dtCriacao.loe(Timestamp.valueOf(this.dtCriacaoEnd)));

        if (this.dtAltaBegin != null)
            filter.and(qEntity.dtAlta.goe(Timestamp.valueOf(this.dtAltaBegin)));
        if (this.dtAltaEnd != null)
            filter.and(qEntity.dtAlta.loe(Timestamp.valueOf(this.dtAltaEnd)));

        if (this.dtUtiBegin != null)
            filter.and(qEntity.dtUti.goe(Timestamp.valueOf(this.dtUtiBegin)));
        if (this.dtUtiEnd != null)
            filter.and(qEntity.dtUti.loe(Timestamp.valueOf(this.dtUtiEnd)));

        if (this.dtObitoBegin != null)
            filter.and(qEntity.dtObito.goe(Timestamp.valueOf(this.dtObitoBegin)));
        if (this.dtObitoEnd != null)
            filter.and(qEntity.dtObito.loe(Timestamp.valueOf(this.dtObitoEnd)));

        return filter;
    }

    @Override
    public Map<String, ComparableExpressionBase<?>> getSortFieldEntityMapping() {
        return ENTITY_RELATIONSHIP;
    }
}
