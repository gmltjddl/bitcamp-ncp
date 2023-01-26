package bitcamp.util;

public abstract class AbstractList implements List {

  protected int size;

  @Override
  public Object get(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("인덱스가 무효합니다.");
    }
    return null;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Iterator iterator() {

    // iterator() 메서드 안에서만 사용하는 클래스라면
    // 이 메서드 안에 두는 것이 유지보수에 좋다
    // 인스턴스를 한 개만 만들어 사용하고,클래스의 크기도 작다면,
    // 익명 클래스로 만드는 것이 코드를 간결하게 만든다.
    // anonymous class = 객체 생성코드 + 클래스 정의 + return 문

    // 로컬 클래스의 생성자를 호출할때
    // 바깥 클래스의 인스턴스 주소를 넘길 필요가 없다
    // 컴파일러가 대신 처리한다
    // 바깥 클래스의 인스턴스 주소르 넘길 수 있도록 생성자 호출을 변경한다

    // return new ListIterator(this);

    return new Iterator() {

      int cursor;

      // 바깥 클래스의 인스턴스를 사용하기 위해 생성자에서 그 주소를 받을 필요가 없다
      // 컴파일러가 바깥 클래스의 객체 주소를 보관할 필드를 자동으로 생성하고
      // 바깥 클래스의 객체 주소를 받을 수 있게 기존 생성자를 자동으로 변경한다
      // 생성자가 없다면 바깥 클래스의 객체 주소를 받는 생성자를 자동으로 추가한다
      // 따라서 다름과 같이 개발자가 직접 필드와 생성자를 추가할 필요가 없다
      // 편 리 하 다
      // 대신 바깥 클래스의 인스턴스를 사용하려면 다음과 같이 객체를 지정해야 한다
      // 바깥 클래스명.this.인스턴스멤버
      //List list;
      //      public ListIterator(List list) {
      //        this.list = list;
      //      }

      @Override
      public boolean hasNext() {
        return cursor >= 0 && cursor < AbstractList.this.size();
      }

      @Override
      public Object next() {
        return AbstractList.this.get(cursor++);
      }
    };

  }

}