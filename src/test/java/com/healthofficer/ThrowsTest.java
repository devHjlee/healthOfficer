package com.healthofficer;

import org.mockito.internal.matchers.Null;

public class ThrowsTest {
    public static void main(String[] args){
        returnThrows(10);

    }
    public static void returnThrows(int a) throws NullPointerException{
        returnThrows2(3);

        try{
            if(a < 10){
                System.out.println("a:"+a);
            }
        }catch(NullPointerException e){
            throw e;
        }

    }

    public static void returnThrows2(int a) throws NullPointerException{
        try{
            if(a < 10){
                System.out.println("a:"+a);
            }
        }catch(NullPointerException e){
            throw e;
        }

    }
}


