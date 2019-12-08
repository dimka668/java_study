package com.klyshov.oracle_sdb;

/**
 * Created by 16688641 on 16.03.2019.
 */

//import com.sbt.processing.dao.exception.DataException;
//import com.sbt.processing.dao.metric.MetricContext;
import oracle.jdbc.OracleShardingKey;
import oracle.jdbc.OracleType;
import oracle.ucp.jdbc.PoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Connection implements ConnectionManager {

    private final PoolDataSource directorsPool;
    private final PoolDataSource catalogPool;

    public ShardedConnectionManager(PoolDataSource directorsPool, PoolDataSource catalogPool) {
        this.directorsPool = directorsPool;
        this.catalogPool = catalogPool;
    }

    public Connection getConnection() {
        LOGGER.trace("GetConnection without collocation key");
        Connection connection = ConnectionContext.get();
        if (connection == null) {
            MetricContext.setTransactionStartLifeTime();
            try {
                LOGGER.trace("Create connection to shard catalog");
                connection = catalogPool.getConnection();
                connection.setAutoCommit(false);
                ConnectionContext.set(connection);
            } catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ignored) {
                }
                ConnectionContext.release();
                throw new DataException("Can't create connection", e);
            }
        }
        return connection;
    }

    public Connection getConnection(Object collocationKey) {
        LOGGER.trace("GetConnection with collocation key = " + collocationKey);
        Connection connection = ConnectionContext.get();
        if (connection == null) {
            MetricContext.setTransactionStartLifeTime();
            try {
                OracleShardingKey shardKey = directorsPool.createShardingKeyBuilder()
                        .subkey(collocationKey, OracleType.VARCHAR2)
                        .build();
                LOGGER.trace("Get connection from shard director with key " + shardKey);
                connection = directorsPool.createConnectionBuilder()
                        .shardingKey(shardKey)
                        .build();
                connection.setAutoCommit(false);
                ConnectionContext.set(connection);
            } catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ignored) {
                }
                ConnectionContext.release();
                throw new DataException("Can't create connection", e);
            }
        }
        return connection;
    }

}
