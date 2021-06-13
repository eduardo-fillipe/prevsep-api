package br.ufs.hu.prevsep.web.api.dto;

import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PageResponse<T> {
    private List<T> content;
    private Boolean hasContent;
    private Boolean hasNext;
    private Integer pageSize;
    private Long pageNumber;
    private Long totalElements;
    private Long totalPages;

    public PageResponse(@NotNull Page<T> page) {
        this.content(page.getContent())
                .hasContent(!content.isEmpty())
                .pageSize(content.size())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .pageNumber(page.getNumber());
    }

    public PageResponse<T> content(List<T> content){
        this.content = content;
        return this;
    }

    public PageResponse<T> hasContent(Boolean hasContent){
        this.hasContent = hasContent;
        return this;
    }

    public PageResponse<T> hasNext(Boolean hasNext){
        this.hasNext = hasNext;
        return this;
    }

    public PageResponse<T> totalElements(Long totalElements){
        this.totalElements = totalElements;
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

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
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

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }
}
