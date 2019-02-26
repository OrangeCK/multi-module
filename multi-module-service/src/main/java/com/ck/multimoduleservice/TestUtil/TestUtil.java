package com.ck.multimoduleservice.TestUtil;

import java.util.Date;

public class TestUtil {

    public static void main(String[] args) {
        System.out.println("打印当前时间：" + getCurDate());
    }
    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurDate() {
        Date d = new Date();
        return d;
    }
}
