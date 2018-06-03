package pl.sda;

import com.mongodb.client.MongoDatabase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MongoConnectorTest {

    @Test
    public void connect() throws Exception {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        MongoDatabase database = mongoConnector.connect();
        //then
        assertEquals(database.getCollection("grades").count(), 801);
    }


}