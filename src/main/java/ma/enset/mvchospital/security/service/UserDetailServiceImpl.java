package ma.enset.mvchospital.security.service;

import lombok.AllArgsConstructor;
import ma.enset.mvchospital.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appuser=accountService.loadUserByUsername(username);
        if(appuser==null)throw new UsernameNotFoundException(String.format("user %s not found ",username));
        String[] roles = appuser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        UserDetails  userDetails= User
                .withUsername(appuser.getUsername())
                .password(appuser.getPassword())
                .roles(roles).build();
        return userDetails  ;    }
}
