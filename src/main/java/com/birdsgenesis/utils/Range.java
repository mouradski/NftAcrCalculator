package com.birdsgenesis.utils;

public class Range {
    private Integer start;
    private Integer end;

    public Range(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }


    public boolean contains(Integer value) {
        if (value == null) {
            return false;
        }

        return value >= start && value <= end;
    }
}
