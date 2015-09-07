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


    /**
     *
     * @param user
     */
    public CalculationService() {
    }


    public double calculateBMR(User user) {
        double basalMetabolicRate = 0;
        if(user.getGender().equalsIgnoreCase("male")) {
            if(user.getWeightUnits().equalsIgnoreCase("lbs")) {
                if(user.getHeightUnits().equalsIgnoreCase("in")) {
                    basalMetabolicRate =  MALE_BASE + 13.75*user.getBodyWeight()/2.2 +
                            5*(user.getHeight()*2.54) - 6.75*user.getAge();
                } else if (user.getHeightUnits().equalsIgnoreCase("cm")) {
                    basalMetabolicRate =  MALE_BASE + 13.75*user.getBodyWeight()/2.2 +
                            5*(user.getHeight()) - 6.75*user.getAge();
                }
            } else if(user.getWeightUnits().equalsIgnoreCase("kg")) {
                if(user.getHeightUnits().equalsIgnoreCase("in")) {
                    basalMetabolicRate =  MALE_BASE + 13.75*user.getBodyWeight() +
                            5*(user.getHeight()*2.54) - 6.75*user.getAge();
                } else if (user.getHeightUnits().equalsIgnoreCase("cm")) {
                    basalMetabolicRate =  MALE_BASE + 13.75*user.getBodyWeight() +
                            5*user.getHeight() - 6.75*user.getAge();
                }
            }
        } else if(user.getGender().equalsIgnoreCase("female")) {
            if(user.getWeightUnits().equalsIgnoreCase("lbs")) {
                if(user.getHeightUnits().equalsIgnoreCase("in")) {
                    basalMetabolicRate =  FEMALE_BASE + 9.56*user.getBodyWeight()/2.2 +
                            1.84*(user.getHeight()*2.54) - 4.67*user.getAge();
                } else if (user.getHeightUnits().equalsIgnoreCase("cm")) {
                    basalMetabolicRate =  FEMALE_BASE + 9.56*user.getBodyWeight()/2.2 +
                            1.84*(user.getHeight()) - 4.67*user.getAge();
                }
            } else if(user.getWeightUnits().equalsIgnoreCase("kg")) {
                if(user.getHeightUnits().equalsIgnoreCase("in")) {
                    basalMetabolicRate =  FEMALE_BASE + 9.56*user.getBodyWeight() +
                            1.84*(user.getHeight()*2.54) - 4.67*user.getAge();
                } else if (user.getHeightUnits().equalsIgnoreCase("cm")) {
                    basalMetabolicRate =  FEMALE_BASE + 9.56*user.getBodyWeight() +
                            1.84*user.getHeight() - 4.67*user.getAge();
                }
            }
        }
        return basalMetabolicRate;
    }

    public double calculateRunBurn(User user, String units, double distance) {
        double result = 0;

        if (units.equalsIgnoreCase("mi")) {
            result = user.getBodyWeight() *
                    RUNNING_CONSTANT * distance;
        } else if (units.equalsIgnoreCase("km")) {
            result = user.getBodyWeight() *
                    RUNNING_CONSTANT * distance * KM_IN_MILE;
        }
        return result;
    }

    public double  calculateWalkBurn(User user, String units, double distance) {
        double result = 0;

        if (units.equalsIgnoreCase("mi")) {
            result = user.getBodyWeight() *
                    WALKING_CONSTANT * distance;
        } else if (units.equalsIgnoreCase("km")) {
            result = user.getBodyWeight() *
                    WALKING_CONSTANT * distance * KM_IN_MILE;
        }
        return result;
    }

    public double calculateTotalBurn(double ... activities) {
        double result = 0;
        if(activities != null) {
            for (double d : activities) {
                result += d;
            }
        }
        return result;
    }
}
