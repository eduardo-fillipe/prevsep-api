package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.PageableRequest;
import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.model.QFormularioSepseEnf2Entity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;

import java.sql.Timestamp;
import java.util.Map;

public class PageableNurseForm2DTO extends PageableRequest<QFormularioSepseEnf2Entity> {

    private Integer idFormulario;
    private Integer creEnfermeiro;
    private Timestamp dtUtiBegin;
    private Timestamp dtUtiEnd;
    private Timestamp dtAltaBegin;
    private Timestamp dtAltaEnd;
    private Timestamp dtObitoBegin;
    private Timestamp dtObitoEnd;
    private Timestamp dtCriacaoBegin;
    private Timestamp dtCriacaoEnd;
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

    public Timestamp getDtUtiBegin() {
        return dtUtiBegin;
    }

    public void setDtUtiBegin(Timestamp dtUtiBegin) {
        this.dtUtiBegin = dtUtiBegin;
    }

    public Timestamp getDtUtiEnd() {
        return dtUtiEnd;
    }

    public void setDtUtiEnd(Timestamp dtUtiEnd) {
        this.dtUtiEnd = dtUtiEnd;
    }

    public Timestamp getDtAltaBegin() {
        return dtAltaBegin;
    }

    public void setDtAltaBegin(Timestamp dtAltaBegin) {
        this.dtAltaBegin = dtAltaBegin;
    }

    public Timestamp getDtAltaEnd() {
        return dtAltaEnd;
    }

    public void setDtAltaEnd(Timestamp dtAltaEnd) {
        this.dtAltaEnd = dtAltaEnd;
    }

    public Timestamp getDtObitoBegin() {
        return dtObitoBegin;
    }

    public void setDtObitoBegin(Timestamp dtObitoBegin) {
        this.dtObitoBegin = dtObitoBegin;
    }

    public Timestamp getDtObitoEnd() {
        return dtObitoEnd;
    }

    public void setDtObitoEnd(Timestamp dtObitoEnd) {
        this.dtObitoEnd = dtObitoEnd;
    }

    public Timestamp getDtCriacaoBegin() {
        return dtCriacaoBegin;
    }

    public void setDtCriacaoBegin(Timestamp dtCriacaoBegin) {
        this.dtCriacaoBegin = dtCriacaoBegin;
    }

    public Timestamp getDtCriacaoEnd() {
        return dtCriacaoEnd;
    }

    public void setDtCriacaoEnd(Timestamp dtCriacaoEnd) {
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
            filter.and(qEntity.dtCriacao.goe(this.dtCriacaoBegin));
        if (this.dtCriacaoEnd != null)
            filter.and(qEntity.dtCriacao.loe(this.dtCriacaoEnd));

        if (this.dtAltaBegin != null)
            filter.and(qEntity.dtAlta.goe(this.dtAltaBegin));
        if (this.dtAltaEnd != null)
            filter.and(qEntity.dtAlta.loe(this.dtAltaEnd));

        if (this.dtUtiBegin != null)
            filter.and(qEntity.dtUti.goe(this.dtUtiBegin));
        if (this.dtUtiEnd != null)
            filter.and(qEntity.dtUti.loe(this.dtUtiEnd));

        if (this.dtObitoBegin != null)
            filter.and(qEntity.dtObito.goe(this.dtObitoBegin));
        if (this.dtObitoEnd != null)
            filter.and(qEntity.dtObito.loe(this.dtObitoEnd));

        return filter;
    }

    @Override
    public Map<String, ComparableExpressionBase<?>> getSortFieldEntityMapping() {
        return ENTITY_RELATIONSHIP;
    }
}
