package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.EnfermeiroEntity;

public interface EnfermeiroRepository extends PrevSepRepository<EnfermeiroEntity, String> {
    EnfermeiroEntity findFirstByCre(Integer cre);
}
