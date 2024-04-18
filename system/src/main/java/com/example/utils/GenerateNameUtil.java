package com.example.utils;

public class GenerateNameUtil {

    /**
     * 随机生成姓名，其中male值是true为男，false为女
     * @param male
     * @return
     */
    public static String getName(Boolean male) {
        BuildFirstName buildFirstName = new BuildFirstName();
        BuildLastName buildLastName = new BuildLastName();
        return buildFirstName.insideFirstName() + buildLastName.insideLastName(male);
    }
}
