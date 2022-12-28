class Exam01 {
  public static void main(String[] args) {
    System.out.println("hello");
    
    class Student {
      String name;
      int age;
      boolean working;
      
    }

    Student obj = new Student();

    obj.name = "홍길동";
    obj.age = 20;
    obj.working = true;

    System.out.println(obj.name);
    System.out.println(obj.age);
    System.out.println(obj.working);   
    
    java.util.HashMap obj2 = new java.util.HashMap();
    obj2.put("name","홍길동");
    obj2.put("age",20);
    obj2.put("working", true);

    System.out.println(obj2.get("name"));
    System.out.println(obj2.get("age"));
    System.out.println(obj2.get("working"));
}
}

