package org.fastcampus.community_feed.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.admin.ui.dto.GetTableListResponse;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableResponseDto;
import org.fastcampus.community_feed.admin.ui.query.AdminTableQueryRepository;
import org.fastcampus.community_feed.auth.repository.entity.QUserAuthEntity;
import org.fastcampus.community_feed.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

  private final JPAQueryFactory queryFactory;
  private final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;
  private final QUserEntity userEntity = QUserEntity.userEntity;

  @Override
  public GetTableListResponse<GetUserTableResponseDto> getUserTableData(
      GetUserTableRequestDto dto) {

    int total = queryFactory.select(userEntity.id)
        .from(userEntity)
        .where(likeName(dto.getName()))
        .fetch()
        .size();

    List<GetUserTableResponseDto> result = queryFactory
        .select(
            Projections.fields(GetUserTableResponseDto.class,
                userEntity.id.as("id"),
                userAuthEntity.email.as("email"),
                userEntity.name.as("name"),
                userAuthEntity.role.as("role"),
                userEntity.regDt.as("createdAt"),
                userEntity.updDt.as("updatedAt"),
                userAuthEntity.lastLoginDt.as("lastLoginAt"))
        )
        .from(userEntity)
        .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
        .where(likeName(dto.getName()))
        .orderBy(userEntity.id.desc())
        .offset(dto.getOffset())
        .limit(dto.getLimit())
        .fetch();
    return new GetTableListResponse<>(total, result);
  }

  private BooleanExpression likeName(String name) {
    if (name == null || name.isBlank()) {
      return null;
    }

    return userEntity.name.like(name + "%");
  }


}
