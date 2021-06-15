package br.ufs.hu.prevsep.web.api.service.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.*;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.exception.FormNotFoundException;
import br.ufs.hu.prevsep.web.api.exception.FormValidationException;
import br.ufs.hu.prevsep.web.api.exception.InvalidDoctorState;
import br.ufs.hu.prevsep.web.api.exception.InvalidFormStateException;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseEnf1Entity;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseEnf2Entity;
import br.ufs.hu.prevsep.web.api.model.FormularioSepseMedicoEntity;
import br.ufs.hu.prevsep.web.api.model.PacienteEntity;
import br.ufs.hu.prevsep.web.api.repository.DoctorFormRepository;
import br.ufs.hu.prevsep.web.api.repository.NurseForm1Repository;
import br.ufs.hu.prevsep.web.api.repository.NurseForm2Repository;
import br.ufs.hu.prevsep.web.api.repository.PatientRepository;
import br.ufs.hu.prevsep.web.api.service.user.doctor.DoctorService;
import br.ufs.hu.prevsep.web.api.service.user.nurse.NurseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

@Service
public class SepseFormServiceImpl implements SepseFormService {

    private final FormSepseMapper formSepseMapper = FormSepseMapper.INSTANCE;

    private final NurseForm1Repository nurseForm1Repository;
    private final DoctorFormRepository doctorFormRepository;
    private final NurseForm2Repository nurseForm2Repository;
    private final PatientRepository patientRepository;
    private final NurseService nurseService;
    private final DoctorService doctorService;


    public SepseFormServiceImpl(NurseForm1Repository nurseForm1Repository, PatientRepository patientRepository,
                                NurseService nurseService, DoctorService doctorService,
                                DoctorFormRepository doctorFormRepository, NurseForm2Repository nurseForm2Repository) {
        this.nurseForm1Repository = nurseForm1Repository;
        this.patientRepository = patientRepository;
        this.nurseService = nurseService;
        this.doctorService = doctorService;
        this.doctorFormRepository = doctorFormRepository;
        this.nurseForm2Repository = nurseForm2Repository;
    }


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

        createDoctorForm(formSepse1);

        return formSepseMapper.mapNurseForm1Dto(formSepse1);
    }

    /**
     * Creates the doctor form associated with a given {@link FormularioSepseEnf1Entity}
     * that must already exists in the system.
     * @param formSepse1 The Sepse Form
     */
    private void createDoctorForm(FormularioSepseEnf1Entity formSepse1) {
        FormularioSepseMedicoEntity doctorFormEntity = new FormularioSepseMedicoEntity();
        doctorFormEntity.setIdFormulario(formSepse1.getIdFormulario());
        doctorFormEntity.setIdPaciente(formSepse1.getPaciente().getIdPaciente());
        doctorFormEntity.setStatus(FormStatus.PENDING.getValue());
        doctorFormEntity.setCrmMedico(formSepse1.getCrmMedico());
        doctorFormEntity.setDtCriacao(new Date(System.currentTimeMillis()));
        doctorFormRepository.save(doctorFormEntity);
    }

    @Override
    @Transactional
    public DoctorFormDTO finishDoctorForm(Integer idForm, DoctorFormUpdateDTO doctorFormUpdateDTO)
            throws FormNotFoundException, InvalidFormStateException {
         FormularioSepseMedicoEntity doctorFormEntity = doctorFormRepository
                 .findById(idForm)
                 .orElseThrow(FormNotFoundException::new);

         if (FormStatus.FINISHED.equals(FormStatus.fromValue(doctorFormEntity.getStatus())))
             throw new InvalidFormStateException()
                     .withMessage("This form can't be modified.")
                     .withDetailedMessage("Form with state " + FormStatus.fromValue(doctorFormEntity.getStatus()) + "can not be modified.");

         mergeEntity(doctorFormEntity, doctorFormUpdateDTO);
         doctorFormEntity.setStatus(FormStatus.FINISHED.getValue());

        doctorFormEntity = doctorFormRepository.save(doctorFormEntity);

        createNurseForm2(doctorFormEntity);

         return formSepseMapper.mapToDoctorFormDto(doctorFormEntity);
    }

    private void mergeEntity(FormularioSepseMedicoEntity entity, DoctorFormUpdateDTO doctorFormUpdateDTO) {
        entity.setFocoInfeccioso(doctorFormUpdateDTO.getFocoInfeccioso());
        entity.setCritExclusao(doctorFormUpdateDTO.getCritExclusao());
        entity.setBundleHora1(doctorFormUpdateDTO.getBundleHora1());
        entity.setDtDispProtocolo(Date.valueOf(doctorFormUpdateDTO.getDtDispProtocolo()));
        entity.setDtColetaLactato(Date.valueOf(doctorFormUpdateDTO.getDtColetaLactato()));
        entity.setDtColetaHemocult(Date.valueOf(doctorFormUpdateDTO.getDtColetaHemocult()));
        entity.setDtPrimeiraDose(Date.valueOf(doctorFormUpdateDTO.getDtPrimeiraDose()));
        entity.setReavaliacoesSeriadas(doctorFormUpdateDTO.getReavaliacoesSeriadas());
    }

    private void createNurseForm2(FormularioSepseMedicoEntity formularioSepseMedicoEntity) {
        FormularioSepseEnf1Entity nurseForm1 = nurseForm1Repository.findById(formularioSepseMedicoEntity.getIdFormulario())
                .orElseThrow(FormNotFoundException::new);
        FormularioSepseEnf2Entity nurseForm2 = new FormularioSepseEnf2Entity();
        nurseForm2.setDtCriacao(new Date(System.currentTimeMillis()));
        nurseForm2.setIdFormulario(nurseForm1.getIdFormulario());
        nurseForm2.setCreEnfermeiro(nurseForm1.getCreEnfermeiro());
        nurseForm2.setStatus(FormStatus.PENDING.getValue());
        nurseForm2Repository.save(nurseForm2);
    }

    @Override
    @Transactional
    public NurseForm2DTO finishNurseForm2(Integer cre, Integer idForm, NurseForm2UpdateDTO nurseForm2UpdateDTO)
            throws FormNotFoundException, InvalidFormStateException, UserNotFoundException {
        Optional<FormularioSepseEnf2Entity> nurseForm2EntityOptional = nurseForm2Repository
                .findById(idForm);

        if (nurseForm2EntityOptional.isEmpty()) {
            FormularioSepseEnf1Entity formularioSepseEnf1Entity = nurseForm1Repository
                    .findById(idForm).orElseThrow(FormNotFoundException::new);

            if (FormStatus.CREATED.equals(FormStatus.fromValue(formularioSepseEnf1Entity.getStatus()))) {
                throw new InvalidFormStateException().withMessage("This form is not available for filling yet.")
                        .withDetailedMessage("The doctor part of this form has state PENDING.");
            }
            throw new FormNotFoundException();
        }

        FormularioSepseEnf2Entity nurseForm2Entity = nurseForm2EntityOptional.get();

        if (FormStatus.FINISHED.equals(FormStatus.fromValue(nurseForm2Entity.getStatus())))
            throw new InvalidFormStateException()
                    .withMessage("This form can't be modified.")
                    .withDetailedMessage("Form with state " + FormStatus.fromValue(nurseForm2Entity.getStatus()) + "can not be modified.");

        nurseForm2Entity.setStatus(FormStatus.FINISHED.getValue());
        mergeEntity(nurseForm2Entity, nurseForm2UpdateDTO);

        if (!validateNurseForm2(nurseForm2Entity))
            throw new FormValidationException().withMessage("The form isn't correctly filled.")
                    .withDetailedMessage("The following fields are conflicting: ")
                    .withFieldError("dtAlta", "")
                    .withFieldError("dtObito", "");

        nurseForm2Entity = nurseForm2Repository.save(nurseForm2Entity);

        FormularioSepseEnf1Entity formularioSepseEnf1Entity = updateStatusNurseForm1(nurseForm2Entity);

        NurseForm2DTO result = formSepseMapper.mapToNurseForm2Dto(nurseForm2Entity);
        result.setPatientDTO(formSepseMapper.mapToPatientDto(formularioSepseEnf1Entity.getPaciente()));

        return result;
    }

    private FormularioSepseEnf1Entity updateStatusNurseForm1(FormularioSepseEnf2Entity formularioSepseEnf2Entity) {
        FormularioSepseEnf1Entity formularioSepseEnf1Entity = nurseForm1Repository
                .findById(formularioSepseEnf2Entity.getIdFormulario()).orElseThrow(FormNotFoundException::new);
        formularioSepseEnf1Entity.setStatus(FormStatus.FINISHED.getValue());
        return nurseForm1Repository.save(formularioSepseEnf1Entity);
    }

    private void mergeEntity(FormularioSepseEnf2Entity entity, NurseForm2UpdateDTO dto) {
        entity.setDtAlta(Date.valueOf(dto.getDtAlta()));
        entity.setDtObito(Date.valueOf(dto.getDtObito()));
        entity.setDtUti(Date.valueOf(dto.getDtUti()));
    }

    private boolean validateNurseForm2(FormularioSepseEnf2Entity nurseForm) {
        return ((nurseForm.getDtAlta() != null && nurseForm.getDtObito() == null) ||
                (nurseForm.getDtObito() != null && nurseForm.getDtAlta() == null));
    }
}
