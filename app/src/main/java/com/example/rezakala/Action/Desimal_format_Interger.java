package com.example.rezakala.Action;

import java.text.DecimalFormat;

public class Desimal_format_Interger {

    public static String GetformatInteger(String str)
    {
        String check=null;
        DecimalFormat decimalFormat=new DecimalFormat("###,###");
         check= decimalFormat.format(Integer.valueOf(str));
        return check;
    }
}
