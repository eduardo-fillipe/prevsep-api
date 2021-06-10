package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerFullDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.manager.ManagerUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.service.manager.ManagerService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = ApiRequestMappings.MANAGERS, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Managers", description = "Manage, create, list and update managers.")
@PreAuthorize("hasRole('ROLE_1')")
public class ManagerController extends BaseController{

    private final ManagerService managerService;

    public ManagerController(ManagerService medicService) {
        this.managerService = medicService;
    }

    @GetMapping
    @Operation(summary = "Returns all managers in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ManagerDTO.class))))})
    public List<ManagerDTO> getManagers(){
        return managerService.getManagers();
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Returns info about a manager by a given CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = ManagerFullDTO.class))),
            @ApiResponse(responseCode = "404", description = "Medic not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("#cpf == authentication.principal")
    public ManagerFullDTO getManagerByCPF(@PathVariable @Valid @CPF String cpf){
        return managerService.getManager(cpf).orElseThrow(() ->
                new UserNotFoundException().withDetailedMessage("This manager was not found in the System."));
    }

    @PostMapping
    @Operation(summary = "Creates a new Manager.")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Manager Created", content = @Content(schema = @Schema(implementation = ManagerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "409", description = "Manager already registered", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    public ManagerDTO createManager(@RequestBody @Valid ManagerRequestDTO managerRequestDTO) {
        return managerService.createManager(managerRequestDTO);
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Updates a specific manager.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modified"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Manager not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("#cpf == authentication.principal")
    public void updateManager(@PathVariable("cpf") @Valid @CPF String cpf, @RequestBody @Valid ManagerUpdateDTO managerUpdateDTO) {
        managerService.updateManager(cpf, managerUpdateDTO);
    }
}
