package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.MedicoEntity;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<MedicoEntity, String> {
    MedicoEntity findFirstByCrm(Integer crm);
}
