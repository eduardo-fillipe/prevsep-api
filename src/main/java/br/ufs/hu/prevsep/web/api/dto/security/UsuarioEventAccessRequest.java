package br.ufs.hu.prevsep.web.api.dto.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioEventAccessRequest {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtLoginBegin;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtLoginEnd;
    private List<String> cpfList;

    public LocalDateTime getDtLoginBegin() {
        return dtLoginBegin;
    }

    public void setDtLoginBegin(LocalDateTime dtLoginBegin) {
        this.dtLoginBegin = dtLoginBegin;
    }

    public LocalDateTime getDtLoginEnd() {
        return dtLoginEnd;
    }

    public void setDtLoginEnd(LocalDateTime dtLoginEnd) {
        this.dtLoginEnd = dtLoginEnd;
    }

    public List<String> getCpfList() {
        return cpfList;
    }

    public void setCpfList(List<String> cpfList) {
        this.cpfList = cpfList;
    }
}
