package br.ufs.hu.prevsep.web.api.service.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.FormStatus;
import br.ufs.hu.prevsep.web.api.dto.form.PatientCreateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.*;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.exception.*;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.*;
import br.ufs.hu.prevsep.web.api.repository.DoctorFormRepository;
import br.ufs.hu.prevsep.web.api.repository.NurseForm1Repository;
import br.ufs.hu.prevsep.web.api.repository.NurseForm2Repository;
import br.ufs.hu.prevsep.web.api.repository.PatientRepository;
import br.ufs.hu.prevsep.web.api.service.user.doctor.DoctorService;
import br.ufs.hu.prevsep.web.api.service.user.nurse.NurseService;
import br.ufs.hu.prevsep.web.api.utils.BeanUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private final JasperReport report30Days;
    private final DataSource dataSource;

    public SepseFormServiceImpl(NurseForm1Repository nurseForm1Repository, PatientRepository patientRepository,
                                NurseService nurseService, DoctorService doctorService,
                                DoctorFormRepository doctorFormRepository, NurseForm2Repository nurseForm2Repository,
                                DataSource dataSource) throws JRException {
        this.dataSource = dataSource;

        report30Days =  JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream("reports/SepseReport.jrxml"));

        this.nurseForm1Repository = nurseForm1Repository;
        this.patientRepository = patientRepository;
        this.nurseService = nurseService;
        this.doctorService = doctorService;
        this.doctorFormRepository = doctorFormRepository;
        this.nurseForm2Repository = nurseForm2Repository;
    }

    @Override
    public byte[] getReportLast30Days() throws JRException, SQLException {
        try (Connection con = dataSource.getConnection()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JasperPrint jp = JasperFillManager.fillReport(report30Days, new HashMap<>(), con);
            JasperExportManager.exportReportToPdfStream(jp, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }

    @Override
    public PageNurseForm1DTO getNurseForm1(PageableNurseForm1DTO request) {
        if (request == null)
            request = new PageableNurseForm1DTO();

        return PageNurseForm1DTO.of(nurseForm1Repository.findAll(
                request.getQueryPredicate(QFormularioSepseEnf1Entity.formularioSepseEnf1Entity),
                request).map(formSepseMapper::mapToNurseForm1Dto));
    }

    @Override
    public PageDoctorFormDTO getDoctorForms(PageableDoctorFormDTO request) {
        if (request == null)
            request = new PageableDoctorFormDTO();

        return PageDoctorFormDTO.of(doctorFormRepository.findAll(
                request.getQueryPredicate(QFormularioSepseMedicoEntity.formularioSepseMedicoEntity),
                request).map(formSepseMapper::mapToDoctorFormDto));
    }

    @Override
    public PageDoctorFormDTO getPendingDoctorForms(Integer crm) {
        PageableDoctorFormDTO request = new PageableDoctorFormDTO();
        request.setCrmMedico(crm);
        ArrayList<FormStatus> formStatuses = new ArrayList<>();
        formStatuses.add(FormStatus.PENDING);
        formStatuses.add(FormStatus.SAVED);
        request.setStatus(formStatuses);

        return PageDoctorFormDTO.of(doctorFormRepository.findAll(
            request.getQueryPredicate(QFormularioSepseMedicoEntity.formularioSepseMedicoEntity),
            request).map(formSepseMapper::mapToDoctorFormDto));
    }


    @Override
    public PageNurseForm2DTO getNurseForm2(PageableNurseForm2DTO request) {
        if (request == null)
            request = new PageableNurseForm2DTO();

        return PageNurseForm2DTO.of(nurseForm2Repository.findAll(
                request.getQueryPredicate(QFormularioSepseEnf2Entity.formularioSepseEnf2Entity),
                request).map(formSepseMapper::mapToNurseForm2Dto));
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

        if (nurseForm1CreateDTO.getFinalizado())
            validateForm1(formularioSepseEnf1Entity);

        formularioSepseEnf1Entity.setDtAcMedico(new Date(System.currentTimeMillis()));
        formularioSepseEnf1Entity.setDtCriacao(new Date(System.currentTimeMillis()));

        PacienteEntity pacienteEntity = patientRepository.save(formularioSepseEnf1Entity.getPaciente());
        formularioSepseEnf1Entity.setPaciente(pacienteEntity);
        formularioSepseEnf1Entity.setCreEnfermeiro(cre);
        formularioSepseEnf1Entity.setStatus(nurseForm1CreateDTO.getFinalizado() ?
                FormStatus.CREATED.getValue() : FormStatus.SAVED.getValue());

        FormularioSepseEnf1Entity formSepse1 = nurseForm1Repository.save(formularioSepseEnf1Entity);

        if (nurseForm1CreateDTO.getFinalizado())
            createDoctorForm(formSepse1);

        return formSepseMapper.mapNurseForm1Dto(formularioSepseEnf1Entity);
    }

    private void validateForm1(FormularioSepseEnf1Entity formularioSepseEnf1Entity) {
        FormValidationException ex = new FormValidationException();

        if (formularioSepseEnf1Entity.getPaciente() == null){
            ex.withFieldError("paciente", "Can't be null");
        } else {
            PacienteEntity patient = formularioSepseEnf1Entity.getPaciente();
            if(patient.getCpf() == null)
                ex.withFieldError("paciente.cpf", "Can't be null");
            if (patient.getIdade() <= 0)
                ex.withFieldError("paciente.idade", "Must be higher then 0");
            if(patient.getLeito() == null)
                ex.withFieldError("paciente.leito", "Can't be null");
            if(patient.getNrAtendimento() == null)
                ex.withFieldError("paciente.nrAtendimento", "Can't be null");
            if(patient.getRegistro() == null)
                ex.withFieldError("paciente.registro", "Can't be null");
            if(patient.getSexo() == null)
                ex.withFieldError("paciente.sexo", "Can't be null");
        }

        if (formularioSepseEnf1Entity.getProcedencia() == null)
            ex.withFieldError("paciente.procedencia", "Can't be null");

        if (ex.getFieldErrors() != null)
            throw ex;
    }

    @Override
    public NurseForm1DTO saveForm1(Integer idForm, Integer cre, NurseForm1UpdateDTO nurseForm1UpdateDTO) {
        return saveForm1(idForm, cre, nurseForm1UpdateDTO, false);
    }

    @Override
    public NurseForm1DTO finishForm1(Integer idForm, Integer cre, NurseForm1UpdateDTO nurseForm1CreateDTO) {
        return saveForm1(idForm, cre, nurseForm1CreateDTO, true);
    }

    private NurseForm1DTO saveForm1(Integer idForm, Integer cre, NurseForm1UpdateDTO nurseForm1UpdateDTO, boolean finish) {
        FormularioSepseEnf1Entity formularioSepseEnf1Entity = nurseForm1Repository
                .findById(idForm).orElseThrow(FormNotFoundException::new);
        PacienteEntity pacienteEntity = formularioSepseEnf1Entity.getPaciente();

        if (pacienteEntity == null)
            throw new PatientNotFoundException()
                    .withDetailedMessage("The data flow of this form is probably corrupted. Please contact the PrevSep Team.");

        if (!FormStatus.SAVED.equals(FormStatus.fromValue(formularioSepseEnf1Entity.getStatus())))
            throw new InvalidFormStateException()
                    .withMessage("This form can't be modified.")
                    .withDetailedMessage("Form with state " + FormStatus.fromValue(formularioSepseEnf1Entity.getStatus()) + " can not be modified.");

        mergeEntity(formularioSepseEnf1Entity, nurseForm1UpdateDTO);

        if (finish) {
            validateForm1(formularioSepseEnf1Entity);
            formularioSepseEnf1Entity.setDtAcMedico(new Date(System.currentTimeMillis()));
            formularioSepseEnf1Entity.setDtCriacao(new Date(System.currentTimeMillis()));
        }

        formularioSepseEnf1Entity.setStatus(finish ? FormStatus.CREATED.getValue() : FormStatus.SAVED.getValue());

        FormularioSepseEnf1Entity formSepse1 = nurseForm1Repository.save(formularioSepseEnf1Entity);

        if (finish)
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
        doctorFormEntity.setFocoInfeccioso(new FormularioSepseMedicoFocoInfecciosoEntity());
        doctorFormEntity.setBundleHora1(new FormularioSepseMedicoBundleEntity());
        doctorFormEntity.setCriterioExclusao(new FormularioSepseMedicoCriterioExclusaoEntity());
        doctorFormEntity.setReavaliacoesSeriadas(new FormularioSepseMedicoReavaliacoesSeriadasEntity());
        doctorFormEntity.setIdFormulario(formSepse1.getIdFormulario());
        doctorFormEntity.setIdPaciente(formSepse1.getPaciente().getIdPaciente());
        doctorFormEntity.setStatus(FormStatus.PENDING.getValue());
        doctorFormEntity.setCrmMedico(formSepse1.getCrmMedico());
        doctorFormEntity.setDtCriacao(new Date(System.currentTimeMillis()));
        doctorFormRepository.save(doctorFormEntity);
    }

    @Override
    public DoctorFormDTO saveDoctorForm(Integer idForm, DoctorFormUpdateDTO doctorFormUpdateDTO) throws FormNotFoundException, InvalidFormStateException {
        return saveDoctorForm(idForm, doctorFormUpdateDTO, false);
    }

    @Override
    @Transactional
    public DoctorFormDTO finishDoctorForm(Integer idForm, DoctorFormUpdateDTO doctorFormUpdateDTO)
            throws FormNotFoundException, InvalidFormStateException {
         return saveDoctorForm(idForm, doctorFormUpdateDTO, true);
    }

    private DoctorFormDTO saveDoctorForm(Integer idForm, DoctorFormUpdateDTO doctorFormUpdateDTO, boolean finished) throws FormNotFoundException, InvalidFormStateException {

        if (finished)
            validateDoctorForm(doctorFormUpdateDTO).ifPresent(e -> {
                throw e;
            });

        FormularioSepseMedicoEntity doctorFormEntity = doctorFormRepository
                .findById(idForm)
                .orElseThrow(FormNotFoundException::new);

        if (FormStatus.FINISHED.equals(FormStatus.fromValue(doctorFormEntity.getStatus())))
            throw new InvalidFormStateException()
                    .withMessage("This form can't be modified.")
                    .withDetailedMessage("Form with state " + FormStatus.fromValue(doctorFormEntity.getStatus()) + "can not be modified.");

        mergeEntity(doctorFormEntity, doctorFormUpdateDTO);
        doctorFormEntity.setStatus(finished ? FormStatus.FINISHED.getValue() : FormStatus.SAVED.getValue());

        doctorFormEntity = doctorFormRepository.save(doctorFormEntity);

        if(finished)
            createNurseForm2(doctorFormEntity);

        DoctorFormDTO result = formSepseMapper.mapToDoctorFormDto(doctorFormEntity);
        result.setIdPaciente(doctorFormEntity.getIdPaciente());

        return result;
    }

    private Optional<PrevSepException> validateDoctorForm(DoctorFormUpdateDTO form) {
        if (form == null)
            return Optional.of(new FormValidationException().withDetailedMessage("The form can not be null."));

        // Criterio de exclusão
        if (form.getCriterioExclusao() != null) {
            if (form.getCriterioExclusao().getApresentaCriterioExclusao()) {
                if (!(form.getCriterioExclusao().getDoencaAtipica() || form.getCriterioExclusao().getFimDeVida() || form.getCriterioExclusao().getProbabilidadeSepseBaixa())) {
                    return Optional.of(new FormValidationException()
                            .withMessage("This form is invalid.")
                            .withDetailedMessage("The patient presents a exclusion criteria, but none is selected."));
                }
            } else {
                if (form.getCriterioExclusao().getDoencaAtipica() || form.getCriterioExclusao().getFimDeVida() || form.getCriterioExclusao().getProbabilidadeSepseBaixa()) {
                    return Optional.of(new FormValidationException()
                            .withMessage("This form is invalid.")
                            .withDetailedMessage("The patient do not presents a exclusion criteria, but a criteria is selected."));
                }
            }
        }

        // Bundle Hora 1
        if (form.getBundleHora1() != null) {
            if (form.getBundleHora1().getIniciado()) {
                if (form.getBundleHora1().getJustificativaNao() != null && !form.getBundleHora1().getJustificativaNao().isBlank())
                    return Optional.of(new FormValidationException().withDetailedMessage("The protocol was initiated, but the field 'justificaNao' was filled."));

                if (checkIfAllFieldsAreFalse(form.getBundleHora1())) {
                    return Optional.of(new FormValidationException()
                            .withDetailedMessage("The protocol was initiated, but no action was selected in the form."));
                }
            } else {
                if (form.getBundleHora1().getJustificativaNao() == null || form.getBundleHora1().getJustificativaNao().isBlank()) {
                    return Optional.of(new FormValidationException()
                            .withDetailedMessage("The protocol was not initiated, but the justification is empty."));
                }
                if (!checkIfAllFieldsAreFalse(form.getBundleHora1())) {
                    return Optional.of(new FormValidationException()
                            .withDetailedMessage("The protocol was not initiated, but one or more fields are filled."));
                }
            }
        } else {
            return Optional.of(new FormValidationException()
                    .withDetailedMessage("The First Hour Bundle can not be null. It must have, at least, an justification, if the protocol was not initiated."));
        }

        // Reavaliações Seriadas
        if (form.getReavaliacoesSeriadas() != null) {
            if (form.getReavaliacoesSeriadas().getAplicada()) {
                if (form.getReavaliacoesSeriadas().getJustificativaNao() != null && !form.getReavaliacoesSeriadas().getJustificativaNao().isBlank())
                    return Optional.of(new FormValidationException().withDetailedMessage("The revalidations were applied, but the field 'justificaNao' was filled."));

                if (checkIfAllFieldsAreFalse(form.getReavaliacoesSeriadas()))
                    return Optional.of(new FormValidationException().withDetailedMessage("The revalidations were applied, but no action was selected in the form."));

                if (form.getReavaliacoesSeriadas().getqSofa()) {
                    if (checkIfAllFieldsAreFalseQSofa(form.getReavaliacoesSeriadas())) {
                        return Optional.of(new FormValidationException().withDetailedMessage("The field qSofa was marked as true, but no related fields (pas, fr, rebaixamentoConsiencia) were marked as true."));
                    }
                } else {
                    if (checkIfAllFieldsAreFalseQSofa(form.getReavaliacoesSeriadas())) {
                        return Optional.of(new FormValidationException().withDetailedMessage("The field qSofa was marked as false, but related fields (pas, fr, rebaixamentoConsiencia) were marked as true."));
                    }
                }
            } else {
                if (form.getReavaliacoesSeriadas().getJustificativaNao() == null || form.getReavaliacoesSeriadas().getJustificativaNao().isBlank()) {
                    return Optional.of(new FormValidationException().withDetailedMessage("The revalidations were not applied, but the justification is empty."));
                }
                if (!checkIfAllFieldsAreFalse(form.getReavaliacoesSeriadas())) {
                    return Optional.of(new FormValidationException().withDetailedMessage("The revalidations were not applied, but one or more fields were selected as applied."));
                }

            }
        } else {
            return Optional.of(new FormValidationException()
                    .withDetailedMessage("The revalidations can not be null. It must have, at least, an justification, if it was not applied."));
        }

        return Optional.empty();
    }

    private boolean checkIfAllFieldsAreFalseQSofa(FormularioSepseMedicoReavaliacoesSeriadasDTO reavaliacoesSeriadasDTO) {
        return !(reavaliacoesSeriadasDTO.getPas100Mmghg() ||
                reavaliacoesSeriadasDTO.getFr22Rpm() ||
                reavaliacoesSeriadasDTO.getRebaixamentoNivelConsiencia());
    }

    private boolean checkIfAllFieldsAreFalse(FormularioSepseMedicoBundleDTO bundleDTO) {
        return bundleDTO.getDtDisparo() == null &&
                bundleDTO.getAntibioticoAmploAspectro() == null &&
                bundleDTO.getHemoculturaDtColeta() == null &&
                bundleDTO.getLactoDtColeta() == null &&
                !bundleDTO.getCristaloides() && !bundleDTO.getVasopressores();
    }

    private boolean checkIfAllFieldsAreFalse(FormularioSepseMedicoReavaliacoesSeriadasDTO reavaliacoesSeriadasDTO) {
        return !reavaliacoesSeriadasDTO.getqSofa() &&
                !reavaliacoesSeriadasDTO.getPas100Mmghg() &&
                !reavaliacoesSeriadasDTO.getFr22Rpm() &&
                !reavaliacoesSeriadasDTO.getRebaixamentoNivelConsiencia() &&
                !reavaliacoesSeriadasDTO.getLactoInicialmenteAlto() &&
                reavaliacoesSeriadasDTO.getOutros() == null;
    }

    private void mergeEntity(FormularioSepseMedicoEntity entity, DoctorFormUpdateDTO doctorFormUpdateDTO) {
        BeanUtils.copyPropertiesIgnoreNulls(doctorFormUpdateDTO.getBundleHora1(), entity.getBundleHora1(), false);
        BeanUtils.copyPropertiesIgnoreNulls(doctorFormUpdateDTO.getCriterioExclusao(), entity.getCriterioExclusao(), false);
        BeanUtils.copyPropertiesIgnoreNulls(doctorFormUpdateDTO.getFocoInfeccioso(), entity.getFocoInfeccioso(), false);
        BeanUtils.copyPropertiesIgnoreNulls(doctorFormUpdateDTO.getReavaliacoesSeriadas(), entity.getReavaliacoesSeriadas(), false);
    }

    private void mergeEntity(FormularioSepseEnf1Entity entity, NurseForm1UpdateDTO nurseFormUpdateDTO) {
        entity.setCrmMedico(nurseFormUpdateDTO.getCrmMedico());
        entity.setProcedencia(ProcedenciaDTO.toValue(nurseFormUpdateDTO.getProcedencia()));

        if (nurseFormUpdateDTO.getPaciente() == null)
            nurseFormUpdateDTO.setPaciente(new PatientCreateDTO());
        if (nurseFormUpdateDTO.getDisfOrganica() == null)
            nurseFormUpdateDTO.setDisfOrganica(new FormularioSepseEnf1DinsfOrgDTO());
        if (nurseFormUpdateDTO.getSirs() == null)
            nurseFormUpdateDTO.setSirs(new FormularioSepseEnf1SirsDTO());

        BeanUtils.copyPropertiesIgnoreNulls(entity.getPaciente(), nurseFormUpdateDTO.getPaciente(), false);
    }

    private void createNurseForm2(FormularioSepseMedicoEntity formularioSepseMedicoEntity) {
        FormularioSepseEnf1Entity nurseForm1 = nurseForm1Repository.findById(formularioSepseMedicoEntity.getIdFormulario())
                .orElseThrow(FormNotFoundException::new);
        FormularioSepseEnf2Entity nurseForm2 = new FormularioSepseEnf2Entity();
        nurseForm2.setDtCriacao(LocalDateTime.now());
        nurseForm2.setIdFormulario(nurseForm1.getIdFormulario());
        nurseForm2.setCreEnfermeiro(nurseForm1.getCreEnfermeiro());
        nurseForm2.setStatus(FormStatus.PENDING.getValue());
        nurseForm2Repository.save(nurseForm2);
    }

    @Override
    @Transactional
    public NurseForm2DTO finishNurseForm2(Integer cre, Integer idForm, NurseForm2UpdateDTO nurseForm2UpdateDTO)
            throws FormNotFoundException, InvalidFormStateException, UserNotFoundException {
        return saveNurseForm2(cre, idForm, nurseForm2UpdateDTO, true);
    }

    @Override
    public NurseForm2DTO saveNurseForm2(Integer cre, Integer idForm, NurseForm2UpdateDTO nurseForm2UpdateDTO)
            throws FormNotFoundException, InvalidFormStateException, UserNotFoundException {
        return saveNurseForm2(cre, idForm, nurseForm2UpdateDTO, false);
    }

    public NurseForm2DTO saveNurseForm2(Integer cre, Integer idForm, NurseForm2UpdateDTO nurseForm2UpdateDTO, boolean finish)
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

        mergeEntity(nurseForm2Entity, nurseForm2UpdateDTO);
        nurseForm2Entity.setStatus(finish ? FormStatus.FINISHED.getValue() : FormStatus.SAVED.getValue());

        FormularioSepseEnf1Entity formularioSepseEnf1Entity;
        if (finish) {
            if (!validateNurseForm2(nurseForm2Entity))
                throw new FormValidationException().withMessage("The form isn't correctly filled.")
                        .withDetailedMessage("The following fields are conflicting: ")
                        .withFieldError("dtAlta", "Can't have the same value of the field 'dtObito'")
                        .withFieldError("dtObito", "Can't have the same value of the field 'dtAlta'");

            nurseForm2Entity = nurseForm2Repository.save(nurseForm2Entity);
            formularioSepseEnf1Entity = updateStatusNurseForm1(nurseForm2Entity);
        } else {
            nurseForm2Entity = nurseForm2Repository.save(nurseForm2Entity);
        }

        return formSepseMapper.mapToNurseForm2Dto(nurseForm2Entity);
    }

    private FormularioSepseEnf1Entity updateStatusNurseForm1(FormularioSepseEnf2Entity formularioSepseEnf2Entity) {
        FormularioSepseEnf1Entity formularioSepseEnf1Entity = nurseForm1Repository
                .findById(formularioSepseEnf2Entity.getIdFormulario()).orElseThrow(FormNotFoundException::new);
        formularioSepseEnf1Entity.setStatus(FormStatus.FINISHED.getValue());
        return nurseForm1Repository.save(formularioSepseEnf1Entity);
    }

    private void mergeEntity(FormularioSepseEnf2Entity entity, NurseForm2UpdateDTO dto) {
        entity.setDtAlta(dto.getDtAlta() == null ? null : dto.getDtAlta());
        entity.setDtObito(dto.getDtObito() == null ? null : dto.getDtObito());
        entity.setDtUti(dto.getDtUti() == null ? null : dto.getDtUti());
    }

    private boolean validateNurseForm2(FormularioSepseEnf2Entity nurseForm) {
        return ((nurseForm.getDtAlta() != null && nurseForm.getDtObito() == null) ||
                (nurseForm.getDtObito() != null && nurseForm.getDtAlta() == null));
    }
}
