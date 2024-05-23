package com.kkini.core.global.database;

import java.io.*;

public class MemberData {

    public static void main(String[] args) {

        String tableName = "member";
        String columns = "create_date_time, modify_date_time, auth_provider, email, image, level, name, nickname, oauth2id, refresh_token, role, stars";
        String create = "now(), ";
        String modify = "now(), ";
        String auth_provider = "'NAVER', ";
        String email = "ssafy";
        String domain = "@ssafy.com";
        String image = "hello";
        String level = "1, ";
        String nickname = "테스트";
        String oauth2id = "test";
        String refresh_token = "token";
        String role = "'ROLE_USER', ";
        String name = "김싸피";


        try{
            File file = new File("C:\\data\\member.sql");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 10000; i++) {
                sb.append("INSERT INTO ").append(tableName).append("(").append(columns).append(") VALUES (")
                        .append(create)
                        .append(modify)
                        .append(auth_provider)
                        .append("'").append(email + Integer.toString(i)).append(domain+"'").append(", ")
                        .append("'").append(image + Integer.toString(i)).append("', ")
                        .append(level)
                        .append("'").append(name + Integer.toString(i)).append("', ")
                        .append("'").append(nickname + Integer.toString(i)).append("', ")
                        .append("'").append(oauth2id + Integer.toString(i)).append("', ")
                        .append("'").append(refresh_token + Integer.toString(i)).append("', ")
                        .append(role)
                        .append(0)
                        .append(");");
                sb.append("\n");
            }
            bufferedWriter.write(sb.toString());
            bufferedWriter.close();
            System.out.println("INSERT문이 파일에 저장되어었습니다.");
        }catch (IOException e){
            System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }
}
