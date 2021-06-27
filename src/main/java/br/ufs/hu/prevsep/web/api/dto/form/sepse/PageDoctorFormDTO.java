package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.PageResponse;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

public class PageDoctorFormDTO extends PageResponse<DoctorFormDTO> {

    public PageDoctorFormDTO(@NotNull Page<DoctorFormDTO> page) {
        super(page);
    }

    public static PageDoctorFormDTO of(Page<DoctorFormDTO> page) {
        return new PageDoctorFormDTO(page);
    }
}
