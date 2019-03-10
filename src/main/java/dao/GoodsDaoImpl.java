package dao;

import database.DataSourceInit;
import entities.Goods;
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
public class GoodsDaoImpl implements GoodsDao {

    static final String addQueryG = "INSERT INTO goods (idGoods, GoodsName, ProductionGroup, UnitOfMeasure, Package) VALUES (?,?,?,?,?)";
    static final String getByNameG = "SELECT * FROM goods WHERE GoodsName = ?";
    static final String getByGoodsIdQueryG = "SELECT * FROM goods WHERE idGoods = ?";
    static final String getAllQueryG = "SELECT * FROM goods";

    public static GoodsDaoImpl instance = null;
    public final DataSource DATASOURCE;

    private GoodsDaoImpl(DataSource datasource) {
        this.DATASOURCE = datasource;
    }

    synchronized public static GoodsDaoImpl getInstance() {
        if (instance == null) {
            try {
                instance = new GoodsDaoImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
                log.error(e.getMessage());
            }
        }
        return instance;
    }

    @Override
    public void addGoods(Goods good) {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQueryG)
        ) {
            preparedStatement.setInt(1, good.getIdGoods());
            preparedStatement.setString(2, good.getGoodsName());
            preparedStatement.setString(3, good.getProductionGroup());
            preparedStatement.setString(4, good.getUnitOfMeasure());
            preparedStatement.setString(5, good.getPack());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public Goods getByName(String goodsName) {
        Goods good = null;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getByNameG)) {
            preparedStatement.setString(1, goodsName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    good = new Goods(
                            resultSet.getInt("idGoods"),
                            resultSet.getString("GoodsName"),
                            resultSet.getString("ProductionGroup"),
                            resultSet.getString("UnitOfMeasure"),
                            resultSet.getString("Package")
                    );
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return good;
    }

    @Override
    public Goods getById(int goodsId) {
        Goods good = null;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getByGoodsIdQueryG)) {
            preparedStatement.setInt(1, goodsId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    good = new Goods(
                            resultSet.getInt("idGoods"),
                            resultSet.getString("GoodsName"),
                            resultSet.getString("ProductionGroup"),
                            resultSet.getString("UnitOfMeasure"),
                            resultSet.getString("Package")
                    );
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return good;
    }

    @Override
    public List<Goods> getAll() {
        List<Goods> allGoods = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllQueryG)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Goods good = new Goods(
                            resultSet.getInt("idGoods"),
                            resultSet.getString("GoodsName"),
                            resultSet.getString("ProductionGroup"),
                            resultSet.getString("UnitOfMeasure"),
                            resultSet.getString("Package")
                    );
                    allGoods.add(good);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return allGoods;
    }
}
