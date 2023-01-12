package bitcamp.bootapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// 이클래스는 설정에 관련된 일을 하는 클래스임을 선언한다.
// 스프링 컴포넌트로 표시하는 것이다.
// spring ioc 컨테이너는 클래스의 인스턴스를 자동 생성한다.

//@EnableWebMvc
// spring 프레임워크에서 webMVC 기능을 사용할 수 있도록 관련 객체를 준비시키라고 선언
// 웹 애플리케이션을 제작할 때 사용할 도구가 완전하게 구비되는 것이다.
// springboot의 경우 이 애노테이션을 생략해도 된다

public class WebConfig implements WebMvcConfigurer {
  //이클래스는 webmvcconfigurer 규칙에 따라 메서드를 만들었음을 선언한다
  //단, 모든 메서드를 정의할 필요는 없다.
  //이 프로젝트 요구 조건에 맞는 부분만 설정하면 된다.(customizing; 고객화)
  //spring webMVC 프레임워크는 이 클래스의 정의된 메서드를 호출하여 설정을 완성한다.

  public WebConfig() {
    System.out.println("Web Config생성됨");
  }
  //Cross-Origin 관련 설정을 수행하는 메서드를 정의한다
  //스프링부트가 시작되면 이 메서드를 호출하여 CrossOrigin을 설정할것이다.
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
    .allowedOrigins("http://localhost:5500","http://127.0.0.1:5500")
    .allowedMethods("*");

  }
}
