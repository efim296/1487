package main.java.services;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
public class DataSourceFactory {
public static DataSource getMyPGDataSource() {
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName("SDB");
        source.setServerName("localhost");
        source.setDatabaseName("lab");
        source.setUser("postgres");
        source.setPassword("yecgaa");
        source.setMaxConnections(10);
        return source;
    }
}
