package org.csu.mypetstore.utils;
public class Validator {
    private static final Validator soleInstance = new Validator();

    private Validator() {
    }

    public static Validator getSoleInstance() {
        return soleInstance;
    }

    public boolean isNull (String str){
        return str == null;
    }

    public boolean isNull (Object object){
        return object == null;
    }


    public boolean isLengthEqualTo(String str, int size){
        return str.length() == size;
    }

    public boolean isLessThan(int a, int b){
        return a < b;
    }

    public boolean isGreaterThan(int a, int b){
        return a > b;
    }

}
