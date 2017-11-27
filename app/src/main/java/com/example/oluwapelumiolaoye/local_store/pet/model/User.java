package com.example.oluwapelumiolaoye.local_store.pet.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by oluwapelumi.olaoye on 11/24/17.
 */

public class User extends RealmObject {

    private String name;
    private String gender;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
