package br.ufs.hu.prevsep.web.api.dto.security;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class UsuarioLoginLogDTO {
    private String id;
    private String idUsuario;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtLogin;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtExplicitLogout;
    private Integer role;
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getDtLogin() {
        return dtLogin;
    }

    public void setDtLogin(LocalDateTime dtLogin) {
        this.dtLogin = dtLogin;
    }

    public LocalDateTime getDtExplicitLogout() {
        return dtExplicitLogout;
    }

    public void setDtExplicitLogout(LocalDateTime dtExplicitLogout) {
        this.dtExplicitLogout = dtExplicitLogout;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioLoginLogDTO that = (UsuarioLoginLogDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(dtLogin, that.dtLogin) && Objects.equals(dtExplicitLogout, that.dtExplicitLogout) && Objects.equals(role, that.role) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtLogin, dtExplicitLogout, role, status);
    }
}
