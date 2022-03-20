package com.samsung.option;

public class CommandOption {
    private SearchOption searchOption;
    private String code;

    public CommandOption(SearchOption searchOption, String code){
        this.searchOption = searchOption;
        this.code = code;
    }

    public SearchOption getSearchOption() {
        return searchOption;
    }

    private void setSearchOption(SearchOption searchOption) {
        this.searchOption = searchOption;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }
}
