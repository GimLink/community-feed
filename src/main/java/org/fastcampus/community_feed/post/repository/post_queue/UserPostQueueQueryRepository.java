package org.fastcampus.community_feed.post.repository.post_queue;

import java.util.List;
import org.fastcampus.community_feed.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {

  List<GetPostContentResponseDto> getContentResponse(Long userid, Long postId);

}
