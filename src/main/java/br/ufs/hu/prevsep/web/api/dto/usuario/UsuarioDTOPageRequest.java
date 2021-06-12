package br.ufs.hu.prevsep.web.api.dto.usuario;

import br.ufs.hu.prevsep.web.api.dto.PageRequest;
import br.ufs.hu.prevsep.web.api.model.QUsuarioEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Valid
public class UsuarioDTOPageRequest extends PageRequest<QUsuarioEntity> {
    private String cpf;
    private String nome;
    private String email;
    private CargoEnum cargo;
    private StatusUsuarioEnum status;

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
    public OrderSpecifier<?>[] getOrderSpecifiers(QUsuarioEntity qEntity) {
        ArrayList<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        LinkedHashMap<String, Order> orderMap = getOrderMap();

        orderMap.forEach((k, v) -> {
            ComparableExpressionBase<?> p = null;
            switch (k) {
                case "cargo":
                    p = qEntity.cargo;
                    break;
                case "status":
                    p = qEntity.status;
                    break;
                case "nome":
                    p = qEntity.nome;
                    break;
                case "cpf":
                    p = qEntity.cpf;
                    break;
                case "email":
                    p = qEntity.email;
                    break;
            }
            if (p != null)
                orderSpecifiers.add(v == Order.ASC ? p.asc() : p.desc());
        });
        if (orderSpecifiers.isEmpty())
            orderSpecifiers.add(qEntity.nome.asc());

        return orderSpecifiers.toArray(new OrderSpecifier[0]);
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
