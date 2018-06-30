package com.learn.ssm.annotations.newfeatures;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class learn {
    //psvm
    public static void main(String[] args) {
       //循环 fori
        //sout
        // ctrl + j
        List<Integer> list =  Arrays.asList(1,2,3,4,5);

        list =  list.stream().map(item -> ++ item ).collect(Collectors.toList());

        System.out.println(list);

       list =  list.stream()
                    .map(item -> ++ item )
                    .filter(item -> item > 4)
                    .collect(Collectors.toList());

        System.out.println(list);

        int sum = list.stream().map(item -> ++item).reduce(0, Integer::sum);
        System.out.println(sum);
    }
}