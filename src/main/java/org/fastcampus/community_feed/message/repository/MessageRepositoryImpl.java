package org.fastcampus.community_feed.message.repository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.fastcampus.community_feed.message.application.interfaces.MessageRepository;
import org.fastcampus.community_feed.message.repository.entity.FcmTokenEntity;
import org.fastcampus.community_feed.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

  private final JpaFcmTokenRepository jpaFcmTokenRepository;

  private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %s님 글에 좋아요를 눌렀습니다.";
  private static final String MESSAGE_KEY = "message";

  @Override
  public void sendLikeMessage(User sender, User targetUser) {
    Optional<FcmTokenEntity> fcmTokenEntity = jpaFcmTokenRepository.findById(targetUser.getId());
    if (fcmTokenEntity == null) {
      return;
    }

    FcmTokenEntity token = fcmTokenEntity.get();
    System.out.println(token.getUserId());
    Message message = Message.builder()
        .putData(MESSAGE_KEY,
            LIKE_MESSAGE_TEMPLATE.formatted(sender.getName(), targetUser.getName()))
        .setToken(token.getFcmToken())
        .build();
    FirebaseMessaging.getInstance().sendAsync(message);
    //브라우저 알림 권한 설정 안해두면 안뜬다

  }
}
