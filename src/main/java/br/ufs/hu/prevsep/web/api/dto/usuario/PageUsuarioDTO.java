package br.ufs.hu.prevsep.web.api.dto.usuario;

import br.ufs.hu.prevsep.web.api.dto.PageResponse;
import org.springframework.data.domain.Page;

public class PageUsuarioDTO extends PageResponse<UsuarioDTO> {
    public PageUsuarioDTO(Page<UsuarioDTO> page) {
        super(page);
    }

    public static PageUsuarioDTO of(Page<UsuarioDTO> page) {
        return new PageUsuarioDTO(page);
    }
}
