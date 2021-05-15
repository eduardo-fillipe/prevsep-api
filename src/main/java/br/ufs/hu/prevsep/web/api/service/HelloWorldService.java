package br.ufs.hu.prevsep.web.api.service;

import br.ufs.hu.prevsep.web.api.dto.HelloWorldDTO;


public interface HelloWorldService {
    /**
     * Return a HelloWorld.
     * @param message The echo withMessage to be returned
     * @return a HelloWorld.
     */
    HelloWorldDTO getHelloWorld(String message);
}
