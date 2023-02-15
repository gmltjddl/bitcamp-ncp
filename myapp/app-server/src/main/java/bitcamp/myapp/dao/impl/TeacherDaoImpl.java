package bitcamp.myapp.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Teacher;

public class TeacherDaoImpl implements TeacherDao {


  SqlSessionFactory sqlSessionFactory;

  public TeacherDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Teacher s) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("TeacherMapper.insert", s);
    }
  }

  @Override
  public List<Teacher> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("TeacherMapper.findAll");
    }
  }
  @Override
  public List<Teacher> findByKeyword(String keyword) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("TeacherMapper.findByKeyword");
    }
  }
  @Override
  public Teacher findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("TeacherMapper.findByNo", no);
    }
  }

  @Override
  public int update(Teacher t) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("TeacherMapper.update", t);
    }
  }

  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("TeacherMapper.delete", no);
    }

  }
}























