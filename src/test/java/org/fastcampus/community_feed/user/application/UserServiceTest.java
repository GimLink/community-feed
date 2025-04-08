package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.fake.FakeObjectFactory;
import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.domain.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

  private final UserService userService = FakeObjectFactory.getUserService();

  @Test
  void givenUserInfoDto_whenCreateUser_thenFindUser() {
    //given
    CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

    //when
    User savedUser = userService.createUser(dto);

    //then
    User foundUser = userService.getUser(savedUser.getId());
    UserInfo userInfo = foundUser.getUserInfo();
    Assertions.assertEquals(savedUser.getId(), foundUser.getId());
    Assertions.assertEquals("test", userInfo.getName());
  }

}
