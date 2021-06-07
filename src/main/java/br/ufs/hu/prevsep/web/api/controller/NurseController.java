package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.doctor.DoctorResponseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseFullDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.nurse.NurseUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.service.nurse.NurseService;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(path = ApiRequestMappings.NURSES, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Nurses", description = "Endpoints de gerenciamento e listagem de enfermeiros")
public class NurseController extends BaseController{

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping
    @Operation(summary = "Returns all nurses in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))))})
    public List<NurseDTO> getMedics(){
        return nurseService.getNurses();
    }

    @GetMapping("/{cre}")
    @Operation(summary = "Returns info about a Nurse by a given CRE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorResponseFullDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Nurse not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public NurseFullDTO getMedicByCRM(@Valid @Size @NotNull @PathVariable Integer cre){
        return nurseService.getNurseByCRE(cre).orElseThrow(() ->
                new UserNotFoundException().withDetailedMessage("This medic was not found in the System."));
    }

    @PostMapping
    @Operation(summary = "Creates a new Nurse.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nurse Created", content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Nurse already registered", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public NurseDTO createMedic(@RequestBody @Valid NurseRequestDTO medicoRequestDTO) {
        return nurseService.createNurse(medicoRequestDTO);
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Updates a Nurse")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modified"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nurse not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public void updateMedic(@PathVariable("cpf") @Valid  @Size(min = 11, max = 11) @NotEmpty String cpf, @RequestBody @Valid NurseUpdateDTO nurseUpdateDTO) {
        nurseService.updateNurse(cpf, nurseUpdateDTO);
    }
}
