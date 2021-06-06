package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.ManagerEntity;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<ManagerEntity, String> {
}
