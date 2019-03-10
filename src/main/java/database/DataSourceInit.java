package database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public interface DataSourceInit {
    ComboPooledDataSource msInstance = new ComboPooledDataSource();

    static DataSource getMsInstance () throws IOException, PropertyVetoException{
        synchronized (DataSourceInit.class) {
            final Properties properties = new Properties();
            try (InputStream resourceAsStream = DataSourceInit.class.getResourceAsStream(
                    "/jdbc/jdbc.properties")) {
                properties.load(resourceAsStream);
                final String url = properties.getProperty("url");
                final String userName = properties.getProperty("userName");
                final String password = properties.getProperty("password");
                final String driver = properties.getProperty("driver");
                final int maxpoolsize = Integer.parseInt(properties.getProperty("pool.max"));
                msInstance.setJdbcUrl(url);
                msInstance.setUser(userName);
                msInstance.setPassword(password);
                msInstance.setDriverClass(driver);
                msInstance.setMaxPoolSize(maxpoolsize);
            }
        }
        return msInstance;
    }
}
