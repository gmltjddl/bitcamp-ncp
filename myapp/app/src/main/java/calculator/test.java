package calculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

  public static void main(String[] args) {
    // 1) 패턴정의
    Pattern pattern = Pattern.compile("\\d+|\\D+|[\\+\\-\\*/]",Pattern.CASE_INSENSITIVE);
    //.개수마다 1개씩 짜름
    // * 쓰려면 \\* 해야함 \하나더해줘야 함
    // 2) 패턴에 따라 콘텐트를 검사할 도구 준비
    Matcher matcher = pattern.matcher("123+2*98-24/19");
    // 3) 패턴의 결과를 담을 컬렉션 준비
    ArrayList<String> items = new ArrayList<>();
    // 4)패턴 검사
    while(matcher.find()) {
      items.add(matcher.group());
    }
    System.out.println("-----------------------");
    for(String item:items)
      System.out.println(item);
  }
}
