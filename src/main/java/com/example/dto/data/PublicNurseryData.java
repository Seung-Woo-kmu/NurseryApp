package com.example.dto.data;

import java.util.List;

public class PublicNurseryData {
    public ChildCareInfo ChildCareInfo;
    public static class ChildCareInfo {
        public int list_total_count;
        public Result RESULT;
        public List<ChildCareCenter> row;

        public static class Result {
            public String CODE;
            public String MESSAGE;
        }

        public static class ChildCareCenter {
            public String SIGUNNAME;
            public String STCODE;
            public String CRNAME;
            public String CRTYPENAME;
            public String CRSTATUSNAME;
            public String ZIPCODE;
            public String CRADDR;
            public String CRTELNO;
            public String CRFAXNO;
            public String CRHOME;
            public String NRTRROOMCNT;
            public String NRTRROOMSIZE;
            public String PLGRDCO;
            public String CCTVINSTLCNT;
            public String CHCRTESCNT;
            public String CRCAPAT;
            public String CRCHCNT;
            public String LA;
            public String LO;
            public String CRCARGBNAME;
            public String CRCNFMDT;
            public String CRPAUSEBEGINDT;
            public String CRPAUSEENDDT;
            public String CRABLDT;
            public String DATASTDRDT;
            public String CRSPEC;
            public double CLASS_CNT_00;
            public double CLASS_CNT_01;
            public double CLASS_CNT_02;
            public double CLASS_CNT_03;
            public double CLASS_CNT_04;
            public double CLASS_CNT_05;
            public double CLASS_CNT_M2;
            public double CLASS_CNT_M5;
            public double CLASS_CNT_SP;
            public double CLASS_CNT_TOT;
            public double CHILD_CNT_00;
            public double CHILD_CNT_01;
            public double CHILD_CNT_02;
            public double CHILD_CNT_03;
            public double CHILD_CNT_04;
            public double CHILD_CNT_05;
            public double CHILD_CNT_M2;
            public double CHILD_CNT_M5;
            public double CHILD_CNT_SP;
            public double CHILD_CNT_TOT;
            public double EM_CNT_0Y;
            public double EM_CNT_1Y;
            public double EM_CNT_2Y;
            public double EM_CNT_4Y;
            public double EM_CNT_6Y;
            public double EM_CNT_A1;
            public double EM_CNT_A2;
            public double EM_CNT_A3;
            public double EM_CNT_A4;
            public double EM_CNT_A5;
            public double EM_CNT_A6;
            public double EM_CNT_A10;
            public double EM_CNT_A7;
            public double EM_CNT_A8;
            public double EM_CNT_TOT;
            public String WORK_DTTM;
        }
    }
}