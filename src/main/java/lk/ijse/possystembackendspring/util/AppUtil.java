package lk.ijse.possystembackendspring.util;

import java.util.UUID;

public class AppUtil {
    public static String createUserId(){
        return "USER-"+ UUID.randomUUID();
    }
}
