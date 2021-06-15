package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm1CreateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm1DTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm2DTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.NurseForm2UpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.nurse.NurseUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.service.form.sepse.SepseFormService;
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
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(path = ApiRequestMappings.NURSES, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Nurses", description = "Nurse related endpoints")
@PreAuthorize("hasAnyRole('ROLE_1', 'ROLE_3')")
public class NurseController extends BaseController{

    private final NurseService nurseService;
    private final SepseFormService sepseFormService;

    public NurseController(NurseService nurseService, SepseFormService sepseFormService) {
        this.nurseService = nurseService;
        this.sepseFormService = sepseFormService;
    }

    @GetMapping
    @Operation(summary = "Returns all nurses in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))))})
    public List<NurseDTO> getNurses(){
        return nurseService.getNurses();
    }

    @GetMapping("/{cre}")
    @Operation(summary = "Returns info about a Nurse by a given CRE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseFullDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Nurse not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PostAuthorize("(hasRole('ROLE_3') and returnObject.userInfo.cpf == authentication.principal) or hasAnyRole('ROLE_1')")
    public NurseFullDTO getNurseByCRE(@Valid @Size @NotNull @PathVariable Integer cre){
        return nurseService.getNurseByCRE(cre).orElseThrow(() ->
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
    public NurseDTO createMedic(@RequestBody @Valid NurseRequestDTO nurseRequestDTO) {
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
    public void updateMedic(@PathVariable("cpf") @Valid  @CPF String cpf, @RequestBody @Valid NurseUpdateDTO nurseUpdateDTO) {
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
    public NurseForm1DTO createForm(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre,
                                    @RequestBody @Valid NurseForm1CreateDTO nurseForm1CreateDTO) {
        return sepseFormService.createForm(cre, nurseForm1CreateDTO);
    }

    @PutMapping("/{cre}/forms/sepse/{idForm}/form1")
    @Operation(summary = "Saves the state of the Nurse's sepse form (2nd part).")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found: Patient, Nurse or Doctor", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: Illegal user or form state", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @Valid
    public void saveForm1(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre, @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                          @RequestBody @Valid NurseForm2UpdateDTO nurseForm2UpdateDTO) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PostMapping("/{cre}/forms/sepse/{idForm}/form1")
    @Operation(summary = "Finishes filling and activate a Sepse form")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found: Patient, Nurse or Doctor", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @Valid
    public NurseForm1DTO finishForm1(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre,
                                     @RequestBody @Valid NurseForm1CreateDTO nurseForm1CreateDTO, @PathVariable @Valid @Min(1) String idForm) {
        return null;
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
    public NurseForm2DTO finishForm2(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre, @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                                     @RequestBody @Valid NurseForm2UpdateDTO nurseForm2UpdateDTO) {
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
    @Valid
    public void saveForm2(@PathVariable("cre") @Valid @Min(1) @NotNull Integer cre, @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                                     @RequestBody @Valid NurseForm2UpdateDTO nurseForm2UpdateDTO) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
