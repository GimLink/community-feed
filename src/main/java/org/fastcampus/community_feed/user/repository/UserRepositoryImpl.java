package org.fastcampus.community_feed.user.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.repository.JPA.JpaUserRepository;
import org.fastcampus.community_feed.user.repository.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  @Override
  public User save(User user) {
    UserEntity entity = new UserEntity(user);
    entity = jpaUserRepository.save(entity);
    return entity.toUser();
  }

  @Override
  public User findById(Long id) {
    UserEntity entity = jpaUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    return entity.toUser();
  }
}
