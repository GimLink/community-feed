package org.fastcampus.community_feed.post.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.community_feed.post.domain.content.Content;
import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.fastcampus.community_feed.user.domain.User;

@Builder
@AllArgsConstructor
@Getter
public class Post {
  private final Long id;
  private final User author;
  private final Content content;
  private PostPublicationState state;
  private final PositiveIntegerCounter likeCount;

  public Post(Long id, User author, Content content, PostPublicationState state) {
    if (author == null) {
      throw new IllegalArgumentException("author should not be null");
    }
    if (content == null) {
      throw new IllegalArgumentException("content should not be null or empty");
    }

    this.id = id;
    this.author = author;
    this.content = content;
    this.state = state;
    this.likeCount = new PositiveIntegerCounter();
  }

  public Post(Long id, User author, Content content) {
    this(id, author, content, PostPublicationState.PUBLIC, new PositiveIntegerCounter());
  }

  public static Post createPost(Long id, User author, String content, PostPublicationState state) {
    return new Post(id, author, new PostContent(content), state);
  }

  public static Post createDefaultStatePost(Long id, User author, String content) {
    return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
  }

  public void updateContent(User user, String content) {
    if (!author.equals(user)) {
      throw new IllegalArgumentException("only author can update content");
    }
    this.content.updateContent(content);
  }

  public void like(User user) {
    if (author.equals(user)) {
      throw new IllegalArgumentException("author cannot like own post");
    }
    likeCount.increase();
  }

  public void unlike() {
    likeCount.decrease();
  }

  public void updateState(PostPublicationState state) {
    this.state = state;
  }

  public String getContent() {
    return content.getContentText();
  }

  public int getLikeCount() {
    return likeCount.getCount();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Post post = (Post) o;
    return Objects.equals(id, post.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
