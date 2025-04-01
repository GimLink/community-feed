package org.fastcampus.community_feed.auth.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.auth.application.interfaces.UserAuthRepository;
import org.fastcampus.community_feed.auth.domain.UserAuth;
import org.fastcampus.community_feed.auth.repository.entity.UserAuthEntity;
import org.fastcampus.community_feed.auth.repository.jpa.JpaUserAuthRepository;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

  private final JpaUserAuthRepository jpaUserAuthRepository;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserAuth registerUser(UserAuth auth, User user) {
    User saveUser = userRepository.save(user);
    UserAuthEntity userAuthEntity = new UserAuthEntity(auth, saveUser.getId());
    userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);
    return userAuthEntity.toUserAuth();
  }
}
