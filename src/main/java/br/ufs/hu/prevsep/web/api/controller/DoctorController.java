package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.DoctorFormDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.DoctorFormUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.PageDoctorFormDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.PageableDoctorFormDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.service.form.sepse.SepseFormService;
import br.ufs.hu.prevsep.web.api.service.user.doctor.DoctorService;
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
@RequestMapping(path = ApiRequestMappings.DOCTORS, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Doctors", description = "Endpoints for managing, create and listing doctors.")
@PreAuthorize("hasAnyRole('ROLE_1', 'ROLE_2', 'ROLE_3')")
public class DoctorController extends BaseController{

    private final DoctorService doctorService;
    private final SepseFormService sepseFormService;

    public DoctorController(DoctorService doctorService, SepseFormService sepseFormService) {
        this.doctorService = doctorService;
        this.sepseFormService = sepseFormService;
    }

    @GetMapping
    @Operation(summary = "Returns all doctors in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))))})
    public List<DoctorResponseDTO> getMedics(){
        return doctorService.getMedics();
    }

    @GetMapping("/{crm}/forms/sepse/pending")
    @Operation(summary = "Returns all pending forms of a doctor with certain crm.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PageDoctorFormDTO.class))))})
    public PageDoctorFormDTO getPendingDoctorForms(@PathVariable("crm") @Valid @Min(1) @NotNull Integer crm) {
        return sepseFormService.getPendingDoctorForms(crm);
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Returns info about a doctor by a given CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseFullDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PostAuthorize("(hasRole('ROLE_2') and #cpf == authentication.principal) or hasAnyRole('ROLE_1', 'ROLE_3')")
    public DoctorResponseFullDTO getMedicByCRM(@Valid @CPF @NotNull @PathVariable String cpf) {
        return doctorService.getMedic(cpf).orElseThrow(() ->
                new UserNotFoundException().withDetailedMessage("This doctor was not found in the System."));
    }

    @PostMapping
    @Operation(summary = "Creates a new doctor.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor Created", content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Doctor already registered", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_1')")
    public DoctorResponseDTO createMedic(@RequestBody @Valid DoctorRequestDTO doctorRequestDTO) {
        return doctorService.createMedic(doctorRequestDTO);
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Updates a doctor.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modified"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("(#cpf == authentication.principal) or hasRole('ROLE_1')")
    public void updateMedic(@PathVariable("cpf") @Valid  @CPF String cpf, @RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        doctorService.updateMedico(cpf, doctorUpdateDTO);
    }

    @PostMapping("/{crm}/forms/sepse/{idForm}")
    @Operation(summary = "Finishes filling a given form in the system.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = DoctorFormDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public DoctorFormDTO finishFillingForm(@RequestBody @Valid DoctorFormUpdateDTO doctorFormUpdateDTO,
                                           @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                                           @PathVariable("crm") @Valid @Min(1) Integer crmDoctor) {
        return sepseFormService.finishDoctorForm(idForm, doctorFormUpdateDTO);
    }

    @PutMapping("/{crm}/forms/sepse/{idForm}")
    @Operation(summary = "Saves the state of a given form in the system.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public void saveForm(@RequestBody DoctorFormUpdateDTO doctorFormUpdateDTO,
                                           @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                                           @PathVariable("crm") @Valid @Min(1) Integer crmDoctor) {
        sepseFormService.saveDoctorForm(idForm, doctorFormUpdateDTO);
    }
}
