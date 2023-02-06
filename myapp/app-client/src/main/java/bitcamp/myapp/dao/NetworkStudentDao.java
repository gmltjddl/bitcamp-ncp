package bitcamp.myapp.dao;

import com.google.gson.Gson;
import bitcamp.myapp.vo.Student;

public class NetworkStudentDao implements StudentDao {

  DaoStub daoStub ;

  public NetworkStudentDao(DaoStub daoStub) {
    this.daoStub = daoStub;
  }

  @Override
  public void insert(Student s) {
    daoStub.fetch("student", "insert", s);
  }

  @Override
  public Student[] findAll() {
    return new Gson().fromJson(daoStub.fetch("student", "findAll"), Student[].class);
  }

  @Override
  public Student findByNo(int no) {
    return new Gson().fromJson(daoStub.fetch("student", "findByNo",no), Student.class);
  }

  @Override
  public void update(Student s) {
    daoStub.fetch("student", "update", s);
  }

  @Override
  public boolean delete(Student s) {
    daoStub.fetch("student", "delete", s);
    return true;
  }
}























