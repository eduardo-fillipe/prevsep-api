package br.ufs.hu.prevsep.web.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Valid
public abstract class PageRequest<T extends EntityPath<?>> {
    @Min(0)
    private Long pageNumber;
    @Min(1)
    private Long pageLimit;
    @Schema(description = "Ordenação da lista de dados em ordem crescente e decrescente (e.g. ['nome ASC', 'nome DESC'])")
    private List<String> pageSort;

    public enum Order {
        ASC("asc"), DESC("desc");
        private final String order;

        Order(String order) {
            this.order = order;
        }

        public String getOrder() {
            return order;
        }

        public static Order fromString(String order){
            if ("desc".equalsIgnoreCase(order))
                return DESC;
            return ASC;
        }
    }

    @JsonIgnore
    public abstract Predicate getQueryPredicate(T qEntity);

    @JsonIgnore
    public abstract OrderSpecifier<?>[] getOrderSpecifiers(T qEntity);

    @JsonIgnore
    public LinkedHashMap<String, Order> getOrderMap(){
        if (pageSort == null)
            return new LinkedHashMap<>();

        return pageSort.stream()
                .map(s -> s.split(" "))
                .filter(e -> e.length == 2 && (e[1].equalsIgnoreCase("asc")) || e[1].equalsIgnoreCase("desc"))
                .collect(Collectors.toMap(elem -> elem[0], elem -> Order.fromString(elem[1]), (u, v) -> v, LinkedHashMap::new));
    }

    public Long getPageNumber() {
        return pageNumber == null ? 0 : pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getPageLimit() {

        return pageLimit == null ? Long.MAX_VALUE : pageLimit;
    }

    public void setPageLimit(Long pageLimit) {
        this.pageLimit = pageLimit;
    }

    public List<String> getPageSort() {
        return pageSort == null ? new ArrayList<>() : pageSort;
    }

    public void setPageSort(List<String> pageSort) {
        this.pageSort = pageSort;
    }
}
