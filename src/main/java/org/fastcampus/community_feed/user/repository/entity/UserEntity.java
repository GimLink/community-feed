package org.fastcampus.community_feed.user.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.common.repository.entity.TimeBaseEntity;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "community_user")
@Getter
@DynamicUpdate
public class UserEntity extends TimeBaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String profileImage;
  private int followingCount;
  private int followerCount;

  @CreatedDate
  @Column(updatable = false)
  private LocalDate regDate;

  public UserEntity(User user) {

    this.id = user.getId();
    this.name = user.getName();
    this.profileImage = user.getProfileImage();
    this.followingCount = user.getFollowingCount();
    this.followerCount = user.getFollowerCount();
  }

  public User toUser() {
    return User.builder()
        .id(id)
        .userInfo(new UserInfo(name, profileImage))
        .followerCount(new PositiveIntegerCounter(followerCount))
        .followingCount(new PositiveIntegerCounter(followingCount))
        .build();
  }
}
