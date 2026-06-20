package com.llanquihuetour.util;

public enum Color {
    /**
     * Color reset.
     */
    RESET("\u001B[0m"),
    /**
     * Red color.
     */
    RED("\u001B[31m"),
    /**
     * Green color.
     */
    GREEN("\u001B[32m"),
    /**
     * Yellow color.
     */
    YELLOW("\u001B[33m"),
    /**
     * Cyan color.
     */
    CYAN("\u001B[36m"),
    /**
     * Blue color.
     */
    BLUE("\u001B[34m"),
    /**
     * White color.
     */
    WHITE("\u001B[37m"),
    /**
     * Purple color.
     */
    PURPLE("\u001B[35m"),
    /**
     * Black color.
     */
    BLACK("\u001B[30m");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }
}

