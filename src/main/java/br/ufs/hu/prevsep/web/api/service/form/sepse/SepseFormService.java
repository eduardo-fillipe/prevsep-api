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
     * Retorna todos os formulários de médico dado um critério.
     *
     * @param request a lista de critérios.
     * @return Todos os formulários de médico encontrados.
     */
    PageDoctorFormDTO getDoctorForms(PageableDoctorFormDTO request);

    /**
     * Retorna todos os formulários de médico pendentes dado um critério.
     *
     * @param request a lista de critérios.
     * @return Todos os formulários de médico pendentes encontrados.
     */
    PageDoctorFormDTO getPendingDoctorForms(Integer crm);

    /**
     * Retorna dos os formulários de enfermeiro pendentes (form2) dado o cre.
     *
     * @param Integer o cre.
     * @return Todos os formulários de enfermeiro pendentes encontrados.
     */
    PageNurseForm2DTO getPendingNurseForms(Integer cre);

    /**
     * Retorna um relatório em PDF dos últimos 30 dias de ocorrências Sepse.
     *
     * @return Byte Array representando o PDF
     */
    byte[] getReportLast30Days() throws JRException, SQLException;

    /**
     * Retorna todos os formulários de enfermeiro (primeiro tipo) dado um critério.
     *
     * @param request a lista de critérios.
     * @return Todos os formulários de enfermeiro encontrados.
     */
    PageNurseForm1DTO getNurseForm1(PageableNurseForm1DTO request);

    /**
     * Retorna todos os formulários de enfermeiro (segundo tipo) dado um critério.
     *
     * @param request a lista de critérios.
     * @return Todos os formulários de enfermeiro encontrados.
     */
    PageNurseForm2DTO getNurseForm2(PageableNurseForm2DTO request);

    /**
     * Esse método cria um Formulário Sepse no sistema. E, dependendo na flag {@code finalizado} no objecto de solicitação,
     * finaliza o preenchimento do formulário, caso contrário apenas salvo o estado do formulário
     * @param cre O CRE do enfermeiro reponsável pela criação desse formulário
     * @param nurseForm1CreateDTO O objecto do formulário
     * @return O objeto do formulário criado no sistema, contendo os ID's do Paciente e Formulário gerado
     */
    NurseForm1DTO createForm(Integer cre, NurseForm1CreateDTO nurseForm1CreateDTO);

    /**
     * Esse método cria um Formulário Sepse no sistema. O fluxo principal é: Finalizado um formulário no {@link NurseForm1Repository}
     * e então cria o formulário de médico associado a este no {@link DoctorFormRepository}
     * @param cre O CRE do enfermeiro reponsável pela criação desse formulário
     * @param nurseForm1CreateDTO O objecto do formulário
     * @param idForm O ID do formulário
     * @return O objeto do formulário criado no sistema, contendo os ID's do Paciente e Formulário gerado
     */
    NurseForm1DTO finishForm1(Integer idForm, Integer cre, NurseForm1UpdateDTO nurseForm1CreateDTO);

    NurseForm1DTO saveForm1(Integer idForm, Integer cre, NurseForm1UpdateDTO nurseForm1CreateDTO);

    /**
     * Esse método finaliza o preenchimento de um Formulário de Médico existente. Para isso, primeiro é necessário checar
     * se o formulário já não está finalizado ({@code br.ufs.hu.prevsep.web.api.dto.form.FormStatus.FINISHED}).
     * Caso positivo, então o formulário é atualizado no banco de dados, seus status é atualizado para FINISHED e o segundo
     * formulário para o enfermeiro é criado.
     *
     * @param idForm o id do formulário
     * @param doctorFormUpdateDTO objeto do formulário do médico
     * @return O formulário atualizado.
     */
    DoctorFormDTO finishDoctorForm(Integer idForm, DoctorFormUpdateDTO doctorFormUpdateDTO)
            throws FormNotFoundException, InvalidFormStateException;


    DoctorFormDTO saveDoctorForm(Integer idForm, DoctorFormUpdateDTO doctorFormUpdateDTO)
            throws FormNotFoundException, InvalidFormStateException;

    /**
     * Esse método finaliza o preenchimento de um Formulário de Enfermeiro existente. Para isso, primeiro é necessário
     * checar se esse formulário e o do médico já não está finalizado ({@code br.ufs.hu.prevsep.web.api.dto.form.FormStatus.FINISHED}).
     * Caso positivo, então o formulário é atualizado no banco de dados, seus status é atualizado para FINISHED e a
     * parte 1 desse formulário é também finalizado.
     *
     * @param cre O CRE do enfermeiro que está preenchendo esse formulário
     * @param idForm O ID do formulário a ser preenchido
     * @param nurseForm2UpdateDTO O objeto do formulário
     * @return O formulário atualizado
     * @throws FormNotFoundException se o formulário não foi encontrado
     * @throws InvalidFormStateException se o formulário não pode ser preenchido devido ao seu estado
     * @throws UserNotFoundException se dado enfermeiro não existe
     */
    NurseForm2DTO finishNurseForm2(Integer cre, Integer idForm, NurseForm2UpdateDTO nurseForm2UpdateDTO)
            throws FormNotFoundException, InvalidFormStateException, UserNotFoundException;


    NurseForm2DTO saveNurseForm2(Integer cre, Integer idForm, NurseForm2UpdateDTO nurseForm2UpdateDTO)
            throws FormNotFoundException, InvalidFormStateException, UserNotFoundException;
}
