package bitcamp.myapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bitcamp.myapp.service.StudentService;
import bitcamp.myapp.service.TeacherService;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Controller;
import bitcamp.util.RequsetMapping;
import bitcamp.util.RequsetParam;
@Controller
public class AuthController  {

  private StudentService studentService;
  private TeacherService teacherService;

  public AuthController(StudentService studentService, TeacherService teacherService) {
    this.studentService = studentService;
    this.teacherService = teacherService;
  }

  @RequsetMapping("/auth/form")
  public String form() {
    return "/auth/form.jsp";
  }
  @RequsetMapping("/auth/login")
  public String login(
      @RequsetParam("usertype") String usertype,
      @RequsetParam("email") String email,
      @RequsetParam("password") String password,
      @RequsetParam("saveEmail") String saveEmail,
      HttpServletResponse response,
      HttpServletRequest request,
      HttpSession session) {

    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 30); // 30일 동안 유지
      response.addCookie(cookie);

    } else {
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member member = null;
    switch (usertype) {
      case "student":
        member = studentService.get(email, password);
        break;
      case "teacher":
        member = teacherService.get(email, password);
        break;
    }

    if (member != null) {
      session.setAttribute("loginUser", member);
      return "redirect:../";
    } else {
      request.setAttribute("error", "loginfail");
      return "/auth/form.jsp";
    }
  }

  @RequsetMapping("/auth/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:../";
  }

  @RequsetMapping("/auth/fail")
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    return "/auth/fail.jsp";
  }
}









