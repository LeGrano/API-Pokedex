package fr.iut.sj.pkdxapi.services;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import fr.iut.sj.pkdxapi.models.UserData;
import fr.iut.sj.pkdxapi.repositories.UserRepository;

import fr.iut.sj.pkdxapi.models.UserData;
import fr.iut.sj.pkdxapi.repositories.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private List<GrantedAuthority> userAuthorities;
    private List<GrantedAuthority> adminAuthorities;
    
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userAuthorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        adminAuthorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var data = userRepository.findById(username);
        if(!data.isPresent()) throw new UsernameNotFoundException(username);
        UserData user = data.get();
        List<GrantedAuthority> authorities = user.admin()? adminAuthorities:userAuthorities;
        return new User(user.getLogin(), user.getPassword(),authorities);
    }
}
