package org.fastcampus.community_feed.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.message.application.interfaces.MessageRepository;
import org.fastcampus.community_feed.post.application.interfaces.LikeRepository;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.comment.Comment;
import org.fastcampus.community_feed.post.repository.entity.comment.CommentEntity;
import org.fastcampus.community_feed.post.repository.entity.like.LikeEntity;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.community_feed.post.repository.jpa.JpaLikeRepository;
import org.fastcampus.community_feed.post.repository.jpa.JpaPostRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

  @PersistenceContext
  private final EntityManager entityManager;

  private final JpaPostRepository jpaPostRepository;
  private final JpaLikeRepository jpaLikeRepository;
  private final JpaCommentRepository jpaCommentRepository;
  private final MessageRepository messageRepository;

  @Override
  public boolean checkLike(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    return jpaLikeRepository.existsById(likeEntity.getId());
  }

  @Override
  public boolean checkLike(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    return jpaLikeRepository.existsById(likeEntity.getId());
  }

  @Override
  @Transactional
  public void like(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    entityManager.persist(likeEntity);
    jpaPostRepository.updateLikeCount(post.getId(), 1);
    messageRepository.sendLikeMessage(user, post.getAuthor());
  }

  @Override
  @Transactional
  public void like(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    entityManager.persist(likeEntity);
    jpaCommentRepository.updateLikeCount(comment.getId(), 1);
  }

  @Override
  @Transactional
  public void unlike(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    jpaLikeRepository.deleteById(likeEntity.getId());
    jpaPostRepository.updateLikeCount(post.getId(), -1);
  }

  @Override
  @Transactional
  public void unlike(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    jpaLikeRepository.deleteById(likeEntity.getId());
    jpaCommentRepository.updateLikeCount(comment.getId(), -1);
  }
}
