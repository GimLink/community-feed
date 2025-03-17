package org.fastcampus.community_feed.user.application;

import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.community_feed.common.FakeObjectFactory;
import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.dto.FollowUserRequestDto;
import org.fastcampus.community_feed.user.application.interfaces.UserRelationRepository;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.repository.FakeUserRelationRepository;
import org.fastcampus.community_feed.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {

  private final UserService userService = FakeObjectFactory.getUserService();

  private final UserRelationService relationService = FakeObjectFactory.getUserRelationService();

  private User user1;
  private User user2;
  private FollowUserRequestDto requestDto;

  @BeforeEach
  void init() {
    CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
    this.user1 = userService.createUser(dto);
    this.user2 = userService.createUser(dto);

    this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
  }


  @Test
  void givenCreateTwoUsers_whenFollow_thenUserFollowSaved() {
    //when
    relationService.followUser(requestDto);

    assertEquals(1, user1.getFollowingCount());
    assertEquals(1, user2.getFollowerCount());

  }


  @Test
  void givenCreateTwoUsersFollowed_whenFollow_thenUserThrowError() {
    //given
    relationService.followUser(requestDto);

    //when, then
    assertThrows(IllegalArgumentException.class, () -> relationService.followUser(requestDto));
  }

  @Test
  void givenCreateOneUser_whenFollow_thenUserThrowError() {
    //given
    FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

    //when, then
    assertThrows(IllegalArgumentException.class, () -> relationService.followUser(sameUser));
  }

  @Test
  void givenCreateTwoUsersFollowed_whenUnfollow_thenUnfollowSaved() {
    //given
    relationService.followUser(requestDto);

    //when
    relationService.unfollowUser(requestDto);

    //then
    assertEquals(0, user1.getFollowingCount());
    assertEquals(0, user2.getFollowerCount());
  }

  @Test
  void givenCreateTwoUsersFollowed_whenUnfollow_thenUserThrowError() {
    //given
    relationService.followUser(requestDto);

    //when
    relationService.unfollowUser(requestDto);

    //when, then
    assertThrows(IllegalArgumentException.class, () -> relationService.unfollowUser(requestDto));
  }

  @Test
  void givenCreateOneUser_whenUnfollow_thenUserThrowError() {
    //given
    FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

    //when, then
    assertThrows(IllegalArgumentException.class, () -> relationService.unfollowUser(sameUser));
  }

}
