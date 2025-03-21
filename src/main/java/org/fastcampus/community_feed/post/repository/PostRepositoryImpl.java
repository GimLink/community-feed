package org.fastcampus.community_feed.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.application.interfaces.PostRepository;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.jpa.JpaPostRepository;
import org.fastcampus.community_feed.post.repository.post_queue.UserPostQueueCommandRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

  private final JpaPostRepository jpaPostRepository;
  private final UserPostQueueCommandRepository commandRepository;

  @Override
  public Post findById(Long id) {
    PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
    return postEntity.toPost();
  }

  @Override
  @Transactional
  public Post save(Post post) {
    PostEntity postEntity = new PostEntity(post);

    if (postEntity.getId() != null) {
      jpaPostRepository.updatePostEntity(postEntity);
      return postEntity.toPost();

    }
    postEntity = jpaPostRepository.save(postEntity);
    commandRepository.publishPost(postEntity);
    return postEntity.toPost();
  }
}
