package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.PageUsuarioDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioDTOPageRequest;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioUpdateDTO;
import br.ufs.hu.prevsep.web.api.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(path = ApiRequestMappings.USERS, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Users", description = "User managing endpoints")
public class UsuarioController extends BaseController{

    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(summary = "Returns all Users in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageUsuarioDTO.class)))})
    @PreAuthorize("hasRole('ROLE_1')")
    public PageUsuarioDTO getUsuarios(@ModelAttribute(value = "usuarioPageRequest") @Valid UsuarioDTOPageRequest usuarioPageRequest) {
        return usuarioService.getUsuarios(usuarioPageRequest);
    }

    @PatchMapping("/{cpf}")
    @Operation(summary = "Updates a User")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modified"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("#cpf == authentication.principal")
    public void updateUsuario(@PathVariable @NotEmpty @CPF String cpf, @RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO){
        usuarioService.updateUsuario(cpf, usuarioUpdateDTO);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Removes a User")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modified"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("(#cpf == authentication.principal) or hasRole('ROLE_1')")
    public void deleteUsuario(@PathVariable @Valid @NotEmpty @CPF String cpf) {
        usuarioService.deleteUsuario(cpf);
    }

}
