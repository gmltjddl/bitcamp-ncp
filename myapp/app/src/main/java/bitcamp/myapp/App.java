package bitcamp.myapp;public class App {

  public static void main(String[] args) {

    while(true) {
      System.out.println("1. 회원관리");
      System.out.println("9. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");

      if(menuNo == 1) {
        while(true) {
          System.out.println("[회원관리]");
          System.out.println("1. 등록");
          System.out.println("2. 목록");
          System.out.println("3. 조회");
          System.out.println("4. 변경");
          System.out.println("5. 삭제");
          System.out.println("0. 이전");


          int menuNo2 = Prompt.inputInt("메뉴>");
          if(menuNo2 == 1) {
            MemberHandler.inputMembers();
          }else if(menuNo2 == 2) {
            MemberHandler.printMembers();
          }else if(menuNo2 == 0) {
            break;
          }else {
            System.out.println("잘못된 번호입니다.");
          }
        }
      }else if (menuNo ==9) {
        break;
      }else {
        System.out.println("잘못된 번호입니다.");
      }
    }
    System.out.println("안녕히 가세요");
    Prompt.close();
  }
}









