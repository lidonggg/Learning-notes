package com.lidong.spring.ioc.overview.domain;

import com.lidong.spring.ioc.overview.annotation.Super;

/**
 * @author Ls J
 * @date 2020/5/5 4:20 PM
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                '}';
    }
}
