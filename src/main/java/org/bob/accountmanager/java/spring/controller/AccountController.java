package org.bob.accountmanager.java.spring.controller;

import org.bob.accountmanager.java.spring.model.Account;
import org.bob.accountmanager.java.spring.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> all() {
        return accountService.getAll();
    }

    @GetMapping("{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return accountService.create(account);
    }

    @PutMapping
    public Account update(@RequestBody Account account) {
        return accountService.update(account);
    }

    @DeleteMapping("{id}")
    public Account deleteById(@PathVariable Long id) {
        return accountService.deleteById(id);
    }
}
