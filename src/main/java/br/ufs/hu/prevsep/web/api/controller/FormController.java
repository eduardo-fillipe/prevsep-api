package br.ufs.hu.prevsep.web.api.controller;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ApiRequestMappings.FORMS_SEPSE, produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Sepse Form", description = "Manage, create, list and update sepse related forms.")
public class FormController extends BaseController {

}
