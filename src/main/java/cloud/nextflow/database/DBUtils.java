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
    private int type = 0;

    private int SQLCONNECTOR = 0;
    private int MONGOCONNECTOR = 1;

    public DBUtils(SQLConnector connector) {
        this.sqlConnector = connector;
        this.type = SQLCONNECTOR;
    }

    public DBUtils(MongoConnector connector) {
        this.mongoConnector = connector;
        this.type = MONGOCONNECTOR;
    }

    public int getType() {
        return type;
    }

    // Example of insert method
    public void insertExample(String username, String email) {
        if (type == MONGOCONNECTOR) {
            Document insertDocument = new Document()
                    .append("username", username)
                    .append("email", email);
            mongoConnector.getCollection().insertOne(insertDocument);
        } else if (type == SQLCONNECTOR) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = sqlConnector.getHikariCP().getConnection();

                preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?, ?)");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);

                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
