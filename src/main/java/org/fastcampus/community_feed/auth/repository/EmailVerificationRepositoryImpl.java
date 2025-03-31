package org.fastcampus.community_feed.auth.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.community_feed.auth.domain.Email;
import org.fastcampus.community_feed.auth.repository.entity.EmailVerificationEntity;
import org.fastcampus.community_feed.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

  private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

  @Override
  public void createEmailVerification(Email email, String token) {
    String emailAddress = email.getEmailText();
    Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(
        emailAddress);

    if (entity.isPresent()) {
      EmailVerificationEntity emailVerificationEntity = entity.get();

      if (emailVerificationEntity.isVerified()) {
        throw new IllegalArgumentException("이미 인증된 이메일 입니다.");
      }

      emailVerificationEntity.updateToken(token);

    }

    EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress,
        token);
    jpaEmailVerificationRepository.save(emailVerificationEntity);
  }
}
