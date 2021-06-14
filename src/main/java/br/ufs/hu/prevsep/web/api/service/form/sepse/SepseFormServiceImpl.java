package br.ufs.hu.prevsep.web.api.service.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.sepse.FormSepseMapper;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm1CreateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm1DTO;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.EnfermeiroEntity;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseEnf1Entity;
import br.ufs.hu.prevsep.web.api.model.MedicoEntity;
import br.ufs.hu.prevsep.web.api.model.PacienteEntity;
import br.ufs.hu.prevsep.web.api.repository.DoctorRepository;
import br.ufs.hu.prevsep.web.api.repository.EnfermeiroRepository;
import br.ufs.hu.prevsep.web.api.repository.NurseForm1Repository;
import br.ufs.hu.prevsep.web.api.repository.PatientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SepseFormServiceImpl implements SepseFormService {

    private final EnfermeiroRepository enfermeiroRepository;
    private final DoctorRepository doctorRepository;
    private final FormSepseMapper formSepseMapper = FormSepseMapper.INSTANCE;
    private final NurseForm1Repository nurseForm1Repository;
    private final PatientRepository patientRepository;


    public SepseFormServiceImpl(EnfermeiroRepository enfermeiroRepository, DoctorRepository doctorRepository, NurseForm1Repository nurseForm1Repository, PatientRepository patientRepository) {
        this.enfermeiroRepository = enfermeiroRepository;
        this.doctorRepository = doctorRepository;
        this.nurseForm1Repository = nurseForm1Repository;
        this.patientRepository = patientRepository;
    }


    @Override
    @Transactional
    public NurseForm1DTO createForm(Integer cre, NurseForm1CreateDTO nurseForm1CreateDTO) {
        EnfermeiroEntity enfermeiroEntity = enfermeiroRepository.findFirstActiveByCre(cre);
        if (enfermeiroEntity == null)
            throw new UserNotFoundException().withMessage("Nurse not found.");

        MedicoEntity medicoEntity = doctorRepository.findFirstByCrm(nurseForm1CreateDTO.getCrmMedico());
        if (medicoEntity == null)
            throw new UserNotFoundException().withMessage("Doctor not found.");

        FormularioSepseEnf1Entity formularioSepseEnf1Entity =
                formSepseMapper.mapToFormularioSepseEnf1Entity(nurseForm1CreateDTO);

        formularioSepseEnf1Entity.setEnfermeiro(enfermeiroEntity);
        formularioSepseEnf1Entity.setMedico(medicoEntity);

        PacienteEntity pacienteEntity = patientRepository.save(formularioSepseEnf1Entity.getPaciente());
        formularioSepseEnf1Entity.setPaciente(pacienteEntity);

        FormularioSepseEnf1Entity entity = nurseForm1Repository.save(formularioSepseEnf1Entity);

        return formSepseMapper.mapNurseForm1Dto(
            entity
        );
    }
}
