package org.fastcampus.community_feed.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;

public class FakeUserRepository implements UserRepository {

  private final Map<Long, User> store = new HashMap<>();

  @Override
  public User save(User user) {
    if (user.getId() != null) {
      store.put(user.getId(), user);
    }
    Long id = store.size() + 1L;
    User newUser = new User(id, user.getUserInfo());
    store.put(id, newUser);
    return newUser;
  }

  @Override
  public User findById(Long id) {
    return store.get(id);
  }
}
