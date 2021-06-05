package br.ufs.hu.prevsep.web.api.repository.usuario;

import br.ufs.hu.prevsep.web.api.model.MedicoEntity;
import org.springframework.data.repository.CrudRepository;

public interface MedicoRepository extends CrudRepository<MedicoEntity, String> {
    MedicoEntity findFirstByCrm(Integer crm);
}
