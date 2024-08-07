package com.commerce.service;

import com.commerce.entity.UserEntity;
import com.commerce.entity.UserRoleEntity;
import com.commerce.repository.IUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService  implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserSecurityService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando usuario...");
        System.out.println("Buscando usuario...");
        UserEntity user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println("Usuario encontrado... "+ user.getUsername());
        System.out.println("Usuario locked ... "+ user.getLocked());
        System.out.println("Usuario disable ... "+ user.getDisabled());
        String[] roles = user.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);
        System.out.println("Roles encontrados... "+ roles);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .disabled(user.getDisabled())
                .accountLocked(user.getLocked())
                .authorities(this.grantedAuthorities(roles))
                .build();
    }

    //Crear los permisos de los usuarios
    private String[] getAuthorities(String role) {
        if (role.equals("CLIENT")) {
            System.out.println(" rol CLIENT y tendra permiso permissionClient");
            return new String[]{"permissionClient"};
        }

        if (role.equals("ADMIN")){
            System.out.println(" rol ADMIN y tendra permiso permissionAdmin");
            return new String[]{"permissionAdmin"};
        }

        return new String[]{};
    }

    //Asignar roles a los usuarios
    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
            for (String authority : this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }


        return authorities;
    }

}
