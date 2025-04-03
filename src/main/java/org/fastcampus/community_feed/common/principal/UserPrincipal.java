package org.fastcampus.community_feed.common.principal;

import lombok.Getter;
import org.fastcampus.community_feed.auth.domain.UserRole;

@Getter
public class UserPrincipal {

  private Long userId;
  private UserRole userRole;

  public UserPrincipal(Long userId, String role) {
    this.userId = userId;
    this.userRole = UserRole.valueOf(role);
  }

}
