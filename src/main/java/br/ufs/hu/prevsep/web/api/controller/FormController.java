package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.PageDoctorFormDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.PageableDoctorFormDTO;
import br.ufs.hu.prevsep.web.api.service.form.sepse.SepseFormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ApiRequestMappings.FORMS_SEPSE, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Sepse Form", description = "Manage, create, list and update sepse related forms.")
public class FormController extends BaseController {

    private final SepseFormService sepseFormService;

    public FormController(SepseFormService sepseFormService) {
        this.sepseFormService = sepseFormService;
    }

    @GetMapping("/doctor")
    @Operation(summary = "Returns forms doctors in the database given the criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageDoctorFormDTO.class)))})
    public PageDoctorFormDTO getDoctorForms(@ModelAttribute PageableDoctorFormDTO pageableRequest) {
        return sepseFormService.getDoctorForms(pageableRequest);
    }
}
