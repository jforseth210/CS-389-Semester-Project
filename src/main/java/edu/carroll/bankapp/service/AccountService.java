package edu.carroll.bankapp.service;

import edu.carroll.bankapp.jpa.model.Account;
import edu.carroll.bankapp.jpa.model.User;
import edu.carroll.bankapp.jpa.repo.AccountRepository;
import edu.carroll.bankapp.web.controller.DashboardController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for managing accounts.
 */
@Service
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);

    private AccountRepository accountRepo;
    private UserService userService;

    public AccountService(UserService userService, AccountRepository accountRepo) {
        this.userService = userService;
        this.accountRepo = accountRepo;
    }

    /**
     * Returns a list of Accounts owned by the currently logged-in user
     *
     * @return
     */
    public List<Account> getUserAccounts() {
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser == null) {
            return null;
        }
        List<Account> accountsList = accountRepo.findByOwner(loggedInUser);
        return accountsList;
    }

    /**
     * Returns the Account matching the given id, if the account is owned by the currently logged-in user.
     * Returns null if the requested account is owned by someone else.
     *
     * @param id the id of the requested account
     * @return
     */
    public Account getUserAccount(int id) {
        Account account = accountRepo.findById(id).get(0);
        String currentUser = userService.getLoggedInUser().getUsername();
        String accountOwner = account.getOwner().getUsername();
        if (!accountOwner.equals(currentUser)) {
            log.warn(currentUser + " attempted to access one of" + accountOwner + "'s accounts");
            return null;
        }
        return account;
    }
}
