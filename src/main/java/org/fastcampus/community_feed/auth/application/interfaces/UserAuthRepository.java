package org.fastcampus.community_feed.auth.application.interfaces;

import org.fastcampus.community_feed.auth.domain.UserAuth;
import org.fastcampus.community_feed.user.domain.User;

public interface UserAuthRepository {

  UserAuth registerUser(UserAuth auth, User user);

  UserAuth loginUser(String email, String password, String fcmToken);

}
