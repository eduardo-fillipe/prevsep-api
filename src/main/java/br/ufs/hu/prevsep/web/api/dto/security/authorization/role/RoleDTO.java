package br.ufs.hu.prevsep.web.api.dto.security.authorization.role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Validated
public class RoleDTO implements GrantedAuthority {
    @NotNull(message = "Can not be null.")
    private Integer roleId;

    @NotEmpty(message = "The Role name must be provided.")
    @NotNull(message = "Can not be null.")
    private String roleName;

    @Size(max = 255, message = "The description size must be between 1 and 255.")
    private String description;

    public RoleDTO() {
    }

    public RoleDTO(Integer roleId, String roleName, String description) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(roleId, roleDTO.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + roleId.toString();
    }
}
