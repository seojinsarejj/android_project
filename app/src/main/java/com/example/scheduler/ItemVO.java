package com.example.scheduler;

import java.io.Serializable;

class ItemVO implements Serializable {

    String titleStr;
    String dateStr;
    String dday;

    public ItemVO(String titleStr,String dateStr,String dday){

        this.titleStr = titleStr;
        this.dateStr = dateStr;
        this.dday = dday;

    }

    public String getTitleStr() {
        return titleStr;
    }

    public String getDateStr() {
        return dateStr;
    }

    public String getDday() {
        return dday;
    }


}

class ItemVO2 implements Serializable {

    String contents;

    public ItemVO2(String contents) {
        this.contents = contents;
    }

    public String getContentsStr() {
        return contents;
    }
}


