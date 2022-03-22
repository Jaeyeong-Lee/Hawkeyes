package com.samsung.option;

public class SearchOption {

    private String column; // CL, employeeNumber
    private String condition; // CL3, 21050301

    public SearchOption(String column, String condition) {
        this.column = column;
        this.condition = condition;
    }

    public String getColumn() {
        return column;
    }

    private void setColumn(String column) {
        this.column = column;
    }

    public String getCondition() {
        return condition;
    }

    private void setCondition(String condition) {
        this.condition = condition;
    }
}
