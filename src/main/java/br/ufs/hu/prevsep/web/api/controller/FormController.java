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

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping(path = ApiRequestMappings.FORMS_SEPSE, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Sepse Form", description = "Manage, create, list and update sepse related forms.")
public class FormController extends BaseController {

    private final SepseFormService sepseFormService;

    public FormController(SepseFormService sepseFormService) {
        this.sepseFormService = sepseFormService;
    }

    @GetMapping("/doctor")
    @Operation(summary = "Returns the doctors forms in the database given a criteria.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok",
            content = @Content(schema = @Schema(implementation = PageDoctorFormDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = FaultDTO.class)))})
    public PageDoctorFormDTO getDoctorForms(@ModelAttribute PageableDoctorFormDTO pageableRequest) {
        return sepseFormService.getDoctorForms(pageableRequest);
    }

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Returns a PDF report of the given form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)),
            @ApiResponse(responseCode = "404", description = "Form not found",
                    content = @Content(schema = @Schema(implementation = FaultDTO.class)))})
    public byte[] getReportLast30Days() throws JRException, SQLException {
        return sepseFormService.getReportLast30Days();
    }

    @GetMapping("/nurse/form1")
    @Operation(summary = "Returns the nurse forms (part 1) in the database given a criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageNurseForm1DTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = FaultDTO.class)))})
    public PageNurseForm1DTO getNurse1Forms(@Valid @ModelAttribute PageableNurseForm1DTO pageableRequest) {
        return sepseFormService.getNurseForm1(pageableRequest);
    }

    @GetMapping("/nurse/form2")
    @Operation(summary = "Returns the nurse forms (part 2) in the database given a criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageNurseForm2DTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = FaultDTO.class)))})
    public PageNurseForm2DTO getNurse2Forms(@Valid @ModelAttribute PageableNurseForm2DTO pageableRequest) {
        return sepseFormService.getNurseForm2(pageableRequest);
    }
}
