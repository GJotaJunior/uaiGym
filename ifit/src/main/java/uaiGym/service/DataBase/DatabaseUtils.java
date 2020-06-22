package uaiGym.service.DataBase;

import java.sql.Date;

public class DatabaseUtils {
    public static Date converteData(java.util.Date data) {
	 return (data != null) ? new java.sql.Date(data.getTime())
		    : null;
    }
}
