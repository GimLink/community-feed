package org.fastcampus.community_feed.auth.application;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.community_feed.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.community_feed.auth.application.interfaces.UserAuthRepository;
import org.fastcampus.community_feed.auth.domain.Email;
import org.fastcampus.community_feed.auth.domain.UserAuth;
import org.fastcampus.community_feed.auth.repository.entity.EmailVerificationEntity;
import org.fastcampus.community_feed.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserAuthRepository userAuthRepository;
  private final EmailVerificationRepository emailVerificationRepository;

  public Long registerUser(CreateUserAuthRequestDto dto) {
    Email email = Email.createEmail(dto.email());

    if (!emailVerificationRepository.isEmailVerified(email)) {
      throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
    }

    UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
    User user = new User(dto.name(), dto.profileImageUrl());
    userAuth = userAuthRepository.registerUser(userAuth, user);

    return userAuth.getUserId();

  }

}
