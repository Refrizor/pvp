package me.refriz.utils;

public class Initializer {

    public static void init() {

        new EventLoader().init();
        new EventLoader().cosmetics();
        new EventLoader().mistforth();
    }
}