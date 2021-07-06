package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RequestMapping(produces = "application/json")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Não Autenticado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FaultDTO.class))),
        @ApiResponse(responseCode = "403", description = "Acesso Negado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FaultDTO.class))),
        @ApiResponse(responseCode = "500", description = "Erro no Servidor Interno", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FaultDTO.class)))
})
@SecurityRequirement(name = "oauth")
public abstract class BaseController {}
