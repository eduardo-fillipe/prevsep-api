package br.ufs.hu.prevsep.web.api.service.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.FormSepseMapper;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm1CreateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm1DTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.exception.InvalidDoctorState;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseEnf1Entity;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseMedicoEntity;
import br.ufs.hu.prevsep.web.api.model.PacienteEntity;
import br.ufs.hu.prevsep.web.api.repository.DoctorFormRepository;
import br.ufs.hu.prevsep.web.api.repository.NurseForm1Repository;
import br.ufs.hu.prevsep.web.api.repository.PatientRepository;
import br.ufs.hu.prevsep.web.api.service.user.doctor.DoctorService;
import br.ufs.hu.prevsep.web.api.service.user.nurse.NurseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;

@Service
public class SepseFormServiceImpl implements SepseFormService {

    private final FormSepseMapper formSepseMapper = FormSepseMapper.INSTANCE;
    private final NurseForm1Repository nurseForm1Repository;
    private final PatientRepository patientRepository;
    private final NurseService nurseService;
    private final DoctorService doctorService;
    private final DoctorFormRepository doctorFormRepository;


    public SepseFormServiceImpl(NurseForm1Repository nurseForm1Repository, PatientRepository patientRepository,
                                NurseService nurseService, DoctorService doctorService,
                                DoctorFormRepository doctorFormRepository) {
        this.nurseForm1Repository = nurseForm1Repository;
        this.patientRepository = patientRepository;
        this.nurseService = nurseService;
        this.doctorService = doctorService;
        this.doctorFormRepository = doctorFormRepository;
    }


    /**
     * This method creates a Sepse Form in the system. The main flow is: Create a form in the {@link NurseForm1Repository}
     * and then the Doctor form associated to it in the {@link DoctorFormRepository}.
     * @param cre The CRE number of the Nurse responsible for create this form.
     * @param nurseForm1CreateDTO The form object
     * @return The form object created on the system, containing the generated Patient and Form Ids.
     */
    @Override
    @Transactional
    public NurseForm1DTO createForm(Integer cre, NurseForm1CreateDTO nurseForm1CreateDTO) {
        nurseService.getNurseByCRE(cre)
                .orElseThrow(() -> new UserNotFoundException().withMessage("Nurse not found."));
        DoctorResponseFullDTO doctor = doctorService
                .getMedicByCRM(nurseForm1CreateDTO.getCrmMedico())
                .orElseThrow(() -> new UserNotFoundException().withMessage("Doctor not found."));

        if (!StatusUsuarioEnum.ATIVO.equals(doctor.getUserInfo().getStatus()))
            throw new InvalidDoctorState()
                    .withDetailedMessage("Invalid state: " + doctor.getUserInfo().getStatus().toString());

        FormularioSepseEnf1Entity formularioSepseEnf1Entity =
                formSepseMapper.mapToFormularioSepseEnf1Entity(nurseForm1CreateDTO);

        PacienteEntity pacienteEntity = patientRepository.save(formularioSepseEnf1Entity.getPaciente());

        formularioSepseEnf1Entity.setPaciente(pacienteEntity);
        formularioSepseEnf1Entity.setCreEnfermeiro(cre);
        formularioSepseEnf1Entity.setStatus(FormStatus.CREATED.getValue());
        FormularioSepseEnf1Entity formSepse1 = nurseForm1Repository.save(formularioSepseEnf1Entity);

        FormularioSepseMedicoEntity doctorFormEntity = new FormularioSepseMedicoEntity();
        doctorFormEntity.setIdFormulario(formSepse1.getIdFormulario());
        doctorFormEntity.setPaciente(pacienteEntity);
        doctorFormEntity.setStatus(FormStatus.PENDING.getValue());
        doctorFormEntity.setCrmMedico(doctor.getCrm());
        doctorFormEntity.setDtCriacao(new Date(System.currentTimeMillis()));
        doctorFormRepository.save(doctorFormEntity);

        return formSepseMapper.mapNurseForm1Dto(formSepse1);
    }
}
