package calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {
  public static void main(String[] args) throws Exception {
    BufferedReader in = null;
    BufferedWriter out = null;
    Socket socket = null;
    Scanner scanner = new Scanner(System.in);
    try {
      socket = new Socket("localhost", 8888);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
      System.out.println("ㅣ      계산기      ㅣ");
      System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
      System.out.println("ㅣ                 0ㅣ");
      System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
      System.out.println("ㅣ % ㅣ CE ㅣ C ㅣ Dㅣ");
      System.out.println(" --------------------");
      System.out.println("ㅣ 7 ㅣ 8 ㅣ 9 ㅣ x ㅣ");
      System.out.println(" --------------------");
      System.out.println("ㅣ 4 ㅣ 5 ㅣ 6 ㅣ - ㅣ");
      System.out.println(" -------------------");
      System.out.println("ㅣ 1 ㅣ 2 ㅣ 3 ㅣ + ㅣ");
      System.out.println(" -------------------");
      System.out.println("ㅣ / ㅣ 0 ㅣ . ㅣ = ㅣ");
      System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
      while (true) {


        System.out.printf("ex)1 + 2 공백입력 필수! \n숫자 입력>>");
        String outputMessage = scanner.nextLine();
        if (outputMessage.equalsIgnoreCase("quit")) {
          out.write(outputMessage + "\n");
          out.flush();
          System.out.println("서버와 연결 종료!");
          break;

        }
        out.write(outputMessage + "\n");
        out.flush();
        String inputMessage = in.readLine();
        System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("ㅣ      계산기      ㅣ");
        System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("ㅣ               "+inputMessage+"ㅣ");
        System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("ㅣ % ㅣ CE ㅣ C ㅣ Dㅣ");
        System.out.println(" --------------------");
        System.out.println("ㅣ 7 ㅣ 8 ㅣ 9 ㅣ x ㅣ");
        System.out.println(" --------------------");
        System.out.println("ㅣ 4 ㅣ 5 ㅣ 6 ㅣ - ㅣ");
        System.out.println(" -------------------");
        System.out.println("ㅣ 1 ㅣ 2 ㅣ 3 ㅣ + ㅣ");
        System.out.println(" -------------------");
        System.out.println("ㅣ / ㅣ 0 ㅣ . ㅣ = ㅣ");
        System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        scanner.close();
        if (socket != null)
          socket.close();
      } catch (IOException e) {
        System.out.println("서버와 채팅 중 오류 발생!");
      }
    }
  }
}