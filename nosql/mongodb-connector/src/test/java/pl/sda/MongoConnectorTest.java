package pl.sda;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Filters.*;
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

    @Test
    public void findAllGrades() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        MongoDatabase database = mongoConnector.connect();
        //then
        FindIterable<Document> grades =
                database.getCollection("grades").find();
        for (Document grade : grades) {
            System.out.println(grade);
        }
    }

    @Test
    public void findAllGradesWhereStudentIdGt100AndTypeEqExam() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        MongoDatabase database = mongoConnector.connect();
        //then
//db.grades.find({$and:[{student_id: {$gt: 100}},{type : "exam"} ] })


        Bson mergedFilters =
                and(
                        new Document("type", "exam"),
                        gt("student_id", 100)
                );

        FindIterable<Document> grades =
                database.getCollection("grades").find(mergedFilters);

        for (Document grade : grades) {
            System.out.println(grade);
        }
    }

    @Test
    public void findAllGradesWhereStudentIdLt100AndTypeEqExamOrHomework() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        MongoDatabase database = mongoConnector.connect();
        //then
        Bson mergedFilters =
                and(
                        or(
                                new Document("type", "exam"),
                                new Document("type", "homework")
                        ),
                        lt(
                                "student_id",
                                100
                        )
                );

        FindIterable<Document> grades =
                database.getCollection("grades").
                        find(mergedFilters);

        for (Document grade : grades) {
            System.out.println(grade);
        }
    }

    @Test
    public void calculateAvgPerStudent() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        MongoDatabase database = mongoConnector.connect();
        //then
        //db.grades.aggregate({$group: {_id: "$student_id", avg: {$avg: "$score"}}})
        MongoCollection<Document> grades =
                database.getCollection("grades");

        Bson group = Aggregates.
                group("$student_id",
                        avg("avg", "$score")
                );
        List<Bson> params = new ArrayList<Bson>();
        params.add(group);
        AggregateIterable<Document> aggregate = grades.aggregate(params);
        for (Document anAggregate : aggregate) {
            System.out.println(anAggregate);
        }
    }


}