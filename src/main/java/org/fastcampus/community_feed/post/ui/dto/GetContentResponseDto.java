package org.fastcampus.community_feed.post.ui.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetContentResponseDto {
  private Long id;
  private String content;
  private Long userId;
  private String userName;
  private String userProfileImage;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;
  private Integer likeCount;
  private boolean isLikedByMe;

}
