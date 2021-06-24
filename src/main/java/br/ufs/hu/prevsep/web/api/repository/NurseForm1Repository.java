package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.FormularioSepseEnf1Entity;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NurseForm1Repository extends PrevSepRepository<FormularioSepseEnf1Entity, Integer> {
    @Query("select max(t.idFormulario) from FormularioSepseEnf1Entity as t")
    Optional<Integer> findMaxIdformulario();
}
