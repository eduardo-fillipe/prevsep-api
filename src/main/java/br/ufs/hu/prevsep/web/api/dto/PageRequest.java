package br.ufs.hu.prevsep.web.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Valid
public abstract class PageRequest<T extends EntityPath<?>> {
    @Min(0)
    private Long pageNumber;
    @Min(1)
    private Long pageLimit;
    @Schema(description = "Ordenação da lista de dados em ordem crescente e decrescente (e.g. ['nome ASC', 'nome DESC'])")
    private List<String> pageSort;


    /**
     * Returns the Conditional Search Predicate for this PageRequest.
     *
     * @param qEntity The qEntity related with this PageRequest.
     * @return A Predicate that describes this PageRequest.
     *
     * @apiNote The conditional Search Predicate is the
     * conditional statement that goes inside of the WHERE clause in a SQL Statement.
     */
    public abstract Predicate getQueryPredicate(T qEntity);

    public OrderSpecifier<?>[] getOrderSpecifiers(T qEntity) {
        LinkedHashMap<String, Order> orderMap = getOrderMap();
        Map<String, ComparableExpressionBase<?>> fieldMapping = getSortFieldEntityMapping(qEntity);

        if (fieldMapping != null) {
            ArrayList<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

            orderMap.forEach((k, v) -> {
                ComparableExpressionBase<?> p = fieldMapping.get(k);
                if (p != null)
                    orderSpecifiers.add(v == Order.ASC ? p.asc() : p.desc());
            });

            return orderSpecifiers.toArray(new OrderSpecifier[0]);
        }

        return new OrderSpecifier[0];
    }

    /**
     * This method creates a link between an entity and a PageRequest. This maps a string to a path in the
     * QEntity. This method is used to know how fields of this RequestPage relates to its Entity while ordering.
     *
     * If a field is not mapped in this map, its ignored during the sorting process.
     * @apiNote A default field for ordering can be set adding the key 'default' to the map.
     *
     * @param qEntity The QEntity related with this PageRequest.
     * @return A Map, where the key is a string that represents the name of the field in this PageRequest
     * and the value is a field of the qEntity parameter.
     */
    public abstract Map<String, ComparableExpressionBase<?>> getSortFieldEntityMapping(T qEntity);

    @JsonIgnore
    private LinkedHashMap<String, Order> getOrderMap(){
        if (pageSort == null)
            return new LinkedHashMap<>();

        return pageSort.stream()
                .map(s -> s.split(" "))
                .filter(e -> e.length == 2 && (e[1].equalsIgnoreCase("asc")) || e[1].equalsIgnoreCase("desc"))
                .collect(Collectors.toMap(elem -> elem[0], elem -> Order.fromString(elem[1]), (u, v) -> v, LinkedHashMap::new));
    }

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
