package com.springdemo.model.enums;

public enum DeparmentCode {
    MKT,
    HR,
    IT,
    FIN,
    ACC,
    PRO,
    LOG,
    SAL;

    private String departmentCode;
     private DeparmentCode(String departmentCode) {
         this.departmentCode = departmentCode;

     }
        public String getDepartmentCode() {
            return departmentCode;
        }
}
