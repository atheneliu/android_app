package com.example.xingshulin.intent1;

import java.io.Serializable;

/**
 * Created by xingshulin on 2017/10/16.
 */

public class User implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String age;
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
