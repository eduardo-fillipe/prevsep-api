package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.FormularioSepseMedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorFormRepository extends PrevSepRepository<FormularioSepseMedicoEntity, Integer> {
}