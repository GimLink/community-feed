package org.fastcampus.community_feed.acceptance.steps;

import io.restassured.RestAssured;
import org.fastcampus.community_feed.auth.application.dto.SendEmailRequestDto;
import org.springframework.http.MediaType;

public class SignUpAcceptanceSteps {

  public static Integer requestSendEmail(SendEmailRequestDto dto) {
    return RestAssured
        .given()
        .body(dto)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("/signup/send-veritification-email")
        .then()
        .extract()
        .jsonPath().get("code");
  }

}
