package za.ac.cput.util;

import java.util.UUID;

public class Helper {

    public static boolean isNullorEmpty(String s){
        return s == null || s.isEmpty();
    }

    public static boolean isNull(Boolean b) {
        return b == null;
    }

    public static UUID generateId(){
        return UUID.randomUUID();
    }
}
