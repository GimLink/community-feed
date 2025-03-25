package org.fastcampus.community_feed.post.repository;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.community_feed.post.ui.dto.GetContentResponseDto;
import org.fastcampus.community_feed.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

  private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

  public FakeUserPostQueryRepository(FakeUserQueueRedisRepository fakeUserQueueRedisRepository) {
    this.fakeUserQueueRedisRepository = fakeUserQueueRedisRepository;
  }

  @Override
  public List<GetPostContentResponseDto> getContentResponse(Long userId, Long postId) {
    List<PostEntity> postEntities = fakeUserQueueRedisRepository.getPostListByUserId(userId);
    List<GetPostContentResponseDto> result = new ArrayList<>();

    for (PostEntity postEntity : postEntities) {
      GetPostContentResponseDto res = GetPostContentResponseDto.builder()
          .id(postEntity.getId())
          .build();
      result.add(res);
    }
    return result;
  }
}
