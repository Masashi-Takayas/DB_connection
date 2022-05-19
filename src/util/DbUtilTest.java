package util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DbUtilTest {

    @Test
    public void testGetConnection() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            assertEquals(false, conn.isClosed());
        }
    }

}
