package br.ufs.hu.prevsep.web.api.model;

import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "usuario_acesso_log", schema = "public")
public class UsuarioAcessoLogEntity implements Persistable<String> {
    private String idLog;
    private String idUsuario;
    private LocalDateTime dtRequisicao;
    private String operation;
    private String bodyRequisicao;
    private String verboRequisicao;
    private String uriRequisicao;
    private Integer statusResponse;
    private String bodyResponse;
    private boolean isNew = true;

    @Id
    @Column(name = "id_log", nullable = false, updatable = false)
    public String getIdLog() {
        return idLog;
    }

    public void setIdLog(String idLog) {
        this.idLog = idLog;
    }

    @Basic
    @Column(name = "id_usuario", nullable = false, length = -1)
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Basic
    @Column(name = "dt_requisicao", nullable = false)
    public LocalDateTime getDtRequisicao() {
        return dtRequisicao;
    }

    public void setDtRequisicao(LocalDateTime dtRequisicao) {
        this.dtRequisicao = dtRequisicao;
    }

    @Basic
    @Column(name = "operation", nullable = true, length = -1)
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Basic
    @Column(name = "body_requisicao", nullable = true, length = -1)
    public String getBodyRequisicao() {
        return bodyRequisicao;
    }

    public void setBodyRequisicao(String bodyRequisicao) {
        this.bodyRequisicao = bodyRequisicao;
    }

    @Basic
    @Column(name = "verbo_requisicao", nullable = true, length = -1)
    public String getVerboRequisicao() {
        return verboRequisicao;
    }

    public void setVerboRequisicao(String verboRequisicao) {
        this.verboRequisicao = verboRequisicao;
    }

    @Basic
    @Column(name = "uri_requisicao", nullable = true, length = -1)
    public String getUriRequisicao() {
        return uriRequisicao;
    }

    public void setUriRequisicao(String uriRequisicao) {
        this.uriRequisicao = uriRequisicao;
    }

    @Basic
    @Column(name = "status_response", nullable = true)
    public Integer getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(Integer statusResponse) {
        this.statusResponse = statusResponse;
    }

    @Basic
    @Column(name = "body_response", nullable = true)
    public String getBodyResponse() {
        return bodyResponse;
    }

    public void setBodyResponse(String bodyResponse) {
        this.bodyResponse = bodyResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioAcessoLogEntity that = (UsuarioAcessoLogEntity) o;
        return Objects.equals(idLog, that.idLog) && Objects.equals(idUsuario, that.idUsuario) && Objects.equals(dtRequisicao, that.dtRequisicao) && Objects.equals(operation, that.operation) && Objects.equals(bodyRequisicao, that.bodyRequisicao) && Objects.equals(verboRequisicao, that.verboRequisicao) && Objects.equals(uriRequisicao, that.uriRequisicao) && Objects.equals(statusResponse, that.statusResponse) && Objects.equals(bodyResponse, that.bodyResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLog, idUsuario, dtRequisicao, operation, bodyRequisicao, verboRequisicao, uriRequisicao, statusResponse, bodyResponse);
    }

    @Nullable
    @Override
    @Transient
    public String getId() {
        return idLog;
    }

    @Override
    @Transient
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
