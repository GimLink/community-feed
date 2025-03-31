package org.fastcampus.community_feed.auth.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.auth.application.dto.SendEmailRequestDto;
import org.fastcampus.community_feed.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signup")
@RequiredArgsConstructor
public class SignUpController {

  @PostMapping("/semd-verification-email")
  public Response<Void> sendEmail(SendEmailRequestDto dto) {
    return Response.ok(null);
  }
}
