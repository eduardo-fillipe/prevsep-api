package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.EnfermeiroEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnfermeiroRepository extends PrevSepRepository<EnfermeiroEntity, String> {
    EnfermeiroEntity findFirstByCre(Integer cre);

    @Query("select e from EnfermeiroEntity as e inner join UsuarioEntity as u on u.cpf = e.cpf where e.cre = :cre and u.status = 1")
    EnfermeiroEntity findFirstActiveByCre(@Param("cre") Integer cre);
}
