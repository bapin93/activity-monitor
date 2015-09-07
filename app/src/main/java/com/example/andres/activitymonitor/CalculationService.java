package com.example.andres.activitymonitor;

/**
 * Created by andres on 9/3/15.
 */
public class CalculationService {

    private static final double MALE_BASE = 66.47;
    private static final double FEMALE_BASE = 665.09;
    private static final double KM_IN_MILE = 0.621;
    private static final double RUNNING_CONSTANT = 0.63;
    private static final double WALKING_CONSTANT = 0.30;
    private static final double KG_IN_POUND = 2.2;
    private static final double CM_IN_INCH = 2.54;
    private static final double FEMALE_HEIGHT_CONSTANT = 1.84;
    private static final double FEMALE_WEIGHT_CONSTANT = 9.56;
    private static final double FEMALE_AGE_CONSTANT = 4.67;
    private static final double MALE_HEIGHT_CONSTANT = 5;
    private static final double MALE_WEIGHT_CONSTANT = 13.75;
    private static final double MALE_AGE_CONSTANT = 6.75;

    private User _user;

    /**
     *
     */
    public CalculationService(final User user) {
        _user = user;
    }

    /**
     *
     * @return the basal metabolic rate for the given user
     */
    public double calculateBMR() {
        double basalMetabolicRate = 0;
        if(_user.getGender().equalsIgnoreCase("male")) {
            if(_user.getWeightUnits().equalsIgnoreCase("lbs")) {
                if(_user.getHeightUnits().equalsIgnoreCase("in")) {
                    basalMetabolicRate =  MALE_BASE + MALE_WEIGHT_CONSTANT*_user.getBodyWeight()/
                            KG_IN_POUND + MALE_HEIGHT_CONSTANT *(_user.getHeight()*CM_IN_INCH) -
                            MALE_AGE_CONSTANT*_user.getAge();
                } else if (_user.getHeightUnits().equalsIgnoreCase("cm")) {
                    basalMetabolicRate =  MALE_BASE + MALE_WEIGHT_CONSTANT*_user.getBodyWeight()/
                            KG_IN_POUND + MALE_HEIGHT_CONSTANT *(_user.getHeight()) -
                            MALE_AGE_CONSTANT*_user.getAge();
                }
            } else if(_user.getWeightUnits().equalsIgnoreCase("kg")) {
                if(_user.getHeightUnits().equalsIgnoreCase("in")) {
                    basalMetabolicRate =  MALE_BASE + MALE_WEIGHT_CONSTANT*_user.getBodyWeight() +
                            MALE_HEIGHT_CONSTANT *(_user.getHeight()*CM_IN_INCH) -
                            MALE_AGE_CONSTANT*_user.getAge();
                } else if (_user.getHeightUnits().equalsIgnoreCase("cm")) {
                    basalMetabolicRate =  MALE_BASE + MALE_WEIGHT_CONSTANT*_user.getBodyWeight() +
                            MALE_HEIGHT_CONSTANT *_user.getHeight() -
                            MALE_AGE_CONSTANT*_user.getAge();
                }
            }
        } else if(_user.getGender().equalsIgnoreCase("female")) {
            if(_user.getWeightUnits().equalsIgnoreCase("lbs")) {
                if(_user.getHeightUnits().equalsIgnoreCase("in")) {
                    basalMetabolicRate =  FEMALE_BASE + FEMALE_WEIGHT_CONSTANT *
                            _user.getBodyWeight()/KG_IN_POUND + FEMALE_HEIGHT_CONSTANT *
                            _user.getHeight()*CM_IN_INCH - FEMALE_AGE_CONSTANT*_user.getAge();
                } else if (_user.getHeightUnits().equalsIgnoreCase("cm")) {
                    basalMetabolicRate =  FEMALE_BASE + FEMALE_WEIGHT_CONSTANT *
                            _user.getBodyWeight()/KG_IN_POUND + FEMALE_HEIGHT_CONSTANT *
                            _user.getHeight() - FEMALE_AGE_CONSTANT*_user.getAge();
                }
            } else if(_user.getWeightUnits().equalsIgnoreCase("kg")) {
                if(_user.getHeightUnits().equalsIgnoreCase("in")) {
                    basalMetabolicRate =  FEMALE_BASE + FEMALE_WEIGHT_CONSTANT *
                            _user.getBodyWeight() + FEMALE_HEIGHT_CONSTANT *_user.getHeight() *
                            CM_IN_INCH - FEMALE_AGE_CONSTANT * _user.getAge();
                } else if (_user.getHeightUnits().equalsIgnoreCase("cm")) {
                    basalMetabolicRate =  FEMALE_BASE + FEMALE_WEIGHT_CONSTANT *
                            _user.getBodyWeight() + FEMALE_HEIGHT_CONSTANT *_user.getHeight() -
                            FEMALE_AGE_CONSTANT*_user.getAge();
                }
            }
        }
        return basalMetabolicRate;
    }

    public double calculateRunBurn(final String units, final double distance) {
        double result = 0;

        if (units.equalsIgnoreCase("mi")) {
            result = _user.getBodyWeight() *
                    RUNNING_CONSTANT * distance;
        } else if (units.equalsIgnoreCase("km")) {
            result = _user.getBodyWeight() *
                    RUNNING_CONSTANT * distance * KM_IN_MILE;
        }
        return result;
    }

    public double  calculateWalkBurn(final String units, final double distance) {
        double result = 0;

        if (units.equalsIgnoreCase("mi")) {
            result = _user.getBodyWeight() *
                    WALKING_CONSTANT * distance;
        } else if (units.equalsIgnoreCase("km")) {
            result = _user.getBodyWeight() *
                    WALKING_CONSTANT * distance * KM_IN_MILE;
        }
        return result;
    }

    public double calculateTotalBurn(final double ... activities) {
        double result = 0;
        if(activities != null) {
            for (double d : activities) {
                result += d;
            }
        }
        return result;
    }
}
