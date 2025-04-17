package org.fastcampus.community_feed.message.repository;

import org.fastcampus.community_feed.message.repository.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {

}
