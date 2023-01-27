package bitcamp.myapp.dao;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import bitcamp.myapp.vo.Teacher;

public class TeacherDao {
  List<Teacher> list;
  public TeacherDao (List<Teacher> list) {
    this.list=list;
  }
  int lastNo;

  public void insert(Teacher teacher) {
    teacher.setNo(++lastNo);
    teacher.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    list.add(teacher);
  }


  public Teacher findByNo(int no) {
    Teacher t = new Teacher();
    t.setNo(no);

    int index = list.indexOf(t);
    if(index == -1) {
      return null;
    }
    return list.get(index);
  }
  protected int indexOf(Object obj) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == ((Teacher) obj).getNo()) {
        return i;
      }
    }
    return -1;
  }
  public Teacher[] findAll() {
    Teacher[] teachers = new Teacher[list.size()];
    Iterator<Teacher> i = list.iterator();
    int index = 0;
    while(i.hasNext()) {
      teachers[index++] = i.next();

    }
    return teachers;
  }

  public void update(Teacher t) {
    int index = list.indexOf(t);
    list.set(index,t); // 인덱스자리에 b객체를 넣는다
  }
  public boolean delete(Teacher t) {
    return list.remove(t);
  }
}





