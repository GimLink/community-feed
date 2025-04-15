package org.fastcampus.community_feed.post.repository;

import java.util.List;
import org.fastcampus.community_feed.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.community_feed.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!test"})
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

  @Override
  public List<GetPostContentResponseDto> getContentResponse(Long userid, Long postId) {
    return null;
  }
}
