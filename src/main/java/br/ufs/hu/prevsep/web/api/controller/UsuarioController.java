package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.security.LoginReportRequest;
import br.ufs.hu.prevsep.web.api.dto.security.PageUsuarioLoginLogDTO;
import br.ufs.hu.prevsep.web.api.dto.security.PageableUsuarioLoginLogDTO;
import br.ufs.hu.prevsep.web.api.dto.security.UsuarioEventAccessRequest;
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
import net.sf.jasperreports.engine.JRException;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.sql.SQLException;

@RestController
@RequestMapping(path = ApiRequestMappings.USERS, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Usuários", description = "Endpoints de gerenciamento de usuários.")
public class UsuarioController extends BaseController{

    private final UsuarioService usuarioService;
    private final UsuarioLogService usuarioLogService;

    public UsuarioController(UsuarioService usuarioService, UsuarioLogService usuarioLogService) {
        this.usuarioService = usuarioService;
        this.usuarioLogService = usuarioLogService;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os usuários presentes no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageUsuarioDTO.class)))})
    @PreAuthorize("hasRole('ROLE_1')")
    public PageUsuarioDTO getUsuarios(@ModelAttribute(value = "usuarioPageRequest") @Valid UsuarioPageableRequestDTO usuarioPageRequest) {
        return usuarioService.getUsuarios(usuarioPageRequest);
    }

    @PatchMapping("/{cpf}")
    @Operation(summary = "Atualiza as informações de um usuário")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modificado"),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("#cpf == authentication.principal")
    public void updateUsuario(@PathVariable @NotEmpty @CPF String cpf, @RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO){
        usuarioService.updateUsuario(cpf, usuarioUpdateDTO);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Remove um usuário")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Modificado"),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("(#cpf == authentication.principal) or hasRole('ROLE_1')")
    public void deleteUsuario(@PathVariable @Valid @NotEmpty @CPF String cpf) {
        usuarioService.deleteUsuario(cpf);
    }

    @GetMapping("/logs/login")
    @Operation(summary = "Retorna o registro de login dos usuários.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = PageUsuarioLoginLogDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("(#usuarioPageRequest.cpfUsuario == authentication.principal) or hasRole('ROLE_1')")
    public PageUsuarioLoginLogDTO getLoginLogs(@ModelAttribute(value = "usuarioLoginPageRequest") PageableUsuarioLoginLogDTO usuarioPageRequest) {
        return usuarioLogService.getLoginLogs(usuarioPageRequest);
    }

    @GetMapping(value = "/logs/login/report", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Retorna um relatorio de logins em dado período e CPF's.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_1')")
    public byte[] getLoginReport(@ModelAttribute LoginReportRequest loginReportRequest) throws JRException, SQLException {
        return usuarioLogService.getLoginReport(loginReportRequest);
    }

    @GetMapping(value = "/logs/activity/report", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    @Operation(summary = "Retorna um relatório de atividades em dado período e CPF's.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)),
            @ApiResponse(responseCode = "400", description = "Solicitação mal feita", content = @Content(schema = @Schema(implementation = FaultDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_1')")
    public byte[] getEventAccessReport(@ModelAttribute @Valid UsuarioEventAccessRequest eventRequest) throws JRException, SQLException {
        return usuarioLogService.getEventAccessReport(eventRequest);
    }
}
