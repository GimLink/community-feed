package org.fastcampus.community_feed.user.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;

@Getter
@Builder
@AllArgsConstructor
public class User {
  private Long id;
  private UserInfo userInfo;
  private PositiveIntegerCounter followingCount;
  private PositiveIntegerCounter followerCount;

  public User(Long id, UserInfo userInfo) {
    if (userInfo == null) {
      throw new IllegalArgumentException("UserInfo cannot be null");
    }

    this.id = id;
    this.userInfo = userInfo;
    this.followingCount = new PositiveIntegerCounter();
    this.followerCount = new PositiveIntegerCounter();
  }

  public void follow(User followee) {
    if (this.equals(followee)) {
      throw new IllegalArgumentException("");
    }

    followingCount.increase();
    followee.increaseFollowerCount();
  }

  public void unfollow(User followee) {
    if (this.equals(followee)) {
      throw new IllegalArgumentException("");
    }

    followingCount.decrease();
    followee.decreaseFollowerCount();
  }

  private void increaseFollowerCount() {
    followerCount.increase();
  }

  private void decreaseFollowerCount() {
    followerCount.decrease();
  }

  public int getFollowingCount() {
    return followingCount.getCount();
  }

  public int getFollowerCount() {
    return followerCount.getCount();
  }
  public String getName() {return userInfo.getName();}
  public String getProfileImageUrl() {return userInfo.getProfileImageUrl();}

  public Long getId() {
    return id;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
