package br.ufs.hu.prevsep.web.api.dto.security;

import br.ufs.hu.prevsep.web.api.dto.PageableRequest;
import br.ufs.hu.prevsep.web.api.model.QUsuarioLoginLogEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;

import java.time.LocalDateTime;
import java.util.Map;

public class PageableUsuarioLoginLogDTO extends PageableRequest<QUsuarioLoginLogEntity> {
    private String id;
    private String cpfUsuario;
    private LocalDateTime dtLoginBegin;
    private LocalDateTime dtLoginEnd;
    private LocalDateTime dtExplicitLogoutBegin;
    private LocalDateTime dtExplicitLogoutEnd;
    private Integer role;
    private Integer status;

    private static final Map<String, ComparableExpressionBase<?>> ENTITY_RELATIONSHIP = Map.of (
            "cpfUsuario", QUsuarioLoginLogEntity.usuarioLoginLogEntity.idUsuario,
            "dtLogin", QUsuarioLoginLogEntity.usuarioLoginLogEntity.dtLogin,
            "dtExplicitLogout", QUsuarioLoginLogEntity.usuarioLoginLogEntity.dtExplicitLogout,
            "role", QUsuarioLoginLogEntity.usuarioLoginLogEntity.role,
            "status", QUsuarioLoginLogEntity.usuarioLoginLogEntity.status
    );

    @Override
    public Map<String, ComparableExpressionBase<?>> getSortFieldEntityMapping() {
        return ENTITY_RELATIONSHIP;
    }

    @Override
    public Predicate getQueryPredicate(QUsuarioLoginLogEntity qEntity) {
        BooleanBuilder filter = new BooleanBuilder();

        if (id != null)
            filter.and(qEntity.id.eq(id));
        if (cpfUsuario != null)
            filter.and(qEntity.idUsuario.eq(cpfUsuario));
        if (this.dtLoginBegin != null)
            filter.and(qEntity.dtLogin.goe(this.dtLoginBegin));
        if (this.dtLoginEnd != null)
            filter.and(qEntity.dtLogin.loe(this.dtLoginEnd));
        if (this.dtExplicitLogoutBegin != null)
            filter.and(qEntity.dtExplicitLogout.goe(this.dtExplicitLogoutBegin));
        if (this.dtExplicitLogoutEnd != null)
            filter.and(qEntity.dtExplicitLogout.loe(this.dtExplicitLogoutEnd));
        if (role != null)
            filter.and(qEntity.role.eq(role));
        if (status != null)
            filter.and(qEntity.status.eq(status));

        return filter;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String idUsuario) {
        this.cpfUsuario = idUsuario;
    }

    public LocalDateTime getDtLoginBegin() {
        return dtLoginBegin;
    }

    public void setDtLoginBegin(LocalDateTime dtLoginBegin) {
        this.dtLoginBegin = dtLoginBegin;
    }

    public LocalDateTime getDtLoginEnd() {
        return dtLoginEnd;
    }

    public void setDtLoginEnd(LocalDateTime dtLoginEnd) {
        this.dtLoginEnd = dtLoginEnd;
    }

    public LocalDateTime getDtExplicitLogoutBegin() {
        return dtExplicitLogoutBegin;
    }

    public void setDtExplicitLogoutBegin(LocalDateTime dtExplicitLogoutBegin) {
        this.dtExplicitLogoutBegin = dtExplicitLogoutBegin;
    }

    public LocalDateTime getDtExplicitLogoutEnd() {
        return dtExplicitLogoutEnd;
    }

    public void setDtExplicitLogoutEnd(LocalDateTime dtExplicitLogoutEnd) {
        this.dtExplicitLogoutEnd = dtExplicitLogoutEnd;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
