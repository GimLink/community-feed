package org.fastcampus.community_feed.admin.ui.query;

import org.fastcampus.community_feed.admin.ui.dto.GetTableListResponse;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {
  GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);

}
