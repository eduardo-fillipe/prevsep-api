package br.ufs.hu.prevsep.web.api.dto.fault;

import java.util.Objects;

public class FieldErrorDTO {
    String field;
    String error;

    public FieldErrorDTO() {
    }

    public FieldErrorDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public FieldErrorDTO(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldErrorDTO that = (FieldErrorDTO) o;
        return field.equals(that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}
