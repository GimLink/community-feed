package org.fastcampus.community_feed.common.idempotency;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.fastcampus.community_feed.common.ui.Response;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class IdempotencyAspect {

  private final IdempotencyRepository idempotencyRepository;
  private final HttpServletRequest request;

  @Around("@annotation(Idempotent)") //특정 로직 실행전 실행후 작업에 관여
  public Object checkIdemPotency(ProceedingJoinPoint joinPoint) throws Throwable {
    String idempotencyKey = request.getHeader("Idempotency-Key");
    if (idempotencyKey == null) {
      return joinPoint.proceed();
    }

    Idempotency idempotency = idempotencyRepository.getByKey(idempotencyKey);

    if (idempotency != null) {
      return idempotency.getResponse();
    }

    Object result = joinPoint.proceed();

    Idempotency newIdempotency = new Idempotency(idempotencyKey, (Response<?>) result);
    idempotencyRepository.save(newIdempotency);

    return result;

  }

}
