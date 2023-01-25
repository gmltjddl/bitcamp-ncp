package bitcamp.util;

//객체 목록을 다루는 기능을 규정한다.
//=>list인터페이스는 iteable 규칙을 상속 받기 때문에
// list를 구현하는 클래스는 iterable 규칙도 함께 구현해야 한다.
public interface List  extends Iterable{
  void add(Object value);
  Object[] toArray();
  Object get(int index);
  Object set(int index, Object value);
  boolean remove(Object value);
  int indexOf(Object value);
  int size();
}
