package org.fastcampus.community_feed.post.repository;

import java.util.List;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {

  @Override
  public void publishPostToFollowerUserList(PostEntity postEntity, List<Long> userIdList) {

  }

  @Override
  public void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId) {

  }

  @Override
  public void deleteFeed(Long userId, Long authorId) {

  }
}
