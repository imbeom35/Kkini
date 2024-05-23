package com.kkini.core.global.database;

import com.kkini.core.domain.recipe.entity.Recipe;

import java.io.*;

public class RecipeData {

    public static void main(String[] args) {

        String tableName = "recipe";
        String columns = "create_date_time, modify_date_time, deleted, difficulty, image, ingredient, name, time, category_id, member_id";
        String create = "now(), ";
        String modify = "now(), ";
        String deleted = "0";
        String ingredient = "'라면 사리 1개, 마늘 1개'";
        String difficulty = "4";
        String image = "'https://t3.ftcdn.net/jpg/02/52/38/80/240_F_252388016_KjPnB9vglSCuUJAumCDNbmMzGdzPAucK.jpg'";
        String name = "'김치찌개'";
        String time = "30";
        String categoryId = "1";
        String memberId = "1";


        try{
            File file = new File("C:\\data\\recipe.sql");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 10000; i++) {
                sb.append("INSERT INTO ").append(tableName).append("(").append(columns).append(") VALUES (")
                        .append(create)
                        .append(modify)
                        .append(deleted).append(", ")
                        .append(difficulty).append(", ")
                        .append(image).append(", ")
                        .append(ingredient).append(", ")
                        .append(name).append(", ")
                        .append(time).append(", ")
                        .append(categoryId).append(", ")
                        .append(memberId)
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
