package bitcamp.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import bitcamp.myapp.service.StudentService;
import bitcamp.myapp.vo.Student;
import bitcamp.util.Controller;
import bitcamp.util.RequsetMapping;
import bitcamp.util.RequsetParam;
@Controller
public class StudentController  {
  private StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @RequsetMapping("/student/form")
  public String form() {
    return "/student/form.jsp";
  }

  @RequsetMapping("/student/insert")
  public String insert(
      @RequsetParam("name") String name,
      @RequsetParam("email") String email,
      @RequsetParam("password") String password,
      @RequsetParam("tel") String tel,
      @RequsetParam("postNo") String postNo,
      @RequsetParam("basicAddress") String basicAddress,
      @RequsetParam("detailAddress") String detailAddress,
      @RequsetParam("working") boolean working,
      @RequsetParam("gender") char gender,
      @RequsetParam("level") byte level,
      HttpServletRequest request
      ) {

    Student student = new Student();
    student.setName(name);
    student.setEmail(email);
    student.setPassword(password);
    student.setTel(tel);
    student.setPostNo(postNo);
    student.setBasicAddress(basicAddress);
    student.setDetailAddress(detailAddress);
    student.setWorking(working);
    student.setGender(gender);
    student.setLevel(level);

    try {
      studentService.add(student);
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "other");
    }
    return "/student/insert.jsp";
  }

  @RequsetMapping("/student/list")
  public String list(
      @RequsetParam("keyword") String keyword,
      HttpServletRequest request) {
    request.setAttribute("students", studentService.list(keyword));
    return "/student/list.jsp";
  }

  @RequsetMapping("/student/view")
  public String execute(
      @RequsetParam("no") int no,
      HttpServletRequest request) {

    request.setAttribute("student",
        studentService.get(no));
    return"/student/view.jsp";
  }

  @RequsetMapping("/student/update")
  public String update(
      @RequsetParam("no") int no,
      @RequsetParam("name") String name,
      @RequsetParam("email") String email,
      @RequsetParam("password") String password,
      @RequsetParam("tel") String tel,
      @RequsetParam("postNo") String postNo,
      @RequsetParam("basicAddress") String basicAddress,
      @RequsetParam("detailAddress") String detailAddress,
      @RequsetParam("working") boolean working,
      @RequsetParam("gender") char gender,
      @RequsetParam("level") byte level,
      HttpServletRequest request) {
    Student student = new Student();
    student.setNo(no);
    student.setName(name);
    student.setEmail(email);
    student.setPassword(password);
    student.setTel(tel);
    student.setPostNo(postNo);
    student.setBasicAddress(basicAddress);
    student.setDetailAddress(detailAddress);
    student.setWorking(working);
    student.setGender(gender);
    student.setLevel(level);

    try {
      studentService.update(student);
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "other");
    }
    return "/student/update.jsp";
  }

  @RequsetMapping("/student/delete")
  public String delete(
      @RequsetParam("no") int no,
      HttpServletRequest request) {
    try {
      studentService.delete(no);
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "other");
    }
    return "/student/delete.jsp";
  }

}
