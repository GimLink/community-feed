package org.fastcampus.community_feed.admin.ui.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fastcampus.community_feed.common.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTableRequestDto extends Pageable {

  String name;

}
