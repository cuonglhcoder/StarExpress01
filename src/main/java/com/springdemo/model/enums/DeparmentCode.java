package com.springdemo.model.enums;

public enum DeparmentCode {
    MKT ("MKT"),
    HR ("HR"),
    IT("IT"),
    FIN("FIN"),
    ACC("ACC"),
    PRO ("PRO"),
    LOG ("LOG"),
    SAL ("SAL");
    String value;
    DeparmentCode(String str){
        this.value = str;
    }
    public String getValue(){
        return  this.value;
    }

}
