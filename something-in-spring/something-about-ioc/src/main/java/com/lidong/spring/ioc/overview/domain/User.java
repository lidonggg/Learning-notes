package com.lidong.spring.ioc.overview.domain;

import com.lidong.spring.ioc.overview.constants.City;

import java.util.List;

/**
 * @author Ls J
 * @date 2020/5/5 4:11 PM
 */
public class User {

    private Long id;

    private String name;

    private String city;

    private String workCities;

    private List<City> lifeCities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorkCities() {
        return workCities;
    }

    public void setWorkCities(String workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", workCities='" + workCities + '\'' +
                ", lifeCities=" + lifeCities +
                '}';
    }
}
