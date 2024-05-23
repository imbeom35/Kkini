package com.kkini.core.global.database;

import java.io.*;

public class PostData {

    public static void main(String[] args) {

        String tableName = "post";
        String columns = "create_date_time, modify_date_time, contents, dislikes, likes, price, member_id, recipe_id";
        String create = "now(), ";
        String modify = "now(), ";
        String contents = "테스트 입니다";
        String dislikes = "0";
        String likes = "0";
        String price = "0";
        String memberId = "1";
        String recipeId = "1";


        try{
            File file = new File("C:\\data\\post.sql");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 1000000; i++) {
                sb.append("INSERT INTO ").append(tableName).append("(").append(columns).append(") VALUES (")
                        .append(create)
                        .append(modify)
                        .append("'").append(contents + Integer.toString(i)).append("', ")
                        .append(dislikes).append(", ")
                        .append(likes).append(", ")
                        .append(price).append(", ")
                        .append(memberId).append(", ")
                        .append(recipeId)
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
