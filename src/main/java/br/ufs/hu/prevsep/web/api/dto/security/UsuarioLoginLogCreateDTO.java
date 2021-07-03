package br.ufs.hu.prevsep.web.api.dto.security;

import java.time.LocalDateTime;

public class UsuarioLoginLogCreateDTO {
    private String idUsuario;
    private LocalDateTime dtLogin;
    private LocalDateTime dtExplicitLogout;
    private Integer role;
    private Integer status;

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


    public static final class UsuarioLoginLogCreateDTOBuilder {
        private String idUsuario;
        private LocalDateTime dtLogin;
        private LocalDateTime dtExplicitLogout;
        private Integer role;
        private Integer status;

        private UsuarioLoginLogCreateDTOBuilder() {
        }

        public static UsuarioLoginLogCreateDTOBuilder builder() {
            return new UsuarioLoginLogCreateDTOBuilder();
        }

        public UsuarioLoginLogCreateDTOBuilder withIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public UsuarioLoginLogCreateDTOBuilder withDtLogin(LocalDateTime dtLogin) {
            this.dtLogin = dtLogin;
            return this;
        }

        public UsuarioLoginLogCreateDTOBuilder withDtExplicitLogout(LocalDateTime dtExplicitLogout) {
            this.dtExplicitLogout = dtExplicitLogout;
            return this;
        }

        public UsuarioLoginLogCreateDTOBuilder withRole(Integer role) {
            this.role = role;
            return this;
        }

        public UsuarioLoginLogCreateDTOBuilder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public UsuarioLoginLogCreateDTO build() {
            UsuarioLoginLogCreateDTO usuarioLoginLogCreateDTO = new UsuarioLoginLogCreateDTO();
            usuarioLoginLogCreateDTO.setIdUsuario(idUsuario);
            usuarioLoginLogCreateDTO.setDtLogin(dtLogin);
            usuarioLoginLogCreateDTO.setDtExplicitLogout(dtExplicitLogout);
            usuarioLoginLogCreateDTO.setRole(role);
            usuarioLoginLogCreateDTO.setStatus(status);
            return usuarioLoginLogCreateDTO;
        }
    }
}
