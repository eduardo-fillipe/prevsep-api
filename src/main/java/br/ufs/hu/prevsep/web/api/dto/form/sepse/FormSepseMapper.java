package br.ufs.hu.prevsep.web.api.dto.form.sepse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FormSepseMapper {
    FormSepseMapper INSTANCE = Mappers.getMapper(FormSepseMapper.class);

}
