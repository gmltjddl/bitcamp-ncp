package bitcamp.myapp.dao;

import java.util.Arrays;

public abstract class ObjectDao {
  private static final int SIZE = 100;

  private int count;
  protected Object[] objects = new Object[SIZE];

  public void insert(Object object) {
    this.objects[this.count++] = object;
  }

  public Object[] findAll() {
    // 배열의 값 복제
    //    Object[] arr = new Object[this.count];
    //    for (int i = 0; i < this.count; i++) {
    //      arr[i] = this.objects[i];
    //    }
    //    return arr;

    // 위와 같다!
    return Arrays.copyOf(objects, count);
  }

  public Object findByNo(int no) {
    //    for (int i = 0; i < this.count; i++) {
    //      if (this.objects[i].getNo() == no) {
    //        return this.objects[i];
    //      }
    //    }
    return null;
  }

  public void update(Object object) {
    this.objects[this.indexOf(object)] = object;
  }

  public void delete(Object object) {
    for (int i = this.indexOf(object) + 1; i < this.count; i++) {
      this.objects[i - 1] = this.objects[i];
    }
    this.objects[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  //객체의 위치를 찾는 것은
  //객체의 타입에 따라 다를수 있기 떄문에
  //이 클래스에서 정의하지말고
  //
  protected abstract int indexOf(Object b) ;

  public int size() {
    return this.count;
  }
  //개발하는 중에 서브 클래스들이 공통으로 필요로 하는 기능을 발견하게 된다.
  //그런 상황이면 이렇게 수퍼 클래스에 해당 메소드를 정의하면 된다.
  public Object get(int i) {
    if (i<0 || i>= this.count) {
      return null;
    }
    return objects[i];
  }

}







