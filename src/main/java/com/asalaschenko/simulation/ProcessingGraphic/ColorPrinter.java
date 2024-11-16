package com.asalaschenko.simulation.ProcessingGraphic;

public class ColorPrinter {

    enum Color{
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        RED_BACKGROUND("\u001B[41m"),
        GREEN_BACKGROUND("\u001B[42m"),
        YELLOW_BACKGROUND("\u001B[43m"),
        RESET("\u001B[0m");

        private final String code;

        Color(String code){
            this.code = code;
        }

        public String getCode(){
            return code;
        }
    }

    public static void printlnRedText(String text){
        System.out.println(Color.RED.getCode()
                + text
                + Color.RESET.getCode());
    }

    public static void printlnGreenText(String text){
        System.out.println(Color.GREEN.getCode()
                + text
                + Color.RESET.getCode());
    }

    public static void printlnYellowText(String text){
        System.out.println(Color.YELLOW.getCode()
                + text
                + Color.RESET.getCode());
    }

    public static void printlnRedBackgroundText(String text){
        System.out.println(Color.RED_BACKGROUND.getCode()
                + text
                + Color.RESET.getCode());
    }

    public static void printlnYellowBackgroundText(String text){
        System.out.println(Color.YELLOW_BACKGROUND.getCode()
                + text
                + Color.RESET.getCode());
    }

    public static void printlnGreenBackgroundText(String text){
        System.out.println(Color.GREEN_BACKGROUND.getCode()
                + text
                + Color.RESET.getCode());
    }

    public static void printRedText(String str) {
        System.out.print("\u001B[31m"
                + str
                + "\u001B[0m");
    }

    public static void printGreenText(String str) {
        System.out.print("\u001B[32m"
                + str
                + "\u001B[0m");
    }

    public static void printYellowText(String str) {
        System.out.print("\u001B[33m"
                + str
                + "\u001B[0m");
    }

    public static void printYellowBackgroundText(String str) {
        System.out.print("\u001B[43m"
                + str
                + "\u001B[0m");
    }

    public static void printCyanText(String str) {
        System.out.print("\u001B[36m"
                + str
                + "\u001B[0m");
    }

    public static void printPurpleText(String str) {
        System.out.print("\u001B[35m"
                + str
                + "\u001B[0m");
    }

    public static void printBlueText(String str) {
        System.out.print("\u001B[34m"
                + str
                + "\u001B[0m");
    }

}
