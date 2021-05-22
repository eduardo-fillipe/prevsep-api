package br.ufs.hu.prevsep.web.api.config.security;

import br.ufs.hu.prevsep.web.api.dto.security.authorization.role.RoleDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public UserDetailsServiceImpl(){}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        if (!username.equals("admin"))
            throw new UsernameNotFoundException("The Username was not found.");

        RoleDTO role = new RoleDTO();
        role.setRoleId(1);
        role.setRoleName("ADMIN");

        RoleDTO[] roles = {role};

        user = new User(username, "$2y$10$o9c4ncVLoe4D4mhvnGT8fePcSkG3O/Lr7FG5UDutzxHLHmbiGG9Dm",
                Arrays.asList(roles));

        return user;
    }

}
