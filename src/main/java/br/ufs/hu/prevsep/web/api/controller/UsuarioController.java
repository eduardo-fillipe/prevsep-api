package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.security.PageUsuarioLoginLogDTO;
import br.ufs.hu.prevsep.web.api.dto.security.PageableUsuarioLoginLogDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.PageUsuarioDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.UsuarioPageableRequestDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.UsuarioUpdateDTO;
import br.ufs.hu.prevsep.web.api.service.security.UsuarioLogService;
import br.ufs.hu.prevsep.web.api.service.user.usuario.UsuarioService;
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

    private final UsuarioService usuarioService;
    private final UsuarioLogService usuarioLogService;

    public UsuarioController(UsuarioService usuarioService, UsuarioLogService usuarioLogService) {
        this.usuarioService = usuarioService;
        this.usuarioLogService = usuarioLogService;
    }

    @GetMapping
    @Operation(summary = "Returns all Users in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageUsuarioDTO.class)))})
    @PreAuthorize("hasRole('ROLE_1')")
    public PageUsuarioDTO getUsuarios(@ModelAttribute(value = "usuarioPageRequest") @Valid UsuarioPageableRequestDTO usuarioPageRequest) {
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

    @GetMapping("/logs/login")
    @Operation(summary = "Returns the Login logs of the users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageUsuarioLoginLogDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("(#usuarioPageRequest.cpfUsuario == authentication.principal) or hasRole('ROLE_1')")
    public PageUsuarioLoginLogDTO getLoginLogs(@ModelAttribute(value = "usuarioLoginPageRequest") PageableUsuarioLoginLogDTO usuarioPageRequest) {
        return usuarioLogService.getLoginLogs(usuarioPageRequest);
    }

}
