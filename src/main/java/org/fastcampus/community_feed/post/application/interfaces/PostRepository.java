package org.fastcampus.community_feed.post.application.interfaces;

import java.util.Optional;
import org.fastcampus.community_feed.post.domain.Post;

public interface PostRepository {

    Post findById(Long id);
    Post save(Post post);
}
