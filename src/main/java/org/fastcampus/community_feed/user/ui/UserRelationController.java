package org.fastcampus.community_feed.user.ui;

import javax.management.relation.RelationService;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.common.ui.Response;
import org.fastcampus.community_feed.user.application.UserRelationService;
import org.fastcampus.community_feed.user.application.dto.FollowUserRequestDto;
import org.fastcampus.community_feed.user.application.interfaces.UserRelationRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

  private final UserRelationService relationService;

  @PostMapping("/follow")
  public Response<Void> followUser(@RequestBody FollowUserRequestDto dto) {
    relationService.followUser(dto);
    return Response.ok(null);
  }

  @PostMapping("/unfollow")
  public Response<Void> unfollowuser(@RequestBody FollowUserRequestDto dto) {
    relationService.unfollowUser(dto);
    return Response.ok(null);
  }

}
