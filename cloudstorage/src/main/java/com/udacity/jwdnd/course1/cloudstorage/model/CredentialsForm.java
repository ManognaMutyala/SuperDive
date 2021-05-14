package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialsForm {
    private String credentialurl;
    private String username;
    private String password;
    private Integer credentialId;



    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }


    public String getCredentialurl() {
        return credentialurl;
    }

    public void setCredentialurl(String credentialurl) {
        this.credentialurl = credentialurl;
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
