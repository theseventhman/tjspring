package com.tj.exercise.springframework.test.bean;

/**
 * @Author: tj
 * @Date: 2023/1/22 21:58
 */
public class Wife {
    private Husband husband;
    private IMother mother; //婆婆

    public String queryHusband() {
        return "Wife.husband、 Mother callMother:" + mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}
