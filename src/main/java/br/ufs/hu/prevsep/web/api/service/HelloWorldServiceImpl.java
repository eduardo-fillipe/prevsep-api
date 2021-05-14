package br.ufs.hu.prevsep.web.api.service;

import br.ufs.hu.prevsep.web.api.dto.HelloWorldDTO;

import java.time.LocalDateTime;

public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public HelloWorldDTO getHelloWorld(String message) {
        HelloWorldDTO h = new HelloWorldDTO();
        h.setMessage(message);
        h.setTime(LocalDateTime.now());
        return h;
    }
}
