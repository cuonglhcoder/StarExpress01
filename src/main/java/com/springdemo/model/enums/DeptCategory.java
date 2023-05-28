package com.springdemo.model.enums;

public enum DeptCategory {
    GROUP ("TẬP ĐOÀN"),
    CORPORATION ("TỔNG CÔNG TY"),
    COMPANY ("CÔNG TY"),
    AREA ("KHU VỰC"),
    BRANCH ("CHI NHÁNH");

    String value;
    DeptCategory (String str){
        this.value = str;
    }
    public String getValue(){
        return  this.value;
    }

    }
