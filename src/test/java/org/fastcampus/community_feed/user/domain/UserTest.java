package org.fastcampus.community_feed.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1 = new User(1L, userInfo);
    private User user2 = new User(2L, userInfo);


    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() {
        boolean value = user1.equals(user2);

        assertFalse(value);
    }

    @Test
    void givenTwoSameIdUser_whenEqual_thenReturnTrue() {
        User sameUser = new User(1L, userInfo);

        boolean value = user1.equals(sameUser);

        assertTrue(value);
    }

    @Test
    void givenTwoUser_whenHashCode_thenReturnFalse() {
        int hashCode1 = user1.hashCode();
        int haschCode2 = user2.hashCode();

        assertNotEquals(hashCode1, haschCode2);
    }

    @Test
    void givenTwoSameIdUser_whenHashCode_thenEqual() {
        User sameUser = new User(1L, userInfo);

        int hashcode1 = user1.hashCode();
        int sameHashCode = sameUser.hashCode();

        assertEquals(hashcode1, sameHashCode);
    }

    @Test
    void givenCreateSameIdUserWhenEqualSameIdThenReturnTrue() {
        // given
        UserInfo testInfo = new UserInfo("test1", "1");
        User oneUser = new User(1L, testInfo);

        // when, then
        assertEquals(oneUser, user1);
    }

    @Test
    void givenUser1WhenFollowUser2ThenUser1IncreaseFollowingCountUser2IncreaseFollowerCount() {
        // given, when
        user1.follow(user2);

        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenUser1FollowUser2WhenUser1UnfollowUser2ThenReturnZero() {
        // given
        user1.follow(user2);

        //when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void whenUser1UnfollowUser2ThenAllCountZero() {
        //when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }


    @Test
    void givenOneUserWhenFollowSameUserThenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> user1.follow(user1));
    }

    @Test
    void givenOneUserWhenUnfollowSameUserThenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> user1.unfollow(user1));
    }
}
