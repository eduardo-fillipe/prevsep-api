package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.*;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.service.form.sepse.SepseFormService;
import br.ufs.hu.prevsep.web.api.service.security.AuthorizationExtensionService;
import br.ufs.hu.prevsep.web.api.service.security.extensionpoint.DoctorCRMExtensionPoint;
import br.ufs.hu.prevsep.web.api.service.security.extensionpoint.NurseCREExtensionPoint;
import br.ufs.hu.prevsep.web.api.service.user.nurse.NurseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = ApiRequestMappings.NURSES, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Nurses", description = "Nurse related endpoints")
@PreAuthorize("hasAnyRole('ROLE_1', 'ROLE_3')")
public class NurseController extends BaseController {

    private final NurseService nurseService;
    private final SepseFormService sepseFormService;
    private final AuthorizationExtensionService authorizationExtensionService;

    public NurseController(NurseService nurseService, SepseFormService sepseFormService,
                           AuthorizationExtensionService authorizationExtensionService) {
        this.nurseService = nurseService;
        this.sepseFormService = sepseFormService;
        this.authorizationExtensionService = authorizationExtensionService;
    }

    @GetMapping
    @Operation(summary = "Returns all nurses in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = NurseDTO.class))))})
    public List<NurseDTO> getNurses() {
        return nurseService.getNurses();
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Returns info about a Nurse by a given CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = NurseFullDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Nurse not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PostAuthorize("(hasRole('ROLE_3') and #cpf == authentication.principal) or hasAnyRole('ROLE_1')")
    public NurseFullDTO getNurseByCRE(@Valid @CPF @NotNull @PathVariable String cpf) {
        return nurseService.getNurse(cpf).orElseThrow(() ->
                new UserNotFoundException().withDetailedMessage("This Nurse was not found in the System."));
    }

    @PostMapping
    @Operation(summary = "Creates a new Nurse.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nurse Created", content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Nurse already registered", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_1')")
    public NurseDTO createNurse(@RequestBody @Valid NurseRequestDTO nurseRequestDTO) {
        return nurseService.createNurse(nurseRequestDTO);
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Updates a Nurse")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modified"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nurse not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("(#cpf == authentication.principal) or hasRole('ROLE_1')")
    public void updateNurse(@PathVariable("cpf") @Valid  @CPF String cpf, @RequestBody @Valid NurseUpdateDTO nurseUpdateDTO) {
        nurseService.updateNurse(cpf, nurseUpdateDTO);
    }

    @PostMapping("/{cre}/forms/sepse")
    @Operation(summary = "Creates a Sepse form.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found: Patient, Nurse or Doctor", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @Valid
    @PreAuthorize("hasRole('ROLE_3')")
    public NurseForm1DTO createForm(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre,
                                    @RequestBody NurseForm1CreateDTO nurseForm1CreateDTO) {
        authorizationExtensionService.authorize(NurseCREExtensionPoint.class, cre);
        return sepseFormService.createForm(cre, nurseForm1CreateDTO);
    }

    @PostMapping("/{cre}/forms/sepse/{idForm}/form1")
    @Operation(summary = "Finishes filling and activate the Nurse's sepse form (1st part)")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found: Patient, Nurse or Doctor", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @Valid
    @PreAuthorize("hasRole('ROLE_3')")
    public NurseForm1DTO finishForm1(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre,
                                     @RequestBody @Valid NurseForm1UpdateDTO nurseForm1UpdateDTO, @PathVariable @Valid @Min(1) Integer idForm) {
        authorizationExtensionService.authorize(NurseCREExtensionPoint.class, cre);
        return sepseFormService.finishForm1(idForm, cre, nurseForm1UpdateDTO);
    }

    @PutMapping("/{cre}/forms/sepse/{idForm}/form1")
    @Operation(summary = "Saves the state of the Nurse's sepse form (1st part)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found: Patient, Nurse or Doctor", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: Illegal user or form state", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @Valid
    @PreAuthorize("hasRole('ROLE_3')")
    public void saveForm1(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre, @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                          @RequestBody @Valid NurseForm1UpdateDTO nurseForm2UpdateDTO) {
        authorizationExtensionService.authorize(NurseCREExtensionPoint.class, cre);
        sepseFormService.saveForm1(idForm, cre, nurseForm2UpdateDTO);
    }

    @PostMapping("/{cre}/forms/sepse/{idForm}/form2")
    @Operation(summary = "Finishes filling a sepse form.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = NurseForm2DTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found: Patient, Nurse or Doctor", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: Illegal user or form state", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @Valid
    @PreAuthorize("hasRole('ROLE_3')")
    public NurseForm2DTO finishForm2(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre, @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                                     @RequestBody @Valid NurseForm2UpdateDTO nurseForm2UpdateDTO) {
        authorizationExtensionService.authorize(NurseCREExtensionPoint.class, cre);
        return sepseFormService.finishNurseForm2(cre, idForm, nurseForm2UpdateDTO);
    }

    @PutMapping("/{cre}/forms/sepse/{idForm}/form2")
    @Operation(summary = "Saves the state of the Nurse's sepse form (2nd part).")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found: Patient, Nurse or Doctor", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: Illegal user or form state", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_3')")
    public void saveForm2(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre, @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                                     @RequestBody NurseForm2UpdateDTO nurseForm2UpdateDTO) {
        authorizationExtensionService.authorize(NurseCREExtensionPoint.class, cre);
        sepseFormService.saveNurseForm2(cre, idForm, nurseForm2UpdateDTO);
    }

    @GetMapping("/{cre}/forms/sepse/pending")
    @Operation(summary = "Retorna os formulários pendentes de um(a) enfermeiro(a) específico(a).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PageNurseForm2DTO.class))))})
    @PreAuthorize("hasRole('ROLE_3')")
    public PageNurseForm2DTO getPendingNurseForms(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre) {
        authorizationExtensionService.authorize(NurseCREExtensionPoint.class, cre);
        return sepseFormService.getPendingNurseForms(cre);
    }
}
