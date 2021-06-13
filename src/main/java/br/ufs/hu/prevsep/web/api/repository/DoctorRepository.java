package br.ufs.hu.prevsep.web.api.repository;

import br.ufs.hu.prevsep.web.api.model.MedicoEntity;

public interface DoctorRepository extends PrevSepRepository<MedicoEntity, String> {
    MedicoEntity findFirstByCrm(Integer crm);
}
