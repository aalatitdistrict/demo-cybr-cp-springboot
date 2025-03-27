/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.cembra.demo_cybr;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javapasswordsdk.PSDKPassword;
import javapasswordsdk.PSDKPasswordRequest;
import javapasswordsdk.exceptions.PSDKException;

/**
 *
 * @author amine
 */
public class CyberArkCPRequest {

    Logger logger = LoggerFactory.getLogger(CyberArkCPRequest.class);

    private final String appId;
    private final String safe;
    private final String folder;
    private final String objectName;
    private final String reason;
    private final List<String> additionalProperties;

    public CyberArkCPRequest(String appId, String safe, String folder, String objectName) {
        this.appId = appId;
        this.safe = safe;
        this.folder = folder;
        this.objectName = objectName;
        this.reason = null;
        this.additionalProperties = null;
    }

    public CyberArkCPRequest(String appId, String safe, String folder, String objectName, String reason) {
        this.appId = appId;
        this.safe = safe;
        this.folder = folder;
        this.objectName = objectName;
        this.reason = reason;
        this.additionalProperties = null;
    }

    public CyberArkCPRequest(String appId, String safe, String folder, String objectName, String reason,
            List<String> additionalProperties) {
        this.appId = appId;
        this.safe = safe;
        this.folder = folder;
        this.objectName = objectName;
        this.reason = reason;
        this.additionalProperties = additionalProperties;
    }

    public String getAppId() {
        return appId;
    }

    public String getSafe() {
        return safe;
    }

    public String getFolder() {
        return folder;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getQuery() {
        return "Safe=" + this.safe + ";Folder=" + this.folder + ";Object=" + this.objectName;
    }

    public String getReason() {
        return reason;
    }

    public PSDKPassword getAccount() {
        PSDKPassword account = null;
        try {
            PSDKPasswordRequest passRequest = new PSDKPasswordRequest();

            // Set request properties
            passRequest.setAppID(this.appId);
            passRequest.setQuery(this.getQuery());
            if (this.reason != null)
                passRequest.setReason(this.reason);
            if (this.additionalProperties != null) {
                passRequest.setRequiredProperties((ArrayList<String>) this.additionalProperties);
            }

            // Get password object
            account = javapasswordsdk.PasswordSDK.getPassword(passRequest);

            // Get password content
            // content = account.getSecureContent();
            // ...
            // Use password content here
            // Use password properties - i.e password.getUserName()
            // ...
        } catch (PSDKException ex) {
            ex.printStackTrace();
        }
        // finally {
        // if (content != null) {
        // // Clean the returned object
        // Arrays.fill(content, (char) 0);
        // }
        // if (account != null) {
        // // Dispose of resources used by this PSDKPassword object
        // try {
        // account.dispose();
        // } catch (PSDKException e) {
        // }
        // }
        // }
        return account;
    }

}
