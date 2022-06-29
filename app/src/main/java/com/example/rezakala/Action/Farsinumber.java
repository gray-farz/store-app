package com.example.rezakala.Action;

public class Farsinumber {

    static  String[] farsinumber =new String[]{"۰","۱","۲","۳","۴","۵","۶","۷","۸","۹"};

   public static String farsinumber(String text)
    {
        if(text.length()==0){
            return "";
        }
        String out="";
        int length=text.length();
        for (int i = 0; i <length ; i++) {
            char c=text.charAt(i);
            if('0' <=c && c<='9'){
                int number =Integer.parseInt(String.valueOf(c));
                out=out+farsinumber[number];
            }
            else if(c==',' || c=='و'){
                out=out+',';
            }
            else
            {
                out=out+c;
            }
        }
        return  out;
    }

}
