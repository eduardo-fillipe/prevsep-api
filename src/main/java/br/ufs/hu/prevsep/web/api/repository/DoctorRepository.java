package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.MedicoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends PrevSepRepository<MedicoEntity, String> {
    MedicoEntity findFirstByCrm(Integer crm);

    @Query("select m from MedicoEntity as m inner join UsuarioEntity as u on u.cpf = m.cpf where m.crm = :crm and u.status = 1")
    MedicoEntity findFirstActiveByCrm(@Param("crm") Integer crm);
}
