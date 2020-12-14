package com.example.potential;

import java.util.Comparator;
class Ascending implements Comparator<Integer>{
    public int compare(Integer a, Integer b)
    {
        return a.compareTo(b);
    }
}