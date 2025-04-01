package org.fastcampus.community_feed.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.community_feed.auth.domain.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PasswordTest {

  @Test
  void givenPassword_whenMatchSamePassword_thenReturnTrue() {
    Password password = Password.createEncryptPassword("password");

    assertTrue(password.matchPassword("password"));

  }

  @Test
  void givenPassword_whenMatchDiffPassword_thenReturnFalse() {
    Password password = Password.createEncryptPassword("password1");

    assertFalse(password.matchPassword("password"));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void givenPasswordIsNull_thenThrowError(String password) {
    assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
  }

}
