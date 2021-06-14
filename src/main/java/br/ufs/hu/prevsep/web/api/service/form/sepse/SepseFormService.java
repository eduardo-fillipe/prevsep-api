package br.ufs.hu.prevsep.web.api.service.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm1CreateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm1DTO;

public interface SepseFormService {
    NurseForm1DTO createForm(Integer cre, NurseForm1CreateDTO nurseForm1CreateDTO);
}
