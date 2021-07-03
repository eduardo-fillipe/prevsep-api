package br.ufs.hu.prevsep.web.api.dto.security;

import br.ufs.hu.prevsep.web.api.dto.PageResponse;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

public class PageUsuarioLoginLogDTO extends PageResponse<UsuarioLoginLogDTO> {
    public PageUsuarioLoginLogDTO(@NotNull Page<UsuarioLoginLogDTO> page) {
        super(page);
    }

    public static PageUsuarioLoginLogDTO of(Page<UsuarioLoginLogDTO> page) {
        return new PageUsuarioLoginLogDTO(page);
    }
}
