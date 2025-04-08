package org.fastcampus.community_feed.admin.ui.dto.users;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fastcampus.community_feed.common.utils.TimeCalculator;

@Getter
@Setter
@NoArgsConstructor
public class GetUserTableResponseDto {

  private Long id;
  private String email;
  private String name;
  private String role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime lastLoginAt;

  public String getCreatedAt() {
    return TimeCalculator.getFormattedDate(createdAt);
  }

  public String getUpdatedAt() {
    return TimeCalculator.getFormattedDate(updatedAt);
  }

  public String getLastLoginAt() {
    return TimeCalculator.getFormattedDate(lastLoginAt);
  }

}
