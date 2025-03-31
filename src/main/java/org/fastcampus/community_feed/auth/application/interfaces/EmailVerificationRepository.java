package org.fastcampus.community_feed.auth.application.interfaces;

import org.fastcampus.community_feed.auth.domain.Email;

public interface EmailVerificationRepository {

  void createEmailVerification(Email email, String token);

}
