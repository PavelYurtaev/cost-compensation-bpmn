package test.pavelyurtaev.compensation.account.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.pavelyurtaev.compensation.account.model.Account;
import test.pavelyurtaev.compensation.account.model.repo.AccountRepo;
import test.pavelyurtaev.compensation.employee.model.Employee;
import test.pavelyurtaev.compensation.employee.model.repo.EmployeeRepo;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountDelegate implements JavaDelegate {

    private final Logger logger = LoggerFactory.getLogger(AccountDelegate.class);
    private final AccountRepo accountRepo;
    private final EmployeeRepo employeeRepo;

    @Autowired
    public AccountDelegate(AccountRepo accountRepo, EmployeeRepo employeeRepo) {
        this.accountRepo = accountRepo;
        this.employeeRepo = employeeRepo;
    }

    public boolean isAccountExists(Long personnelNumber) {
        return accountRepo.findByEmployeeId(personnelNumber).isPresent();
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        final Long personnelNumber = (Long) delegateExecution.getVariable("personnelNumber");
        final Employee employee = employeeRepo.findById(personnelNumber).orElseGet(this::createEmployee);
        final Account account = new Account();
        account.setAmount(BigDecimal.ZERO);
        account.setEmployee(employee);
        final Account savedAccount = accountRepo.save(account);
        delegateExecution.setVariable("accountId", savedAccount.getId());
        logger.info("Account created, id " + savedAccount.getId());
    }

    private Employee createEmployee() {
        final Employee employee = new Employee();
        employee.setName("Employee" + ThreadLocalRandom.current().nextInt());
        return employeeRepo.save(employee);
    }

    @Transactional
    public void compensateCost(final Long accountId, final Long amount) {
        final Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("No Account with id " + accountId));
        account.setAmount(account.getAmount().add(BigDecimal.valueOf(amount)));
        logger.info("New Account amount " + account.getAmount());
    }
}
