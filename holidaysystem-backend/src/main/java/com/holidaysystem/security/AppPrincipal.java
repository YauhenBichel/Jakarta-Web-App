package com.holidaysystem.security;

import java.security.Principal;

import com.holidaysystem.model.AccountDetailsModel;

public class AppPrincipal implements Principal {

    private AccountDetailsModel accountDetail;

    public AppPrincipal(AccountDetailsModel accountDetail) {
        this.accountDetail = accountDetail;
    }

    @Override
    public String getName() {
        return accountDetail.getEmail();
    }
}