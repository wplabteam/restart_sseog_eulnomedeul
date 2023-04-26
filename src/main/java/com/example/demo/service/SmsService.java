package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class SmsService {

    private final String userid = "kok353600";
    private final String passwd = "a1234567";
    private final String sender = "01096030024";

    /**
     * method         : send
     * author         : 오동준
     * date           : 2023/04/26
     * description    : 문자 발송
     */
    public Map send(String receiver, String message) {

        Map result = new HashMap<String, Object>();

        try {
            String urlString = "http://munjanara.co.kr/send.sys?userid=" + userid +
                    "&passwd=" + passwd +
                    "&sender=" + sender +
                    "&receiver=" + receiver +
                    "&message=";
            // Charset : 문자 인코딩을 나타내는 자바 클래스
            // 즉 문자열을 byte[]로 변환할 때 사용하는 인코딩 방식을 지정 할수 있다 .
            // euc-kr : 한글 인코딩 방식
            // UTF-8 : 유니코드 인코딩 방식
            // ISO-8859-1 : 영어 인코딩 방식
            // getBytes() : 문자열을 byte[]로 변환
            // 사람언어로 들어온 메시지를 컴퓨터가 이해할 수 있게 변환 (인코딩)
            byte[] byteArray = message.getBytes(Charset.forName("euc-kr"));

            /**
             * StringBuilder : 문자열을 조작하기 위한 클래스  문자열에 대해 다양한 연산을 수행할 수 있음
             * 예) 문자열 추가, 문자열 삽입, 문자열 삭제, 문자열 변경 등
             * */
            StringBuilder hexBuilder = new StringBuilder();

            for (byte b : byteArray) {
                // 16진수 format 변경(유니코드 포맷형 맞추기 위해 '%'붙힘)
                // 2는 16진수를 2자리로 만들어줌
                // 0은 빈자리는 0으로 채움
                hexBuilder.append(String.format("%%%02X", b));
            }
            //urlString에 message=뒤부터 문자열을 붙여줌
            urlString += hexBuilder.toString();

            // new URL : 문자열 형식의 URL을 파싱하여 URL 객체를 생성하는 역할
            URL url = new URL(urlString);

            // HTTP 연결 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 응답코드 확인
            int responseCode = connection.getResponseCode();
//            System.out.println("Response Code : " + responseCode);



            // 응답 데이터 읽기
            // BUfferedReader : 문자 입력 스트림으로부터 문자를 읽어 들이기 위한 클래스 (문자 입력 스트림으로부터 문자를 읽어 들이기 위한 클래스)

            // InputStreamReader : 바이트 입력 스트림에서 문자 입력 스트림으로 변환하는 클래스
            //  바이트 스트림은 1바이트 단위로 데이터를 처리하지만 문자 스트림은 2바이트 단위로 데이터를 처리합니다

            // connection.getInputStream()은 위코드 HttpURLConnection 객체에서 가져온 바이트 스트림으로
            // InputStreamReader를 통해 문자 스트림으로 변환합니다
            // 이렇게하면 BufferedReader를 통해 한 줄씩 읽을 수 있습니다

            // readLine()은 BufferedReader 클래스의 메소드로, 한 줄씩 문자열을 읽어들입니다.
            // 읽어들일 문자열이 없으면 null을 반환 while문 종료
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            // 응답 출력
//            System.out.println("Response: " + response.toString());
//            System.out.println("message: " + message);

            // 연결 종료
            connection.disconnect();

            result.put("msg", response.toString());
            result.put("code", responseCode);


        } catch (Exception e) {
            log.error("sms error [e]" + e);
        }
        return result;
    }

    /**
     * method         : getCertRandomNo
     * author         : 오동준
     * date           : 2023/04/26
     * description    : 인증번호 생성
     */

    public static String getCertRandomNo(int len, int dupCd) {

        Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수

        for (int i = 0; i < len; i++) {

            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));

            if (dupCd == 1) {
                //중복 허용시 numStr에 append
                numStr += ran;
            } else if (dupCd == 2) {
                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
                if (!numStr.contains(ran)) {
                    //중복된 값이 없으면 numStr에 append
                    numStr += ran;
                } else {
                    //생성된 난수가 중복되면 루틴을 다시 실행한다
                    i -= 1;
                }
            }
        }
        return numStr;
    }
}

