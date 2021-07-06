package br.ufs.hu.prevsep.web.api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "usuario_login_log", schema = "public")
public class UsuarioLoginLogEntity {
    private String id;
    private String idUsuario;
    private UsuarioEntity usuario;
    private LocalDateTime dtLogin;
    private LocalDateTime dtExplicitLogout;
    private Integer role;
    private Integer status;

    @Id
    @Column(name = "id", nullable = false, length = -1)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Basic
    @Column(name = "id_usuario")
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Basic
    @Column(name = "dt_login")
    public LocalDateTime getDtLogin() {
        return dtLogin;
    }

    public void setDtLogin(LocalDateTime dtLogin) {
        this.dtLogin = dtLogin;
    }

    @Basic
    @Column(name = "dt_explicit_logout")
    public LocalDateTime getDtExplicitLogout() {
        return dtExplicitLogout;
    }

    public void setDtExplicitLogout(LocalDateTime dtExplicitLogout) {
        this.dtExplicitLogout = dtExplicitLogout;
    }

    @Basic
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "cpf", insertable = false, updatable = false)
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioLoginLogEntity that = (UsuarioLoginLogEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(dtLogin, that.dtLogin) && Objects.equals(dtExplicitLogout, that.dtExplicitLogout) && Objects.equals(role, that.role) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtLogin, dtExplicitLogout, role, status);
    }
}
