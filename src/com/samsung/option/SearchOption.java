package com.samsung.option;

public class SearchOption {

    private String column;
    private String condition;

    public SearchOption(String column, String condition) {
        this.column = column;
        this.condition = condition;
    }

    public String getColumn() {
        return column;
    }

    public String getCondition() {
        return condition;
    }
}
