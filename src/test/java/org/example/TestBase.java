package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class TestBase {

    protected static String testInputFilePath = "src/test/JSONTestInput/testInput.json";
    protected String registrationEndPoint = "/v1/auth/register";
    protected String registrationPayloadFilePath = "src/test/JSONRequestBodies/registrationRequestBody.json";
    protected String verificationEndPoint = "/v1/auth/verify";
    protected String verificationPayloadFilePath = "src/test/JSONRequestBodies/verificationRequestBody.json";
    protected String LoginEndPoint = "/v1/auth/login";
    protected String loginPayloadFilePath = "src/test/JSONRequestBodies/loginRequestBody.json";
    protected static TestInput testInput;

    protected static ObjectMapper objectMapper;

    @BeforeClass
    public static void setup() throws IOException {
        RestAssured.baseURI = "https://provider.test.carefer.co/api/provider";

        objectMapper = new ObjectMapper();
        testInput = objectMapper.readValue(new File(testInputFilePath), TestInput.class);
    }
}
