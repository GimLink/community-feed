package org.fastcampus.community_feed.auth;

import org.fastcampus.community_feed.auth.domain.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailTest {

  @ParameterizedTest
  @NullAndEmptySource
  void givenEmailIsEmpty_whenCreate_thenTrhowError(String email) {
    Assertions.assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));

  }

  @ParameterizedTest
  @ValueSource(strings = {"valid/@ab", "naver.com", "나랏말싸미@듕귁에달아.com"})
  void givenInvalidEmail_whenCreate_thenThrowError(String email) {
    Assertions.assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
  }

  @ParameterizedTest
  @ValueSource(strings = {"vail@ab", "a@naver.com", "abc@gmail.com", "test@test.com"})
  void givenEmailValidWhenCreateThenReturnEmail(String email) {

    Email emailValue = Email.createEmail(email);
    Assertions.assertEquals(email, emailValue.getEmailText());

  }



}
