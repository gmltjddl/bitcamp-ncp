package bitcamp.myapp.dao;

import java.sql.Date;
import bitcamp.myapp.vo.Student;
import bitcamp.util.Iterator;
import bitcamp.util.List;

public class StudentDao  {
  List list;
  public StudentDao (List list) {
    this.list=list;
  }

  int lastNo;

  public void insert(Student student) {
    student.setNo(++lastNo);
    student.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    list.add(student);
  }


  public Student findByNo(int no) {
    Student s = new Student();
    s.setNo(no);
    int index = list.indexOf(s);
    if(index == -1) {
      return null;
    }
    return (Student) list.get(index);
  }
  public void update(Student s) {
    int index = list.indexOf(s);
    list.set(index,s); // 인덱스자리에 b객체를 넣는다

  }
  public boolean delete(Student s) {
    return list.remove(s);
  }
  public Student[] findAll() {
    Student[] students = new Student[list.size()];
    Iterator i = list.iterator();
    int index = 0;
    while(i.hasNext()) {
      students[index++] = (Student) i.next();

    }
    return students;
  }

}







