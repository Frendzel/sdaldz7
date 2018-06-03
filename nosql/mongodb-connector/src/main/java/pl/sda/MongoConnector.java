package pl.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoCredential.createCredential;

public class MongoConnector {

    private MongoClient mongoClient;

    public MongoDatabase connect() {
        MongoCredential mongoCredential =
                createCredential("test",
                        "test",
                        "test123".toCharArray());
        ServerAddress serverAddress =
                new ServerAddress("127.0.0.1");
        mongoClient = new MongoClient(serverAddress,
                prepareCredentials(mongoCredential));
        return mongoClient.getDatabase("test");
    }

    private List<MongoCredential> prepareCredentials(MongoCredential mongoCredential) {
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(mongoCredential);
        return credentials;
    }

}
