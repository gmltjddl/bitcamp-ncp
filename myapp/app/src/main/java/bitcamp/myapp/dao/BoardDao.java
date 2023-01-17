package bitcamp.myapp.dao;


import java.sql.Date;
import bitcamp.myapp.vo.Board;

public class BoardDao extends ObjectDao{

  //가장 최근 게시글의 글 번호를 저장하는필드
  //가장 최근 게시글이 삭제 되더라도 그 값은 그대로 유지할 것이다.
  int lastNo;
  @Override
  public Board findByNo(int no) {
    Board b = new Board();
    b.setNo(no);
    //    int index = this.indexOf(b);
    //
    //    if (index <0) {
    //      return null;
    //    }else {
    //      return (Board) this.get(index);
    //    }
    return (Board) this.get(this.indexOf(b));
  }


  @Override  //컴파일러에게 오버라이딩을 제대로 했는지 검사해 달라고 표시
  protected int indexOf(Object obj) {
    for (int i = 0; i < this.size(); i++) {
      if (((Board)this.objects[i]).getNo() == ((Board)obj).getNo()) {
        return i;
      }
    }
    return -1;
  }
  //슈퍼 클래스의 insert()는 객체를 등록할때 번호를 자동증가시키는 기능이 없다.
  //그러나 BoardDao는 그런기능이 필요하다
  // 수퍼클래스의 메서드를 서브 클래스의 역할이나 목적에 맞게 재정의한다
  // -> 이걸 오버라이딩이라 부른다.
  @Override
  public void insert(Object object) {
    //객체를 배열에 담기전에 그객체의 번호를 설정한다
    ((Board) object).setNo(++lastNo);
    //인스터스를 생성할 때의 날짜와 시각을 설정한다
    ((Board) object).setCreatedDate(new Date(System.currentTimeMillis()).toString());
    //그런후에 수퍼 클래스에서 상속받은 insert()를 사용하여 객체를 배열에 보관한다.
    super.insert(object);
  }

}







