package com.example;

/**
 * Represents an example class that does something, I hope.
 */
public class Example extends Barker implements Woof {
    final String heck = "owo!!!!!!";

    public static Example doSomething() {
        final Example example = new Example();
        example.doesBark();
        example.getRixicleFluff();
        example.getWinterfox();

        return example;
    }

    /**
     * owo!!!!!!!!
     */
    public String getHeck() {
        return heck;
    }

    public Winterfox getWinterfox() {
        return new Winterfox(true, 18);
    }

    public RixicleFluff getRixicleFluff() {
        return RixicleFluff.Cutest;
    }

    @Bark
    public boolean doesBark() {
        return true;
    }

    @Override
    public void woof() {
        System.out.println("WOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOF");
    }
}
