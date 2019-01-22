package com.grug.anyTech;

import com.alibaba.fastjson.JSON;

/**
 * Created by feichen on 2019/1/14.
 */
public class InnerClass {

    private String name;

    public static class Test {
        private String testName;
        private String testAge;

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public String getTestAge() {
            return testAge;
        }

        public void setTestAge(String testAge) {
            this.testAge = testAge;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        innerClass.setName("name");

        System.out.println(JSON.toJSONString(innerClass));

        Test test = new Test();
        test.setTestName("testName");
        test.setTestAge("testAge");
        String jsonString = JSON.toJSONString(test);
        System.out.println(jsonString);

        Test test1 = JSON.parseObject(jsonString, Test.class);

        System.out.println(JSON.toJSONString(test1));


    }
}
