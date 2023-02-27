package bitcamp.myapp.listener;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.BoardFileDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.StudentService;
import bitcamp.myapp.service.TeacherService;
import bitcamp.myapp.service.impl.DefaultBoardService;
import bitcamp.myapp.service.impl.DefaultStudentService;
import bitcamp.myapp.service.impl.DefaultTeacherService;
import bitcamp.util.BitcampSqlSessionFactory;
import bitcamp.util.Controller;
import bitcamp.util.DaoGenerator;
import bitcamp.util.RequestHandlerMapping;
import bitcamp.util.RequsetMapping;
import bitcamp.util.TransactionManager;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  List<Class<?>> controllerClasses = new ArrayList<>();

  List<Object> servicePool = new ArrayList<>();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      InputStream mybatisConfigInputStream = Resources.getResourceAsStream(
          "bitcamp/myapp/config/mybatis-config.xml");
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      BitcampSqlSessionFactory sqlSessionFactory = new BitcampSqlSessionFactory(
          builder.build(mybatisConfigInputStream));

      TransactionManager txManager = new TransactionManager(sqlSessionFactory);

      BoardDao boardDao = new DaoGenerator(sqlSessionFactory).getObject(BoardDao.class);
      MemberDao memberDao = new DaoGenerator(sqlSessionFactory).getObject(MemberDao.class);
      StudentDao studentDao = new DaoGenerator(sqlSessionFactory).getObject(StudentDao.class);
      TeacherDao teacherDao = new DaoGenerator(sqlSessionFactory).getObject(TeacherDao.class);
      BoardFileDao boardFileDao = new DaoGenerator(sqlSessionFactory).getObject(BoardFileDao.class);

      BoardService boardService = new DefaultBoardService(txManager, boardDao, boardFileDao);
      StudentService studentService = new DefaultStudentService(txManager, memberDao, studentDao);
      TeacherService teacherService = new DefaultTeacherService(txManager, memberDao, teacherDao);

      servicePool.add(new DefaultBoardService(txManager, boardDao, boardFileDao));
      servicePool.add(new DefaultStudentService(txManager, memberDao, studentDao));
      servicePool.add(new DefaultTeacherService(txManager, memberDao, teacherDao));
      // 서블릿 컨텍스트 보관소를 알아낸다.
      ServletContext ctx = sce.getServletContext();

      //웹어플이 배치되어 있는 폴더 알아내기
      findPageController(new File(ctx.getRealPath("/WEB-INF/classes")),"");

      //페이지 컨트롤러의 인스턴스 생성하기
      createPageControllers(ctx);

    } catch (Exception e) {
      System.out.println("웹 애플리케이션 자원을 준비하는 중에 오류 발생!");
      e.printStackTrace();
    }
  }

  private void findPageController(File dir, String packageName) throws Exception {
    // 디렉토리에서 파일을 찾아서 이름찍는거
    File[] files = dir.listFiles (file-> file.isDirectory() || file.getName().endsWith(".class"));
    if(packageName .length() > 0 ) {
      packageName += ".";
    }
    for(File file : files) {
      String qName = packageName + file.getName(); // 패키지명 + 파일명 예) bitcamp.myapp.vo
      if(file.isDirectory()) {
        findPageController(file, qName);
        //디렉토리안에 디렉토리에서 파일을 찾아 이름을 찍음
      }else {
        Class<?> clazz = Class.forName(qName.replace(".class",""));
        if(clazz.isInterface()) { //인터페이스는 제외
          continue;
        }
        Controller anno = clazz.getAnnotation(Controller.class);
        if(anno != null) {
          controllerClasses.add(clazz);
        }
      }
    }
  }

  private void createPageControllers(ServletContext ctx) throws Exception {
    for(Class<?> c : controllerClasses) {
      Constructor<?> constructor =  c.getConstructors()[0];
      //생성자를 알아낸다
      Parameter[] params = constructor.getParameters();
      //생성자에 넘겨줘야할 파라미터 정보를 알아낸다
      Object[] arguments = prepareArguments(params);
      //파라미터에 해당하는 서비스 객체를 servicePool에서 찾는다.
      Object controller = constructor.newInstance(arguments);
      //찾은 파라미터를 가지고 생성자를 호출한다.

      //페이지 컨트롤러에서 RequestMapping 애노테이션이 붙은 메소드를 찾아
      //servletContext 보관소에 저장한다.
      Method[] methods =c.getDeclaredMethods(); // 슈퍼클래스 메소드 제외하고 현재 클래스 메소드만
      for (Method m : methods) {
        RequsetMapping anno = m.getAnnotation(RequsetMapping.class);
        if(anno ==null) continue;
        ctx.setAttribute(anno.value(), new RequestHandlerMapping(controller,m));
        System.out.println(c.getName() +"."+m.getName() +"() 요청 핸들러 등록" );

      }
    }
  }

  private Object[] prepareArguments(Parameter[] params) {
    Object[] arguments = new Object[params.length];
    for(int i = 0; i<params.length; i++) {
      for(Object obj : servicePool) {
        if(params[i].getType().isInstance(obj)) {
          arguments[i] =obj;
          break;
        }
      }
    }
    return arguments;
  }
}



