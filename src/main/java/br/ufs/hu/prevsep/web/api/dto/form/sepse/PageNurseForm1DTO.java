package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.PageResponse;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

public class PageNurseForm1DTO extends PageResponse<NurseForm1DTO> {

    public PageNurseForm1DTO(@NotNull Page<NurseForm1DTO> page) {
        super(page);
    }

    public static PageNurseForm1DTO of(Page<NurseForm1DTO> page) {
        return new PageNurseForm1DTO(page);
    }
}
