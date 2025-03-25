package org.fastcampus.community_feed.post.repository.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.community_feed.post.repository.jpa.JpaPostRepository;
import org.fastcampus.community_feed.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.community_feed.user.repository.JPA.JpaUserRelationRepository;
import org.fastcampus.community_feed.user.repository.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{

  private final JpaPostRepository jpaPostRepository;
  private final JpaUserRelationRepository jpaUserRelationRepository;
  private final UserQueueRedisRepository redisRepository;

  @Override
  @Transactional
  public void publishPost(PostEntity postEntity) {
    UserEntity userEntity = postEntity.getAuthor();
    List<Long> followerIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
    redisRepository.publishPostToFollowerUserList(postEntity, followerIds);
  }

  @Override
  @Transactional
  public void saveFollowPost(Long userId, Long targetId) {
    List<PostEntity> posts = jpaPostRepository.findAllPostByAuthorId(targetId);
    redisRepository.publishPostListToFollowingUser(posts, userId);
  }

  @Override
  @Transactional
  public void deleteUnfollowPost(Long userId, Long targetId) {
    redisRepository.deleteFeed(userId, targetId);

  }
}
