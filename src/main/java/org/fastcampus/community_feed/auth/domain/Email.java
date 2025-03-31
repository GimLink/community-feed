package org.fastcampus.community_feed.auth.domain;

public class Email {

  private final String emailText;

  private Email(String email) {
    this.emailText = email;

  }

  public static Email createEmail(String email) {
    return new Email(email);
  }

  public String getEmailText() {
    return this.emailText;
  }

}
