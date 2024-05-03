package ma.enset.mvchospital.security.repo;

import ma.enset.mvchospital.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {
}
