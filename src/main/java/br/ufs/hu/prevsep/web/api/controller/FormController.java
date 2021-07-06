package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.*;
import br.ufs.hu.prevsep.web.api.service.form.sepse.SepseFormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping(path = ApiRequestMappings.FORMS_SEPSE, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Formulário Sepse", description = "Gerencia, cria, lista e atualiza formulários do tipo Sepse.")
@PreAuthorize("hasAnyRole('ROLE_1', 'ROLE_2', 'ROLE_3')")
public class FormController extends BaseController {

    private final SepseFormService sepseFormService;

    public FormController(SepseFormService sepseFormService) {
        this.sepseFormService = sepseFormService;
    }

    @GetMapping("/doctor")
    @Operation(summary = "Retorna os formulários dos médicos presentes no banco de dados a partir de um certo critério.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok",
            content = @Content(schema = @Schema(implementation = PageDoctorFormDTO.class))),
        @ApiResponse(responseCode = "400", description = "Solicitação mal feita",
            content = @Content(schema = @Schema(implementation = FaultDTO.class)))})
    public PageDoctorFormDTO getDoctorForms(@ModelAttribute PageableDoctorFormDTO pageableRequest) {
        return sepseFormService.getDoctorForms(pageableRequest);
    }

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Retorna um relatório em PDF de um certo formulário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)),
            @ApiResponse(responseCode = "404", description = "Formulário não encontrado",
                    content = @Content(schema = @Schema(implementation = FaultDTO.class)))})
    @PreAuthorize("hasRole('ROLE_1')")
    public byte[] getReportLast30Days() throws JRException, SQLException {
        return sepseFormService.getReportLast30Days();
    }

    @GetMapping("/nurse/form1")
    @Operation(summary = "Retorna os formulários de enfermeiro (parte 1) presentes no banco de dados a partir de um certo critério.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageNurseForm1DTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita",
                    content = @Content(schema = @Schema(implementation = FaultDTO.class)))})
    public PageNurseForm1DTO getNurse1Forms(@Valid @ModelAttribute PageableNurseForm1DTO pageableRequest) {
        return sepseFormService.getNurseForm1(pageableRequest);
    }

    @GetMapping("/nurse/form2")
    @Operation(summary = "Retorna os formulários de enfermeiro (parte 2) presentes no banco de dados a partir de um certo critério.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageNurseForm2DTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita",
                    content = @Content(schema = @Schema(implementation = FaultDTO.class)))})
    public PageNurseForm2DTO getNurse2Forms(@Valid @ModelAttribute PageableNurseForm2DTO pageableRequest) {
        return sepseFormService.getNurseForm2(pageableRequest);
    }
}
