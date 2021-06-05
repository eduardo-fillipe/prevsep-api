package br.ufs.hu.prevsep.web.api.service.usuario;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.user.CPFAlreadyRegistered;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioResponseDTO updateUsuario(String cpf, UsuarioUpdateDTO usuario) throws UserNotFoundException;

    Optional<UsuarioResponseDTO> getUsuario(String cpf);

    List<UsuarioResponseDTO> getUsuarios();

    void deleteUsuario(String cpf) throws UserNotFoundException;
}
