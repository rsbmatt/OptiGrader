package org.mahabal.optigrader.api;

import com.zaxxer.hikari.HikariDataSource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Matthew
 */
public class ApiServer {

    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "somefuckingpassword?";
    private static final String DATABASE_NAME = "optigrader";
    private static Logger log = null;

    static {
        InputStream stream = ApiServer.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            log = Logger.getLogger(ApiServer.class.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final ServletHandler handler = new ServletHandler();

    ApiServer() {

        log.info("Starting API Server...");

        // set the data source and create the connection
        final HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/" + DATABASE_NAME);
        ds.setUsername(DATABASE_USERNAME);
        ds.setPassword(DATABASE_PASSWORD);

        // create the access point for JDBI and set the data source
        final Jdbi dbi = Jdbi.create(ds);
        log.info("Successfully connected to database");
        dbi.installPlugin(new SqlObjectPlugin());

    }

    public static void main(String[] args) throws Exception {

        // create an instance of the Jetty server and tell it to listen on port 8080
        final Server server = new Server();

        // create an instance of this ApiServer and set the server's handler
        final ApiServer api = new ApiServer();
        server.setHandler(api.handler);

        // start the server
        server.start();
        log.info("Server is now listening on port: 8080");
        server.join();

    }


}
