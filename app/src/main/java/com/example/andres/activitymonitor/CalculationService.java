package com.example.andres.activitymonitor;

/**
 * Created by andres on 9/3/15.
 */
public class CalculationService {

    private static final double MALE_BASE = 66.47;
    private static final double FEMALE_BASE = 665.09;


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
}
