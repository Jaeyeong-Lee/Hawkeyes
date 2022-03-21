package com.samsung.option;

public class CommandOption {
    private SearchOption searchOption;
    private SearchOption modifyOption;
    private String code;
    private boolean isPrint;

    public CommandOption(SearchOption searchOption, String code) {
        this.searchOption = searchOption;
        this.code = code;
    }

    public CommandOption(SearchOption searchOption, String code, boolean isPrint){
        this.searchOption = searchOption;
        this.code = code;
        this.isPrint = isPrint;
    }

    public CommandOption(SearchOption searchOption, SearchOption modifyOption, String code, boolean isPrint){
        this.searchOption = searchOption;
        this.modifyOption = modifyOption;
        this.code = code;
        this.isPrint = isPrint;
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
