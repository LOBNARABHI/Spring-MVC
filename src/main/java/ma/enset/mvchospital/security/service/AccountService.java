package ma.enset.mvchospital.security.service;

import ma.enset.mvchospital.security.entities.AppRole;
import ma.enset.mvchospital.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String email, String confirmPassword ) throws Exception;
    AppRole addNewRole(String role) throws Exception;
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);
}
