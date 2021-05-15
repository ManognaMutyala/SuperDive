package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    public Credentials isExists(int userid, int credentialId) {
        return credentialMapper.isExists(userid, credentialId);
    }

    public int insert(CredentialsForm credentials, int userid) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encodedKey);
        return credentialMapper.insert(new Credentials(userid, credentials.getCredentialurl(), credentials.getUsername(), encodedKey, encryptedPassword, null));
    }

    public List<Credentials> getAllCredentials(int userid) {
        List<Credentials> credentialsList = new ArrayList<>();
        credentialsList.addAll(credentialMapper.getAllCredentials(userid));
        for (Credentials credentials : credentialsList) {
            credentials.setEncpassword(credentials.getPassword());
            credentials.setPassword(encryptionService.decryptValue(credentials.getPassword(), credentials.getKey()));

        }
        return credentialsList;
    }

    public void deleteCredentialById(int userid, int credentialId) {
        credentialMapper.deleteCredentialById(userid, credentialId);
    }

    public void updateById(CredentialsForm credentials,int userid)
    {

        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), credentialMapper.getKey(credentials.getCredentialId(),userid));
        credentialMapper.update(credentials.getCredentialurl(), credentials.getUsername(),encryptedPassword,userid,credentials.getCredentialId());

    }




}
