package org.fastcampus.community_feed.auth.repository.jpa;

import org.fastcampus.community_feed.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {

}
