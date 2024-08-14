package dao.impl;

import dao.IDao;
import db.H2C;
import model.Odontologos;
import org.apache.log4j.Logger;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class OdontologosDaoH2 implements IDao<Odontologos> {
    public static final Logger logger = Logger.getLogger(OdontologosDaoH2.class);
    public static final String INSERT=  "INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    @Override
    public Odontologos guardar(Odontologos odontologos) {
        Connection connection = null;
        Odontologos odontologosAretornar = null;
        try{
            connection= H2C.getConecction();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,odontologos.getNumeroMatricula());
            preparedStatement.setString(2,odontologos.getNombre());
            preparedStatement.setString(3,odontologos.getApellido());
            connection.commit();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                Integer id= resultSet.getInt(1);
                odontologosAretornar = new Odontologos(id, odontologos.getNumeroMatricula(),odontologos.getNombre(),odontologos.getApellido());
            }

            logger.info("Odontologo persistido"+odontologosAretornar);

        }catch (Exeption e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            }finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            logger.error(ex.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            }
        }
        return odontologosAretornar;
    }

    @Override
    public List<T> listarTodos() {
        return null;
    }
}
