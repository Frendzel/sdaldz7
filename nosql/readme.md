BEFORE:

Import data

mongoimport --db test --collection grades --drop --file grades.json
Creating admin user

use admin
db.createUser({user: "root",pwd: "password",roles: ["userAdminAnyDatabase", "dbAdminAnyDatabase", "readWriteAnyDatabase"],})
Creating test user

use test
db.createUser(
  {
    user: "test",
    pwd: "test123",
    roles: [ { role: "readWrite", db: "test" }]
  }
)
TASKS:

ARCHITECTURE
Extract connection properties to separated file
Prepare configuration class responsible for reading properties (can be singleton)
Map collection to java bean
Turn on log4j logging
Try to connect to DB (remember about turning on mongod!)
MONGODB CRUD
INSERT COLLECTION https://www.mkyong.com/mongodb/java-mongodb-insert-a-document/
SHOW DOCUMENTS NUMBER INSIDE COLLECTION
STUDENTS ID > 100
STUDENT GRADES WHERE ID > 100 AND GRADE TYPE = EXAM
STUDENT IDS WITH AVG
SORT STUDENT IDS
INCREMENT ALL EXAM GRADES IF THEY ARE IN RANGE <10,20> https://docs.mongodb.com/manual/reference/operator/update/max/#up._S_max
IMPORTANT:

https://docs.mongodb.com/manual/crud/
https://docs.mongodb.com/manual/tutorial/insert-documents/
https://docs.mongodb.com/manual/tutorial/query-documents/
https://docs.mongodb.com/manual/tutorial/update-documents/
https://docs.mongodb.com/manual/tutorial/remove-documents/
https://docs.mongodb.com/manual/aggregation/
