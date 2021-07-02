package br.ufs.hu.prevsep.web.api.service.form.sepse;

import br.ufs.hu.prevsep.web.api.dto.form.sepse.*;
import br.ufs.hu.prevsep.web.api.exception.FormNotFoundException;
import br.ufs.hu.prevsep.web.api.exception.InvalidFormStateException;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.repository.DoctorFormRepository;
import br.ufs.hu.prevsep.web.api.repository.NurseForm1Repository;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface SepseFormService {


    /**
     * Returns all doctor forms given a criteria.
     *
     * @param request the list of criteria.
     * @return All doctor forms found.
     */
    PageDoctorFormDTO getDoctorForms(PageableDoctorFormDTO request);

    /**
     * Returns all pending doctor forms given a criteria.
     *
     * @param request the list of criteria.
     * @return All pending doctor forms found.
     */
    PageDoctorFormDTO getPendingDoctorForms(Integer crm);

    /**
     * Returns a PDF reporting the lasts 30 days Sepse occurences.
     *
     * @return Byte Array representing the PDF
     */
    byte[] getReportLast30Days() throws JRException, SQLException;

    /**
     * Returns all nurses forms (first kind) given a criteria.
     *
     * @param request the list of criteria.
     * @return All nurses forms found during the search.
     */
    PageNurseForm1DTO getNurseForm1(PageableNurseForm1DTO request);

    /**
     * Returns all nurses forms (second kind) given a criteria.
     *
     * @param request the list of criteria.
     * @return All nurses forms found during the search.
     */
    PageNurseForm2DTO getNurseForm2(PageableNurseForm2DTO request);

    /**
     * This method creates a Sepse Form in the system. And, depending on the flag {@code finalizado} on the request object,
     * finishes the form filling, otherwise just saves the form state
     * @param cre The CRE number of the Nurse responsible for create this form
     * @param nurseForm1CreateDTO The form object
     * @return The form object created on the system, containing the generated Patient and Form Ids
     */
    NurseForm1DTO createForm(Integer cre, NurseForm1CreateDTO nurseForm1CreateDTO);

    /**
     * This method creates a Sepse Form in the system. The main flow is: Finishes a form in the {@link NurseForm1Repository}
     * and then creates the Doctor form associated to it in the {@link DoctorFormRepository}
     * @param cre The CRE number of the Nurse responsible for create this form
     * @param nurseForm1CreateDTO The form object
     * @param idForm The Form's id
     * @return The form object created on the system, containing the generated Patient and Form Ids
     */
    NurseForm1DTO finishForm1(Integer idForm, Integer cre, NurseForm1UpdateDTO nurseForm1CreateDTO);

    NurseForm1DTO saveForm1(Integer idForm, Integer cre, NurseForm1UpdateDTO nurseForm1CreateDTO);

    /**
     * This method finishes filling an existing Doctor Form. To do so, first is necessary to check if the form isn't
     * already finished ({@code br.ufs.hu.prevsep.web.api.dto.form.FormStatus.FINISHED}). If not, than the form is updated
     * in the database, your status is updated to FINISHED and the second for the nurse is created.
     *
     * @param idForm the target form's id
     * @param doctorFormUpdateDTO Doctor form object
     * @return The updated form.
     */
    DoctorFormDTO finishDoctorForm(Integer idForm, DoctorFormUpdateDTO doctorFormUpdateDTO)
            throws FormNotFoundException, InvalidFormStateException;


    DoctorFormDTO saveDoctorForm(Integer idForm, DoctorFormUpdateDTO doctorFormUpdateDTO)
            throws FormNotFoundException, InvalidFormStateException;

    /**
     * This method finishes filling and existing Nurse Form 2. To do so, first is necessary to check if the form isn't
     * already finished ({@code br.ufs.hu.prevsep.web.api.dto.form.FormStatus.FINISHED}) and whether the Doctor is FINISHED.
     * If so, than the form is updated in the database, your status is updated to FINISHED and the Nurse Form 1 of this form
     * is updated to finished.
     *
     * @param cre The CRE of the Nurse filling this form
     * @param idForm The id of the form to be fill
     * @param nurseForm2UpdateDTO The form object
     * @return The updated form in the database
     * @throws FormNotFoundException if the form was not found
     * @throws InvalidFormStateException if the form can't be filled due to his state
     * @throws UserNotFoundException if the given nurse do not existes
     */
    NurseForm2DTO finishNurseForm2(Integer cre, Integer idForm, NurseForm2UpdateDTO nurseForm2UpdateDTO)
            throws FormNotFoundException, InvalidFormStateException, UserNotFoundException;


    NurseForm2DTO saveNurseForm2(Integer cre, Integer idForm, NurseForm2UpdateDTO nurseForm2UpdateDTO)
            throws FormNotFoundException, InvalidFormStateException, UserNotFoundException;
}
