package br.ufs.hu.prevsep.web.api.repository.usuario;

import br.ufs.hu.prevsep.web.api.model.EnfermeiroEntity;
import org.springframework.data.repository.CrudRepository;

public interface EnfermeiroRepository extends CrudRepository<EnfermeiroEntity, String> {
    EnfermeiroEntity findFirstByCre(Integer cre);
}
