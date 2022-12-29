// 바이트코드로 컴파일 시 import지우고 아래 코드로 변환되어 인식됨
// class A {
//   p1.B obj;
//   p2.C obj2;
//   p2.px.C obj3;
//   p2.px.aaa.bbb.ccc.ddd.D obj4;
// }

import p1.B;
// import p2.C;
import p2.px.C;
import p2.px.aaa.bbb.ccc.ddd.D;

class A {
  B obj;
  p2.C obj2;
  C obj3;
  D obj4;

  public static void main(String[] args) {
    System.out.println("Hello!-A");
    System.out.println("안녕하세요!");
  }
}