package com.example.authtoken.model;

public class login {

    private String grant_type;
    private String scope ;
    private String client_id;
    private String client_secret ;
    private String username;
    //"etudiant"
    private String password;
    //"pm2020"

    public login(String username, String password) {
        this.username = username;
        this.password = password;
        this.grant_type="password" ;
        this.scope= "*" ;
        this.client_id="WMZNSSETCJDPTZSVETRNOPGYFKMAKHHQ" ;
        this.client_secret="5813427175e8e5d18452a90035077331" ;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
