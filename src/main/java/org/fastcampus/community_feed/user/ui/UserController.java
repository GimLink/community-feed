package org.fastcampus.community_feed.user.ui;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.common.ui.Response;
import org.fastcampus.community_feed.user.application.UserService;
import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.dto.GetUserListResponseDto;
import org.fastcampus.community_feed.user.application.dto.GetUserResponseDto;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.repository.JPA.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final JpaUserListQueryRepository jpaUserListQueryRepository;

  @PostMapping
  public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
    User user = userService.createUser(dto);
    return Response.ok(user.getId());
  }

  @GetMapping("/{userId}")
  public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId) {
    return Response.ok(userService.getUserProfile(userId));
  }

  @GetMapping("/{userId}/follower")
  public Response<List<GetUserListResponseDto>> getFollowerList(
      @PathVariable(name = "userId") Long id) {

    List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowerUserList(id);
    return Response.ok(result);
  }

  @GetMapping("/{userId}/following")
  public Response<List<GetUserListResponseDto>> getFollowingList(
      @PathVariable(name = "userId") Long id) {
    List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowingUserList(id);
    return Response.ok(result);
  }

}
