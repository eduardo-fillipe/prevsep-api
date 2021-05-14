package br.ufs.hu.prevsep.web.api.service;

import br.ufs.hu.prevsep.web.api.dto.HelloWorldDTO;

import java.time.LocalDateTime;

public interface HelloWorldService {
    /**
     * Return a HelloWorld.
     * @param message The echo setMessage to be returned
     * @return a HelloWorld.
     */
    public HelloWorldDTO getHelloWorld(String message);
}
