package org.fastcampus.community_feed.common.idempotency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fastcampus.community_feed.common.ui.Response;

@Getter
@AllArgsConstructor
public class Idempotency {

  private final String key;
  private final Response<?> response;

}
