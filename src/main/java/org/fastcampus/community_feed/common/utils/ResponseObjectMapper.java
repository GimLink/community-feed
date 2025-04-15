package org.fastcampus.community_feed.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fastcampus.community_feed.common.ui.Response;

public class ResponseObjectMapper {

  private ResponseObjectMapper() {}

  private static final ObjectMapper objMapper = new ObjectMapper();

  public static Response toResponseObject(String response) {
    try {
      return objMapper.readValue(response, Response.class);
    } catch (Exception e) {
      return null;
    }
  }

  public static String toStringResponse(Response<?> response) {
    try {
      return objMapper.writeValueAsString(response);
    } catch (Exception e) {
      return null;
    }
  }

}
