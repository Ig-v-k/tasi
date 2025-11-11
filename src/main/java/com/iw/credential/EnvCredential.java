package com.iw.credential;

import com.iw.Credential;

public final class EnvCredential implements Credential {

    final String login;
    final String password;

    public EnvCredential(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String login() {
        return System.getenv(login);
    }

    @Override
    public String password() {
        return System.getenv(password);
    }
}
