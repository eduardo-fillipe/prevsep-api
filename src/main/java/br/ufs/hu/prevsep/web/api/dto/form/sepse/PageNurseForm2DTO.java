package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.PageResponse;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

public class PageNurseForm2DTO extends PageResponse<NurseForm2DTO> {

    public PageNurseForm2DTO(@NotNull Page<NurseForm2DTO> page) {
        super(page);
    }

    public static PageNurseForm2DTO of(@NotNull Page<NurseForm2DTO> page) {
        return new PageNurseForm2DTO(page);
    }
}
