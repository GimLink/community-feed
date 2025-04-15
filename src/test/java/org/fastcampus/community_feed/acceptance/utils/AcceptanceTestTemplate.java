package org.fastcampus.community_feed.acceptance.utils;


import static org.fastcampus.community_feed.acceptance.steps.LoginAcceptanceSteps.requestLoginToken;

import org.fastcampus.community_feed.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

  @Autowired
  private DatabaseCleanUp cleanUp;

  @Autowired
  private DataLoader dataLoader;

  @BeforeEach
  public void init() {
    cleanUp.execute();
    dataLoader.loadData();
  }

  protected String getEmailToken(String email) {
    return dataLoader.getEmailToken(email);
  }

  protected void cleanUp() {
    cleanUp.execute();
  }

  protected boolean isEmailVerified(String email) {
    return dataLoader.isEmailVerified(email);
  }

  protected Long getUserId(String email) {
    return dataLoader.getUserId(email);
  }

  protected void createUser(String email) {
    dataLoader.createUser(email);
  }

  protected String login(String email) {
    return requestLoginToken(new LoginRequestDto(email, "password", "token"));
  }
}
