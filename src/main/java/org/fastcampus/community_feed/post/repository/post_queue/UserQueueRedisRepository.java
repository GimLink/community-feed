package org.fastcampus.community_feed.post.repository.post_queue;

import java.util.List;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.springframework.stereotype.Repository;

public interface UserQueueRedisRepository {

  void publishPostToFollowerUserList(PostEntity postEntity, List<Long> userIdList);

  void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId);

  void deleteFeed(Long userId, Long authorId);

}
