package dao;

import database.DataSourceInit;
import entities.PriceInfo;
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
public class PriceInfoDaoImpl implements PriceInfoDao {
    static final String addQueryP = "INSERT INTO priceinfo (id, idConsignment, ConsignmentPrice, PaymentCondition, Consumer) VALUES (?,?,?,?,?)";
    static final String getByConsignmentP = "SELECT * FROM priceinfo WHERE idConsignment = ?";
    static final String changePriceQueryP = "UPDATE priceinfo SET ConsignmentPrice = ? WHERE idConsignment = ?";
    static final String getAllQueryP = "SELECT * FROM priceinfo";

    public static PriceInfoDaoImpl instance = null;
    public final DataSource DATASOURCE;

    private PriceInfoDaoImpl(DataSource datasource) {
        this.DATASOURCE = datasource;
    }

    synchronized public static PriceInfoDaoImpl getInstance() {
        if (instance == null) {
            try {
                instance = new PriceInfoDaoImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
                log.error(e.getMessage());
            }
        }
        return instance;
    }


    @Override
    public void addPrice(PriceInfo info) {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQueryP)
        ) {
            preparedStatement.setInt(1, info.getId());
            preparedStatement.setInt(2, info.getIdConsignment());
            preparedStatement.setInt(3, info.getConsignmentPrice());
            preparedStatement.setString(4, info.getPayCondition());
            preparedStatement.setString(5, info.getConsumer());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public PriceInfo getByConsignment(int consignmentId) {
        PriceInfo info = null;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getByConsignmentP)) {
            preparedStatement.setInt(1, consignmentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    info = new PriceInfo(
                            resultSet.getInt("id"),
                            resultSet.getInt("idConsignment"),
                            resultSet.getInt("ConsignmentPrice"),
                            resultSet.getString("PaymentCondition"),
                            resultSet.getString("Consumer")
                    );
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return info;

    }

    @Override
    public void changePrice(int consignmentId, int price) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(changePriceQueryP)
            ) {
                preparedStatement.setInt(1, price);
                preparedStatement.setInt(2, consignmentId);
                preparedStatement.execute();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }


    @Override
    public List<PriceInfo> getAll() {
        List<PriceInfo> allPrice = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllQueryP)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PriceInfo info = new PriceInfo(
                            resultSet.getInt("id"),
                            resultSet.getInt("idConsignment"),
                            resultSet.getInt("ConsignmentPrice"),
                            resultSet.getString("PaymentCondition"),
                            resultSet.getString("Consumer")
                    );
                    allPrice.add(info);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return allPrice;

    }
}

