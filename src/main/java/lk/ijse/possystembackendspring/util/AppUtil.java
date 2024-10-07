package lk.ijse.possystembackendspring.util;

import java.util.UUID;

public class AppUtil {
    public static String createCustomerId(){
        return "Customer-"+ UUID.randomUUID();
    }
    public static String createItemId(){
        return "Item-"+ UUID.randomUUID();
    }
}
