package ma.enset.mvchospital.security.service;

import lombok.AllArgsConstructor;
import ma.enset.mvchospital.security.entities.AppRole;
import ma.enset.mvchospital.security.entities.AppUser;
import ma.enset.mvchospital.security.repo.AppRoleRepository;
import ma.enset.mvchospital.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) throws Exception {
        AppUser appUser= appUserRepository.findByUsername(username);
        if(appUser!=null) throw new RuntimeException("already exist");
        if(!password.equals(confirmPassword))throw new RuntimeException("doesn't matches");
        AppUser user=AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email).build();

        AppUser saved = appUserRepository.save(user);
        return saved;    }

    @Override
    public AppRole addNewRole(String role) throws Exception {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if(appRole!=null)throw new RuntimeException("already exist");
        AppRole appRole1=AppRole.builder()
                        .role(role)
                        .build();

        return appRoleRepository.save(appRole1);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole == null) {
            throw new RuntimeException("Role not found with ID: " + role);
        }

        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);

        AppRole appRole = appRoleRepository.findById(role).orElse(null);


        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);    }
}
