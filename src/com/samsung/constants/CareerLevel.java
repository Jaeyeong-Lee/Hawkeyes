package com.samsung.constants;

public enum CareerLevel {
    CL1, CL2, CL3, CL4;

    public static String nullSaferToString(CareerLevel careerLevel){
        return (careerLevel!=null)?(careerLevel.toString()):null;
    }
}
