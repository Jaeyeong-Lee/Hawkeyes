package com.samsung.constants;

public enum Certi {
    PRO, EX, ADV;

    public static String nullSaferToString(Certi certi){
        return (certi!=null)?(certi.toString()):null;
    }
}
