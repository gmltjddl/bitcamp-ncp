package calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class CalcServer {
  public static void main(String[] args) throws Exception {
    BufferedReader in = null;
    BufferedWriter out = null;
    ServerSocket serversocket = null;
    Socket socket = null;
    try {
      serversocket = new ServerSocket(8888);
      socket = serversocket.accept();
      System.out.println("서버 연결 완료!");

      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

      while (true) {
        String inputMessage = in.readLine();
        if (inputMessage.equalsIgnoreCase("quit")) {
          System.out.println("클라이언트에서 연결을 종료하였음!");
          break;
        }
        // System.out.println(inputMessage);
        String res = calc(inputMessage);
        out.write(res + "\n");
        out.flush();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        if (socket != null)
          socket.close();
        if (serversocket != null)
          serversocket.close();
      } catch (IOException e) {
        System.out.println("클라이언트와 채팅 중 오류가 발생!");
      }
    }
  }


  public static String calc(String exp) {
    StringTokenizer st = new StringTokenizer(exp, " ");
    if (st.countTokens() != 3) {
      return "error";
    }
    String res = "";
    double op1 = Double.parseDouble(st.nextToken());

    String opcode = st.nextToken();
    double op2 = Double.parseDouble(st.nextToken());

    switch (opcode) {
      case "+":
        res = Double.toString(Math.round((op1 + op2)*10)/10.0);
        break;
      case "-":
        res = Double.toString(Math.round((op1 - op2)*10)/10.0);
        break;
      case "*":
        res = Double.toString(Math.round((op1 * op2)*10)/10.0);
        break;
      case "%":
        res = Double.toString(Math.round((op1 % op2)*1)/1);
        break;
      case "/":
        if(op2 == 0) {
          res = "error";
          break;
        }
        res = Double.toString(Math.round((op1 / op2)*10)/10.0);
        break;
      default:
        res = "error";
    }
    return res;
  }
}