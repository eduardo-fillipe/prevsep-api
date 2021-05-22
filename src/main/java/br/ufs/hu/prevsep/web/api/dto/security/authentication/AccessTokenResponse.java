package br.ufs.hu.prevsep.web.api.dto.security.authentication;

import br.ufs.hu.prevsep.web.api.dto.security.authentication.views.AccessTokenResponseView;
import br.ufs.hu.prevsep.web.api.dto.security.authorization.role.RoleDTO;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class AccessTokenResponse {
    private String access_token;
    private String token_type;
    private Long expires_in;
    private List<RoleDTO> claims;

    public AccessTokenResponse() {
    }

    public AccessTokenResponse(String access_token, String token_type, Long expires_in, List<RoleDTO> claims) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.claims = claims;
    }

    @JsonView(AccessTokenResponseView.NormalView.class)
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @JsonView(AccessTokenResponseView.NormalView.class)
    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @JsonView(AccessTokenResponseView.NormalView.class)
    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    @JsonView(AccessTokenResponseView.NormalView.class)
    public List<RoleDTO> getClaims() {
        return claims;
    }

    public void setClaims(List<RoleDTO> claims) {
        this.claims = claims;
    }
}
