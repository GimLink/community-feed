package org.fastcampus.community_feed.acceptance;

import static org.fastcampus.community_feed.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.fastcampus.community_feed.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.fastcampus.community_feed.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.community_feed.post.application.dto.CreatePostRequestDto;
import org.fastcampus.community_feed.post.domain.PostPublicationState;
import org.fastcampus.community_feed.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

  /**
   * User 1 --- follow ---> User 2
   * User 1 --- follow ---> User 3
   */
  @BeforeEach
  void setUp() {
    super.init();
  }

  /**
   * User 2 create Post 1
   * User 1 Get Post 1 From Feed
   */
  @Test
  void givenUserHasFollowerWhenCreatePostThenFollowerFeedCanGetPost() {
    // given
    CreatePostRequestDto dto = new CreatePostRequestDto(2L, "1 content", PostPublicationState.PUBLIC);
    Long createdPostId = requestCreatePost(dto);

    // when, 팔로워의 피드 요청
    List<GetPostContentResponseDto> result = requestFeed(1L);

    // then
    assertEquals(1, result.size());
    assertEquals(createdPostId, result.get(0).getId());
  }

}
