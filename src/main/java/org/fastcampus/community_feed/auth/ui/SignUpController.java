package org.fastcampus.community_feed.auth.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.auth.application.AuthService;
import org.fastcampus.community_feed.auth.application.EmailService;
import org.fastcampus.community_feed.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.community_feed.auth.application.dto.SendEmailRequestDto;
import org.fastcampus.community_feed.common.ui.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signup")
@RequiredArgsConstructor
public class SignUpController {

  private final EmailService emailService;
  private final AuthService authService;

  @PostMapping("/send-verification-email")
  public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
    emailService.sendEmail(dto);
    return Response.ok(null);
  }

  @GetMapping("/verify-token")
  public Response<Void> verifyToken(String email, String token) {
    emailService.verifyEmail(email, token);
    return Response.ok(null);
  }

  @PostMapping("/register")
  public Response<Long> register(@RequestBody CreateUserAuthRequestDto dto) {
    authService.registerUser(dto);
    return Response.ok(1L);
  }
}
