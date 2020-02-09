package test.pavelyurtaev.compensation.account.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.pavelyurtaev.compensation.account.model.Account;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByEmployeeId(Long id);
}
