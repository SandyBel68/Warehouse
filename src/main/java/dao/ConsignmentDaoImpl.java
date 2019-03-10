package dao;

import database.DataSourceInit;
import entities.Consignment;
import lombok.extern.log4j.Log4j;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Log4j
public class ConsignmentDaoImpl implements ConsignmentDao {
    static final String addQueryC = "INSERT INTO consignment (idconsignment, idGoods, Quantity, ProductionDate, Status) VALUES (?,?,?,?,?)";
    static final String changeStatusQueryC = "UPDATE consignment SET Status = ? WHERE idconsignment = ?";
    static final String getByIdQueryC = "SELECT * FROM consignment WHERE idconsignment = ?";
    static final String getAllQueryC = "SELECT * FROM consignment";


    public static ConsignmentDaoImpl instance = null;
    public final DataSource DATASOURCE;

    private ConsignmentDaoImpl(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    synchronized public static ConsignmentDaoImpl getInstance() {
        if (instance == null) {
            try {
                instance = new ConsignmentDaoImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
                log.error(e.getMessage());
            }
        }
        return instance;
    }


    @Override
    public void addConsignment(Consignment con) {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQueryC)
        ) {
            preparedStatement.setInt(1, con.getIdConsignment());
            preparedStatement.setInt(2, con.getIdGoods());
            preparedStatement.setInt(3, con.getQuantity());
            preparedStatement.setDate(4, con.getProductionDate());
            preparedStatement.setString(5, con.getStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public void changeStatus(int idConsignment, String status) {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(changeStatusQueryC)
        ) {
            preparedStatement.setInt(2, idConsignment);
            preparedStatement.setString(1, status);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public Consignment getById(int idConsignment) {
        Consignment cons = null;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getByIdQueryC)) {
            preparedStatement.setInt(1, idConsignment);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cons = new Consignment(
                            resultSet.getInt("idconsignment"),
                            resultSet.getInt("idGoods"),
                            resultSet.getInt("Quantity"),
                            resultSet.getDate("ProductionDate"),
                            resultSet.getString("Status")
                    );
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return cons;
    }

    @Override
    public List<Consignment> getAll() {
        List<Consignment> allConsignments = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllQueryC)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Consignment cons = new Consignment(
                            resultSet.getInt("idconsignment"),
                            resultSet.getInt("idGoods"),
                            resultSet.getInt("Quantity"),
                            resultSet.getDate("ProductionDate"),
                            resultSet.getString("Status")
                    );
                    allConsignments.add(cons);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return allConsignments;
    }

}

