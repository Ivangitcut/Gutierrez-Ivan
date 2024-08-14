package test;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class OdontologosServiceTest {
    static Logger logger= Logger.getLogger(OdontologosServiceTest.class);
    @BeforeAll
    static void cargarTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection= DriverManager.getConnection("jdbc:h2./H2C;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (SQLException e){
                logger.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName()

}