package org.bob.accountmanager.java.spring.controller;

import org.bob.accountmanager.java.spring.model.Account;
import org.bob.accountmanager.java.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/all")
    public List<Account> all() {
        return accountService.getAll();
    }

    @RequestMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @RequestMapping("/create")
    public Account create(@RequestBody Account account) {
        return accountService.create(account);
    }

    @RequestMapping("/update")
    public Account update(@RequestBody Account account) {
        return accountService.update(account);
    }

    @RequestMapping("/delete/{id}")
    public Account deleteById(@PathVariable Long id) {
        return accountService.deleteById(id);
    }
}
