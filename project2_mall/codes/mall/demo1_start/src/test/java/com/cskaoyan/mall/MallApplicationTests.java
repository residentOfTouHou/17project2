package com.cskaoyan.mall;

import com.cskaoyan.mall.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class MallApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        String arrayString = "[\"coding\",\"running\"]";

        String[] array = parseString2Array(arrayString);
        for (String s : array) {
            System.out.println(s);
        }
    }

    private String[] parseString2Array(String arrayString) throws JsonProcessingException {
        /*String s = arrayString.replaceAll("\"", "").
                replaceAll("\[","").
                replaceAll("]","");
        String[] split = s.split(",");*/
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = objectMapper.readValue(arrayString, String[].class);
        return strings;
    }

    @Test
    public void mytest21(){
        String[] strings = {"coding", "rap"};
        String arrayString = parseArray2String(strings);
        System.out.println(arrayString);
    }

    private String parseArray2String(String[] strings) {
        /*StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (String string : strings) {
            stringBuffer.append("\"");
            stringBuffer.append(string).append("\"").append(",");

        }*/
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(strings);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Test
    public void mytest3(){
        User user = new User();
        user.setId(0);
        user.setUsername("songge");
        user.setPassword("zhenshuai");
        user.setAge(0);
        user.setGender("male");
        String[] strings = {"coding", "rap"};
        user.setHobby(strings);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(user);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void mytest4() throws JsonProcessingException {
        String userString = "{\"id\":0,\"username\":\"songge\",\"password\":\"zhenshuai\",\"age\":0,\"gender\":\"male\",\"hobby\":[\"coding\",\"rap\"]}";
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userString, User.class);

        System.out.println(user);
    }

}
