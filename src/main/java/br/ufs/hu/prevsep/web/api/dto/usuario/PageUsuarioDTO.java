package br.ufs.hu.prevsep.web.api.dto.usuario;

import br.ufs.hu.prevsep.web.api.dto.PageResponse;

public class PageUsuarioDTO extends PageResponse<UsuarioDTO> {
    public PageUsuarioDTO(PageResponse<UsuarioDTO> pageResponse) {
        super(pageResponse);
    }

    public static PageUsuarioDTO of(PageResponse<UsuarioDTO> pageResponse) {
        return new PageUsuarioDTO(pageResponse);
    }
}
