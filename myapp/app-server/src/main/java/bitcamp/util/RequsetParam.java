package bitcamp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//애노테이션 유지 정책 변경하기
// -> class : 컴파일 했을때 바이트 코드에 유지하기
// -> runtime : class + 실행중에 Reflection API를 사용해서 값 꺼내기
// -> source : 컴파일 했을 때 제거하기 즉 소스파일에서만 유지하기
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequsetParam {
  String value() default ""; // 필수속성


}
