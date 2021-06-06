package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorUpdateDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.service.doctor.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@RequestMapping(path = ApiRequestMappings.MEDICS, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Medics", description = "Endpoints de gerenciamento e listagem de médicos")
public class DoctorController extends BaseController{

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    @Operation(summary = "Returns all medics in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))))})
    public List<DoctorResponseDTO> getMedics(){
        return doctorService.getMedics();
    }

    @GetMapping("/{crm}")
    @Operation(summary = "Returns info about a medic by a given CRM")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseFullDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Medic not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public DoctorResponseFullDTO getMedicByCRM(@Valid @Size @NotNull @PathVariable Integer crm){
        return doctorService.getMedicByCRM(crm).orElseThrow(() ->
                new UserNotFoundException().withDetailedMessage("This medic was not found in the System."));
    }

    @PostMapping
    @Operation(summary = "Creates a new medic.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medic Created", content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Medic already registered", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public DoctorResponseDTO createMedic(@RequestBody @Valid DoctorRequestDTO doctorRequestDTO) {
        return doctorService.createMedic(doctorRequestDTO);
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Updates a medic.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modified"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Medic not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public void updateMedic(@PathVariable("cpf") @Valid  @Size(min = 11, max = 11) @NotEmpty String cpf, @RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        doctorService.updateMedico(cpf, doctorUpdateDTO);
    }
}
