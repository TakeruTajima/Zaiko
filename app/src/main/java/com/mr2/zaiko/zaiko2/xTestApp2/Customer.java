package com.mr2.zaiko.zaiko2.xTestApp2;

import javax.inject.Inject;

/**
 * Created by ryosuke on 2018/02/14.
 */

public class Customer {

    long id;
    Boolean isLogin = false;

    @Inject
    public Customer(){}

    public long id(){return  id;}
    public String login(){
        if (isLogin) {
            return "loginしています";
        }

        isLogin = true;
        return "loginしました";
    }
}
