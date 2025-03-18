package org.fastcampus.community_feed.user.domain;

public class UserInfo {
  private final String name;
  private final String profileImage;

  public UserInfo(String name, String profileImage) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("name should not be null or empty");
    }

    this.name = name;
    this.profileImage = profileImage;
  }

  public String getName() {
    return name;
  }
  public String getProfileImage() {return profileImage;}
}
