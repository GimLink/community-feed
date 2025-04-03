package org.fastcampus.community_feed.acceptance.steps;

import io.restassured.RestAssured;
import java.util.List;
import org.fastcampus.community_feed.post.application.dto.CreatePostRequestDto;
import org.fastcampus.community_feed.post.ui.dto.GetPostContentResponseDto;
import org.springframework.http.MediaType;

public class FeedAcceptanceSteps {

  public static Long requestCreatePost(CreatePostRequestDto dto) {
    return RestAssured
        .given().log().all()
        .body(dto)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("/post")
        .then().log().all()
        .extract()
        .jsonPath()
        .getObject("value", Long.class);
  }

  public static List<GetPostContentResponseDto> requestFeed(String token) {
    return RestAssured
        .given().log().all()
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .header("Authorization", "Bearer " + token)
        .get("/feed")
        .then().log().all()
        .extract()
        .jsonPath()
        .getList("value", GetPostContentResponseDto.class);
  }

  public static Integer requestFeedCode(String token) {
    return RestAssured
        .given().log().all()
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .header("Authorization", "Bearer " + token)
        .get("/feed")
        .then().log().all()
        .extract()
        .jsonPath()
        .get("code");
  }

}
