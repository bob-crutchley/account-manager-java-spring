package org.bob.accountmanager.java.spring.account;

import org.bob.accountmanager.java.spring.controller.AccountController;
import org.bob.accountmanager.java.spring.model.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateAccountTest {

    @Autowired
    AccountController accountController;

    private final Account account1 = new Account();
    private final Account account2 = new Account();

    @Before
    public void before() {
        this.account1.setId(1L);
        this.account1.setForename("forename");
        this.account1.setSurname("surname");
        this.account2.setId(2L);
        this.account2.setForename("forename");
        this.account2.setSurname("surname");
    }

    @After
    public void after() {
        for (Account account : accountController.all()) {
            accountController.deleteById(account.getId());
        }
    }

    @Test
    public void createAccount() {
        assertAccountsMatch(account1, accountController.create(account1));
    }

    @Test
    public void getById() {
        Account createdAccount = accountController.create(account1);
        assertAccountsMatch(createdAccount, accountController.getById(createdAccount.getId()));
    }

    @Test
    public void updateAccount() {
        accountController.create(account1);
        account1.setForename("updated_forename");
        account1.setSurname("updated_surname");
        assertAccountsMatch(account1, accountController.update(account1));
    }

    @Test
    public void deleteById() {
        accountController.deleteById(accountController.create(account1).getId());
        assert accountController.getById(account1.getId()) == null;
    }

    @Test
    public void all() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        AtomicInteger id = new AtomicInteger();
        accountController.all().forEach(account ->  {
            id.getAndIncrement();
            assertAccountsMatch(account, accounts.get(id.get()));
        });
    }

    private void assertAccountsMatch(Account firstAccount, Account secondAccount) {
        assert firstAccount.getForename().equals(secondAccount.getForename());
        assert firstAccount.getSurname().equals(secondAccount.getSurname());
    }
}
