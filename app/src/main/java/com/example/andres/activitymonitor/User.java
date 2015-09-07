package com.example.andres.activitymonitor;

import java.io.Serializable;

/**
 * <p>The User class holds the model of a user's personal information.</p>
 *
 * Created by andres on 9/3/15.
 */
public class User implements Serializable{
    private String _name;
    private double _bodyWeight;
    private double _height;
    private String _heightUnits;
    private String _weightUnits;
    private int _age;
    private String _gender;

    /**
     * Constructor
     *
     * @param name the username
     * @param bodyWeight user's body weight
     * @param height user's height
     * @param heightUnit the unit for height
     * @param age user's age
     * @param gender user's gender
     */
    public User(final String name, final double bodyWeight, final double height,
                final String heightUnit, final String weightUnit, final int age,
                final String gender) {
        _name = name;
        _bodyWeight = bodyWeight;
        _height = height;
        _heightUnits = heightUnit;
        _weightUnits = weightUnit;
        _age = age;
        _gender = gender;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return _name;
    }

    /**
     *
     * @param name
     */
    public void setName(final String name) {
        _name = name;
    }

    /**
     *
     * @return
     */
    public double getBodyWeight() {
        return _bodyWeight;
    }

    /**
     *
     * @param bodyWeight
     */
    public void setBodyWeight(final double bodyWeight) {
        _bodyWeight = bodyWeight;
    }

    /**
     *
     * @return
     */
    public String getWeightUnits() {
        return _weightUnits;
    }

    /**
     *
     * @param weightUnits
     */
    public void setWeightUnits(String weightUnits) {
        _weightUnits = weightUnits;
    }

    /**
     *
     * @return
     */
    public double getHeight() {
        return _height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(final double height) {
        _height = height;
    }

    /**
     *
     * @return
     */
    public String getHeightUnits() {
        return _heightUnits;
    }

    /**
     *
     * @param units
     */
    public void setHeightUnits(final String units) {
        _heightUnits = units;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return _age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age) {
        _age = age;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return _gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        _gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "_name=" + _name +
                ", _bodyWeight=" + _bodyWeight +
                ", _height=" + _height +
                ", _heightUnits=" + _heightUnits +
                ", _age=" + _age +
                ", _gender=" + _gender +
                '}';
    }
}
