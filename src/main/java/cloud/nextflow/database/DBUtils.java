package cloud.nextflow.database;

import cloud.nextflow.database.types.mongo.MongoConnector;
import cloud.nextflow.database.types.sql.SQLConnector;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    private MongoConnector mongoConnector;
    private SQLConnector sqlConnector;
    private String type;

    public DBUtils(SQLConnector connector) {
        this.sqlConnector = connector;
        this.type = "SQLCONNECTOR";
    }

    public DBUtils(MongoConnector connector) {
        this.mongoConnector = connector;
        this.type = "MONGOCONNECTOR";
    }

    public String getType() {
        return type;
    }
}
