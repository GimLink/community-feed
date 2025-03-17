package org.fastcampus.community_feed.common.ui;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.community_feed.common.domain.exception.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public Response<Void> handleIllegealArgumentException(IllegalArgumentException exception) {
    log.error(exception.getMessage());
    return Response.error(ErrorCode.INVALID_INPUT_VALUE);
  }

  @ExceptionHandler(Exception.class)
  public Response<Void> handleException(Exception exception) {
    log.error(exception.getMessage());
    return Response.error(ErrorCode.INVALID_INPUT_VALUE);
  }
}
