package org.fastcampus.community_feed.admin.ui.dto.posts;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fastcampus.community_feed.common.utils.TimeCalculator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostTableResponseDto {
  @Getter
  private Long postId;
  @Getter
  private Long userId;
  @Getter
  private String userName;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public String getContent() {
    if (content.length() > 10) {
      return content.substring(0, 10) + "....";
    }
    return content;
  }

  public String getCreatedAt() {
    return TimeCalculator.getFormattedDate(createdAt);
  }
  public String getUpdattedAt() {
    return TimeCalculator.getFormattedDate(updatedAt);
  }



}
