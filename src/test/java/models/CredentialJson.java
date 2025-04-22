package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CredentialJson {
    @JsonProperty("credentials")
    private Map<String, Credential> mapCredentials;

    public Map<String, Credential> getCredentials() {
        return mapCredentials;
    }
}
