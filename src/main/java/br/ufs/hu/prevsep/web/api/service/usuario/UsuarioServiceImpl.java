package br.ufs.hu.prevsep.web.api.service.usuario;

import br.ufs.hu.prevsep.web.api.dto.mapper.UsuarioMapper;
import br.ufs.hu.prevsep.web.api.dto.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.PasswordDoesNotHaveMinimumRequirementsException;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.UsuarioEntity;
import br.ufs.hu.prevsep.web.api.repository.UsuarioRepository;
import br.ufs.hu.prevsep.web.api.utils.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioResponseDTO updateUsuario(String cpf, UsuarioUpdateDTO usuario) throws UserNotFoundException {
        UsuarioEntity existingUsuario =  usuarioRepository.findById(cpf).orElseThrow(() ->
                new UserNotFoundException().withDetailedMessage("This user was not found."));

        UsuarioEntity mappedEntity = usuarioMapper.mapToUsuarioEntity(usuario);
        BeanUtils.copyPropertiesIgnoreNulls(mappedEntity, existingUsuario, true);

        if (usuario.getSenha() != null) {
            Optional<PasswordDoesNotHaveMinimumRequirementsException> passValidation =
                    validatePassword(usuario.getSenha());
            if (passValidation.isEmpty()) {
                existingUsuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            } else {
                throw passValidation.get();
            }
        }

        UsuarioEntity result = usuarioRepository.save(existingUsuario);

        return usuarioMapper.mapToUsuarioResponseDto(result);
    }

    @Override
    public Optional<UsuarioResponseDTO> getUsuario(String cpf) {
        if (cpf == null)
            return  Optional.empty();

        Optional<UsuarioEntity> usuario = usuarioRepository.findById(cpf);

        if (usuario.isPresent()
                && StatusUsuarioEnum.fromId(usuario.get().getStatus()) != StatusUsuarioEnum.DESATIVADO) {
            return Optional.ofNullable(usuarioMapper.mapToUsuarioResponseDto(usuario.get()));
        }

        return Optional.empty();
    }

    @Override
    public List<UsuarioResponseDTO> getUsuarios() {
        List<UsuarioResponseDTO> result = new ArrayList<>();

        usuarioRepository.findAll().forEach(usuarioEntity -> {
            if (StatusUsuarioEnum.fromId(usuarioEntity.getStatus()) != StatusUsuarioEnum.DESATIVADO)
                result.add(usuarioMapper.mapToUsuarioResponseDto(usuarioEntity));
        });

        return result;
    }

    @Override
    public void deleteUsuario(String cpf) throws UserNotFoundException {
        getUsuario(cpf).orElseThrow(() ->
                new UserNotFoundException().withDetailedMessage("This user was not found."));

        UsuarioUpdateDTO usuarioUpdateDTO = new UsuarioUpdateDTO();
        usuarioUpdateDTO.setStatus(StatusUsuarioEnum.DESATIVADO);

        updateUsuario(cpf, usuarioUpdateDTO);
    }
}
