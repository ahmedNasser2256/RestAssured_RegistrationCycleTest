package org.example;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class AppTest extends TestBase {
    @Test(priority = 1)
    public void postRegistrationRequest() throws IOException {
        Map<String, Object> request = getRegistrationRequestBody();
        given()
                .header("Content-Type", "application/json")
                .header("Platform", "careferProviderApplication2Ej!%")
                .and()
                .body(request)
                .when()
                .post(baseURI + registrationEndPoint)
                .then()
                .body("success", Is.is(true))
                .body("status_code", Is.is(200))
                .body("message", Is.is("Success Register."));

        
    }


    @Test(priority = 2)
    public void postVerificationRequest() throws IOException {
        Map<String, Object> request = getVerificationRequestBody();
         given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Platform", "careferProviderApplication2Ej!%")
                .and()
                .body(request)
                .when()
                .post(baseURI + verificationEndPoint )
                .then()
                 .body("success", Is.is(true))
                 .body("status_code", Is.is(200))
                 .body("message", Is.is("Success verify."));

    }

    @Test(priority = 3)
    public void postLoginRequest() {
        File jsonFileRequestBody = new File(loginPayloadFilePath);
         given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Platform", "careferProviderApplication2Ej!%")
                .and()
                .body(jsonFileRequestBody)
                .when()
                .post(baseURI + LoginEndPoint )
                .then()
                 .body("success", Is.is(true))
                 .body("status_code", Is.is(200))
                 .body("message", Is.is("Success Login."));

    }

    private Map<String, Object> getVerificationRequestBody() throws IOException {
        Map<String,Object> request =
                objectMapper.readValue(new File(verificationPayloadFilePath), HashMap.class);
        request.put("mobile", testInput.getMobile());
        return request;
    }

    private Map<String, Object> getRegistrationRequestBody() throws IOException {
        Map<String,Object> request =
                objectMapper.readValue(new File(registrationPayloadFilePath), HashMap.class);
        request.put("mobile", testInput.getMobile());
        request.put("name", testInput.getName());
        request.put("password", testInput.getPassword());
        request.put("password_confirmation", testInput.getConfirmPassword());

        return request;
    }

    private Map<String, Object> getLoginRequestBody() throws IOException {
        Map<String,Object> request =
                objectMapper.readValue(new File(loginPayloadFilePath), HashMap.class);
        request.put("mobile", testInput.getMobile());
        request.put("password", testInput.getPassword());

        return request;
    }
}