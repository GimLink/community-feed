package org.fastcampus.community_feed.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserInfoTest {

  @Test
  void givenNameAndProfileImage_whenCreated_thenThrowNothing() {
    //given
    String name = "abcd";
    String profileImageUrl = "";

    //when

    //then
    assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
  }

  @Test
  void givenBlankNameAndProfileImage_whenCreated_thenThrowError() {
    String name = "";
    String profileImageUrl = "";

    assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));


  }

}
