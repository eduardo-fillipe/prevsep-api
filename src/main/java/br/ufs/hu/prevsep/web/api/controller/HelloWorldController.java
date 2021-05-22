package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.PrevSepApiRequestMappings;
import br.ufs.hu.prevsep.web.api.dto.HelloWorldDTO;
import br.ufs.hu.prevsep.web.api.service.HelloWorldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PrevSepApiRequestMappings.HELLO_WORLD, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Hello World Controller", description = "A Hello World Controller.")
public class HelloWorldController extends BaseController {

    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping
    @Operation(summary = "Returns a Hello World from the API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = HelloWorldDTO.class)))})
    @PreAuthorize("hasRole('ROLE_1')")
    public HelloWorldDTO getUsers() {
        return helloWorldService.getHelloWorld("Hello from PrevSep! :)");
    }

}
