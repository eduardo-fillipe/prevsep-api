package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, String>,
        JpaSpecificationExecutor<UsuarioEntity> {
    UsuarioEntity getUsuarioEntityByEmail(String email);
}
