package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.CredentialJson;
import utilities.Logs;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    private static final String credencialesPath = "src/test/resources/data/login/credentials.json";

    public static CredentialJson getMapCredentials() {
        final var objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(credencialesPath), CredentialJson.class);
        } catch (IOException ioException) {
            Logs.error("Error to Read the Json Document Credentials; %s", ioException.getLocalizedMessage());
            throw new RuntimeException(ioException.getLocalizedMessage());
        }
    }
}
