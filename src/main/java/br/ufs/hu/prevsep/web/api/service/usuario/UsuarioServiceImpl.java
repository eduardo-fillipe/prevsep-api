package br.ufs.hu.prevsep.web.api.service.usuario;

import br.ufs.hu.prevsep.web.api.dto.mapper.UsuarioMapper;
import br.ufs.hu.prevsep.web.api.dto.usuario.CargoEnum;
import br.ufs.hu.prevsep.web.api.dto.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.PasswordDoesNotHaveMinimumRequirementsException;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.UsuarioEntity;
import br.ufs.hu.prevsep.web.api.repository.UsuarioRepository;
import br.ufs.hu.prevsep.web.api.utils.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
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
    public List<UsuarioResponseDTO> getUsuarios(StatusUsuarioEnum status, CargoEnum cargo, String nome, String email) {
        List<UsuarioResponseDTO> result = new ArrayList<>();

        Specification<UsuarioEntity> filter =
                (root, criteriaQuery, criteriaBuilder) -> {
                    ArrayList<Predicate> filters = new ArrayList<>();
                    filters.add(criteriaBuilder.notEqual(root.get("status"), StatusUsuarioEnum.DESATIVADO.getStatus()));
                    if (cargo != null)
                        filters.add(criteriaBuilder.equal(root.get("cargo"), cargo.getId()));
                    if (status != null)
                        filters.add(criteriaBuilder.equal(root.get("status"), status.getStatus()));
                    if (nome != null)
                        filters.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
                    if (email != null)
                        filters.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"));

                    return criteriaBuilder.and(filters.toArray(new Predicate[0]));
        };

        usuarioRepository.findAll(filter).forEach(usuarioEntity -> result.add(usuarioMapper.mapToUsuarioResponseDto(usuarioEntity)));

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
