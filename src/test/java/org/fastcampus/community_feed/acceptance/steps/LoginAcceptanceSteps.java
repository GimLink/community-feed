package org.fastcampus.community_feed.acceptance.steps;

import io.restassured.RestAssured;
import org.fastcampus.community_feed.auth.application.dto.LoginRequestDto;
import org.fastcampus.community_feed.auth.application.dto.UserAccessTokenResponseDto;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {

  public static String requestLoginToken(LoginRequestDto dto) {
    UserAccessTokenResponseDto res = RestAssured
        .given()
        .body(dto)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("login")
        .then()
        .extract()
        .jsonPath()
        .getObject("value", UserAccessTokenResponseDto.class);
    return res.accessToken();
  }

  public static Integer requestLoginGetResponseCode(LoginRequestDto dto) {
    return RestAssured
        .given()
        .body(dto)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("login")
        .then()
        .extract()
        .jsonPath().getObject("code", Integer.class);
  }


}
