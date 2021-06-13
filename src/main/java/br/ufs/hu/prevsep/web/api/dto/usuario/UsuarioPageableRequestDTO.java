package br.ufs.hu.prevsep.web.api.dto.usuario;

import br.ufs.hu.prevsep.web.api.dto.PageableRequest;
import br.ufs.hu.prevsep.web.api.model.QUsuarioEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;

import javax.validation.Valid;
import java.util.Map;

@Valid
public class UsuarioPageableRequestDTO extends PageableRequest<QUsuarioEntity> {
    private String cpf;
    private String nome;
    private String email;
    private CargoEnum cargo;
    private StatusUsuarioEnum status;

    private static final Map<String, ComparableExpressionBase<?>> ENTITY_RELATIONSHIP = Map.of(
            "email", QUsuarioEntity.usuarioEntity.email,
            "cpf", QUsuarioEntity.usuarioEntity.cpf,
            "nome", QUsuarioEntity.usuarioEntity.nome,
            "status", QUsuarioEntity.usuarioEntity.status,
            "cargo", QUsuarioEntity.usuarioEntity.cargo
        );

    @Override
    public Predicate getQueryPredicate(QUsuarioEntity qEntity) {
        BooleanBuilder filter = new BooleanBuilder(qEntity.status.ne(StatusUsuarioEnum.DESATIVADO.getStatus()));

        if (cargo != null)
            filter.and(qEntity.cargo.eq(cargo.getId()));
        if (status != null)
            filter.and(qEntity.status.eq(status.getStatus()));
        if (nome != null)
            filter.and(qEntity.nome.lower().like("%" + nome.toLowerCase() + "%"));
        if (email != null)
            filter.and(qEntity.email.lower().like("%" + email.toLowerCase() + "%"));

        return filter;
    }

    @Override
    public Map<String, ComparableExpressionBase<?>> getSortFieldEntityMapping() {
        return ENTITY_RELATIONSHIP;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CargoEnum getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnum cargo) {
        this.cargo = cargo;
    }

    public StatusUsuarioEnum getStatus() {
        return status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(StatusUsuarioEnum status) {
        this.status = status;
    }
}
