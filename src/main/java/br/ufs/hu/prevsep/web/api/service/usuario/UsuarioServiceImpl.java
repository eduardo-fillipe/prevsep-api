package br.ufs.hu.prevsep.web.api.service.usuario;

import br.ufs.hu.prevsep.web.api.dto.mapper.UsuarioMapper;
import br.ufs.hu.prevsep.web.api.dto.usuario.CargoEnum;
import br.ufs.hu.prevsep.web.api.dto.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioResponseDTO;
import br.ufs.hu.prevsep.web.api.dto.usuario.UsuarioUpdateDTO;
import br.ufs.hu.prevsep.web.api.exception.PasswordDoesNotHaveMinimumRequirementsException;
import br.ufs.hu.prevsep.web.api.exception.user.UserNotFoundException;
import br.ufs.hu.prevsep.web.api.model.QUsuarioEntity;
import br.ufs.hu.prevsep.web.api.model.UsuarioEntity;
import br.ufs.hu.prevsep.web.api.repository.UsuarioRepository;
import br.ufs.hu.prevsep.web.api.utils.BeanUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JPAQueryFactory queryFactory;
    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, EntityManager entityManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.queryFactory = new JPAQueryFactory(entityManager);
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
    public List<UsuarioResponseDTO> getUsuarios(StatusUsuarioEnum status, CargoEnum cargo, String name, String email) {
        QUsuarioEntity qUser = QUsuarioEntity.usuarioEntity;
        BooleanBuilder filter = new BooleanBuilder(qUser.status.ne(StatusUsuarioEnum.DESATIVADO.getStatus()));

        if (cargo != null)
            filter.and(qUser.cargo.eq(cargo.getId()));
        if (status != null)
            filter.and(qUser.status.eq(status.getStatus()));
        if (name != null)
            filter.and(qUser.nome.lower().like("%" + name.toLowerCase() + "%"));
        if (email != null)
            filter.and(qUser.email.lower().like("%" + email.toLowerCase() + "%"));

        return queryFactory.selectFrom(qUser)
                .where(filter).fetch()
                .stream().map(usuarioMapper::mapToUsuarioResponseDto)
                .collect(Collectors.toList());
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
