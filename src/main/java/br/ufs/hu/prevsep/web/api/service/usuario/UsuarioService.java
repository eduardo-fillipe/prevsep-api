package br.ufs.hu.prevsep.web.api.service.usuario;

import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.PasswordDoesNotHaveMinimumRequirementsException;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public interface UsuarioService {
    String REGEX_HAS_LETTER = "(.*([a-z]|[A-Z]).*)";
    String REGEX_HAS_NUMBER = "(.*[0-9].*)";
    String REGEX_HAS_ESPECIAL = "(.*[@#$%^&+=].*)";

    /**
     * Validates if a given password have the minimum requirements.
     * @param password The password
     * @return true if the password meet the requirements, otherwise false
     */
    default Optional<PasswordDoesNotHaveMinimumRequirementsException> validatePassword(String password) {

        PasswordDoesNotHaveMinimumRequirementsException validationError = new PasswordDoesNotHaveMinimumRequirementsException();
        if (password == null)
            return Optional.ofNullable((PasswordDoesNotHaveMinimumRequirementsException) validationError.withFieldError("password", "Cannot be null."));

        if (password.length() < 8)
            validationError.withFieldError("password", "Required length: 8");

        if (password.contains(" "))
            validationError.withFieldError("password", "Cannot contain blank spaces.");

        Pattern p = Pattern.compile(REGEX_HAS_LETTER);
        if (!p.matcher(password).matches())
            validationError.withFieldError("password", "Must have at least 1 letter.");

        p = Pattern.compile(REGEX_HAS_NUMBER);
        if (!p.matcher(password).matches())
            validationError.withFieldError("password", "Must have at least 1 number.");

        p = Pattern.compile(REGEX_HAS_ESPECIAL);
        if (!p.matcher(password).matches())
            validationError.withFieldError("password", "Must have at least 1 special character.");

        return validationError.getFieldErrors() == null ?
                Optional.empty() :
                Optional.of(validationError);
    }

    UsuarioResponseDTO updateUsuario(String cpf, UsuarioUpdateDTO usuario) throws UserNotFoundException;

    Optional<UsuarioResponseDTO> getUsuario(String cpf);

    List<UsuarioResponseDTO> getUsuarios();

    void deleteUsuario(String cpf) throws UserNotFoundException;
}
