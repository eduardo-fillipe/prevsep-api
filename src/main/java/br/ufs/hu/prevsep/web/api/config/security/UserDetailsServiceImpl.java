package br.ufs.hu.prevsep.web.api.config.security;

import br.ufs.hu.prevsep.web.api.dto.security.authorization.role.RoleDTO;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.CargoEnum;
import br.ufs.hu.prevsep.web.api.dto.user.usuario.StatusUsuarioEnum;
import br.ufs.hu.prevsep.web.api.model.UsuarioEntity;
import br.ufs.hu.prevsep.web.api.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        UsuarioEntity usuarioEntity = usuarioRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        if (StatusUsuarioEnum.fromId(usuarioEntity.getStatus()) == StatusUsuarioEnum.DESATIVADO)
            throw new UsernameNotFoundException("Usuário não encontrado.");

        ArrayList<RoleDTO> roles = new ArrayList<>();
        if (StatusUsuarioEnum.fromId(usuarioEntity.getStatus()) == StatusUsuarioEnum.ATIVO) {
            RoleDTO role = new RoleDTO();
            role.setRoleId(usuarioEntity.getCargo());
            role.setRoleName(String.valueOf(CargoEnum.fromId(usuarioEntity.getCargo())));
            roles.add(role);
        }

        user = new User(username, usuarioEntity.getSenha(),
                roles);

        return user;
    }

}
