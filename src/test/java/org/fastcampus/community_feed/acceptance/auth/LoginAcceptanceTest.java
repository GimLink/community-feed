package org.fastcampus.community_feed.acceptance.auth;


import static org.fastcampus.community_feed.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static org.fastcampus.community_feed.acceptance.steps.LoginAcceptanceSteps.requestLoginToken;
import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.community_feed.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.community_feed.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {

  private final String email = "email@email.com";

  @BeforeEach
  void setUp() {
    this.cleanUp();
    this.createUser(email);
  }

  @Test
  void givenEmailAndPassword_whenLogin_thenReturnToken() {
    //given
    LoginRequestDto dto = new LoginRequestDto(email, "password", "token");

    //when
    String token = requestLoginToken(dto);

    //then
    assertNotNull(token);
  }

  @Test
  void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero() {

    //given
    LoginRequestDto dto = new LoginRequestDto(email, "wrong password", "token");

    //when
    Integer code = requestLoginGetResponseCode(dto);

    //then
    assertEquals(400, code);
  }

}
