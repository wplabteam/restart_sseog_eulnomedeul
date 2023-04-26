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

    public Map send(String receiver, String message) {

        Map result = new HashMap<String, Object>();

        try {
            String urlString = "http://munjanara.co.kr/send.sys?userid=" + userid +
                    "&passwd=" + passwd +
                    "&sender=" + sender +
                    "&receiver=" + receiver +
                    "&message=";

            byte[] byteArray = message.getBytes(Charset.forName("euc-kr"));
            StringBuilder hexBuilder = new StringBuilder();

            for (byte b : byteArray) {
                // 16진수 format 변경(유니코드 포맷형 맞추기 위해 '%'붙힘)
                hexBuilder.append(String.format("%%%02X", b));
            }

            urlString += hexBuilder.toString();

            URL url = new URL(urlString);

            // HTTP 연결 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 응답코드 확인
            int responseCode = connection.getResponseCode();

            // 응답 데이터 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            // 응답 출력
            System.out.println("Response: " + response.toString());
            System.out.println("message: " + message);

            // 연결 종료
            connection.disconnect();

            result.put("msg", response.toString());
            result.put("code", responseCode);


        } catch (Exception e) {
            log.error("sms error [e]" + e);
        }
        return result;
    }


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

