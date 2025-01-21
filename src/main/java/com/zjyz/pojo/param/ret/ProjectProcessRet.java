package com.zjyz.pojo.param.ret;

public class ProjectProcessRet {


    public static class RentOutProcess {
        private String materialName;
        private Double process;
        private Integer rentNumber;
        private Integer predictRentNumber;
    }

    public static class ReturnProcess {
        private Double process;
        private Integer returnNumber;
        private Integer compensationNumber;
        private Integer predictAllNumber;
    }
}
