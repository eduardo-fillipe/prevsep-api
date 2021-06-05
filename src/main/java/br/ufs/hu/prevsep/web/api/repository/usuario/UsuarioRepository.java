package br.ufs.hu.prevsep.web.api.repository.usuario;

import br.ufs.hu.prevsep.web.api.model.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, String> {
    UsuarioEntity getUsuarioEntityByEmail(String email);
}
