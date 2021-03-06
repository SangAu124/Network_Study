package kr.hs.dgsw.server.sangau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChattingMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(5000);  	// 서버 시작(클라이언트 접속 대기)

            System.out.println("서버가 시작되었습니다.");

            while(true) {
                Socket sc = ss.accept();
                System.out.println(sc.getInetAddress() +": 접속하였습니다.");

                try {
                    // 데이터 주고 받는 객체 생성
                    ClientAccept ca = new ClientAccept(sc);

                    ClientThread ct = new ClientThread(sc.getInetAddress().toString(), ca);
                    ct.start();
                } catch(Exception e) {
                    System.out.println("연결 종료");
                }
            }
        } catch(Exception e) {
        } finally {
            try {
                ss.close();
            } catch(IOException e) {}
        }
    }
}
