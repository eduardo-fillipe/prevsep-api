package br.ufs.hu.prevsep.web.api.dto;

import br.ufs.hu.prevsep.web.api.utils.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> {
    private List<T> content;
    private Boolean hasContent;
    private Integer pageSize;
    private Long totalPages;
    private Long pageNumber;

    public PageResponse(PageResponse<T> pageResponse) {
        BeanUtils.copyPropertiesIgnoreNulls(pageResponse, this, true);
    }

    public PageResponse(List<T> content, @NotNull PageRequest<?> request, Long totalElements) {
        if(content == null)
            content = new ArrayList<>();

        this.content(content)
                .hasContent(!content.isEmpty())
                .pageSize(content.size())
                .totalPages(-Math.floorDiv(-totalElements, request.getPageLimit()))
                .pageNumber(request.getPageNumber());
    }

    public PageResponse<T> content(List<T> content){
        this.content = content;
        return this;
    }

    public PageResponse<T> hasContent(Boolean hasContent){
        this.hasContent = hasContent;
        return this;
    }

    public PageResponse<T> pageSize(Integer pageSize){
        this.pageSize = pageSize;
        return this;
    }

    public PageResponse<T> pageNumber(long pageNumber){
        this.pageNumber = pageNumber;
        return this;
    }

    public PageResponse<T> totalPages(long totalPages){
        this.totalPages = totalPages;
        return this;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Boolean getHasContent() {
        return hasContent;
    }

    public void setHasContent(Boolean hasContent) {
        this.hasContent = hasContent;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }
}
