package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.DoctorFormDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.DoctorFormUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.form.sepse.PageDoctorFormDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.user.doctor.DoctorUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.service.form.sepse.SepseFormService;
import br.ufs.hu.prevsep.web.api.service.security.AuthorizationExtensionService;
import br.ufs.hu.prevsep.web.api.service.security.extensionpoint.DoctorCRMExtensionPoint;
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
@Tag(name = "Médicos", description = "Endpoints para gerenciar, criar e listar médicos.")
@PreAuthorize("hasAnyRole('ROLE_1', 'ROLE_2', 'ROLE_3')")
public class DoctorController extends BaseController{

    private final DoctorService doctorService;
    private final SepseFormService sepseFormService;
    private final AuthorizationExtensionService authorizationExtensionService;

    public DoctorController(DoctorService doctorService, SepseFormService sepseFormService,
                            AuthorizationExtensionService authorizationExtensionService) {
        this.doctorService = doctorService;
        this.sepseFormService = sepseFormService;
        this.authorizationExtensionService = authorizationExtensionService;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os médicos no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))))})
    public List<DoctorResponseDTO> getMedics(){
        return doctorService.getMedics();
    }

    @GetMapping("/{crm}/forms/sepse/pending")
    @Operation(summary = "Retorna todos os formulários pedentes de um médico de acordo com o seu crm.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PageDoctorFormDTO.class))))})
    @PreAuthorize("hasRole('ROLE_2')")
    public PageDoctorFormDTO getPendingDoctorForms(@PathVariable("crm") @Valid @Min(1) @NotNull Integer crm) {
        authorizationExtensionService.authorize(DoctorCRMExtensionPoint.class, crm);
        return sepseFormService.getPendingDoctorForms(crm);
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Retorna informações sobre um médico dado um CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseFullDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PostAuthorize("(hasRole('ROLE_2') and #cpf == authentication.principal) or hasAnyRole('ROLE_1', 'ROLE_3')")
    public DoctorResponseFullDTO getMedicByCPF(@Valid @CPF @NotNull @PathVariable String cpf) {
        return doctorService.getMedic(cpf).orElseThrow(() ->
                new UserNotFoundException().withDetailedMessage("Esse médico não foi encontrado no Sistema."));
    }

    @PostMapping
    @Operation(summary = "Cria um novo médico.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico criado", content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Médico já registrado", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_1')")
    public DoctorResponseDTO createMedic(@RequestBody @Valid DoctorRequestDTO doctorRequestDTO) {
        return doctorService.createMedic(doctorRequestDTO);
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Atualiza as informações de um médico.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modificado"),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("(#cpf == authentication.principal) or hasRole('ROLE_1')")
    public void updateMedic(@PathVariable("cpf") @Valid  @CPF String cpf, @RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        doctorService.updateMedico(cpf, doctorUpdateDTO);
    }

    @PostMapping("/{crm}/forms/sepse/{idForm}")
    @Operation(summary = "Finaliza o preenchimento de um certo formulário no Sistema.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = DoctorFormDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflito", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_2')")
    public DoctorFormDTO finishFillingForm(@RequestBody @Valid DoctorFormUpdateDTO doctorFormUpdateDTO,
                                           @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                                           @PathVariable("crm") @Valid @Min(1) Integer crmDoctor) {
        authorizationExtensionService.authorize(DoctorCRMExtensionPoint.class, crmDoctor);
        return sepseFormService.finishDoctorForm(idForm, doctorFormUpdateDTO);
    }

    @PutMapping("/{crm}/forms/sepse/{idForm}")
    @Operation(summary = "Salva o estado de um certo formulário no Sistema.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflito", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_2')")
    public void saveForm(@RequestBody DoctorFormUpdateDTO doctorFormUpdateDTO,
                                           @PathVariable("idForm") @Valid @Min(1) Integer idForm,
                                           @PathVariable("crm") @Valid @Min(1) Integer crmDoctor) {
        authorizationExtensionService.authorize(DoctorCRMExtensionPoint.class, crmDoctor);
        sepseFormService.saveDoctorForm(idForm, doctorFormUpdateDTO);
    }
}
