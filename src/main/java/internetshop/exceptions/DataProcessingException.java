package internetshop.exceptions;

import java.sql.SQLException;

public class DataProcessingException extends Exception {
    public DataProcessingException(String s, SQLException e) {
        super(s, e);
    }
}