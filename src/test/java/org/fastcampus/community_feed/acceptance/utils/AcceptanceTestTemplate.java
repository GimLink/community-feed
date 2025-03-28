package org.fastcampus.community_feed.acceptance.utils;


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

}
