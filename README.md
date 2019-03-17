# TriviaGameServer
Trivia Game -  server side, includes API & MySQL integration.


This is my final project for the course Android 1 in my university (Holon Institute of Technology), it includes 9 java files:

apiImport.java:
Created in order to import questions from https://opentdb.com/ API to local SQL DataBase, by creating ArrayList of the questions, 
then parse as JSON array.


Decryptor.java:
In order to decrypt the encrypted (using AES algorithm) vars regarding the connection to the DataBase.


exportQues.java:
This class was created in order to set the general export/import method from the server to the client side.


leaderboard.java:
This class was created in order to set leaderboard details.


mySQLdb.java:
implements DBAccessInterface:
1. Creating a basic connection to the local DataBase
2. query function, that would print a row from the DataBase or add a new row to the DataBase.
3. queryImportlb function that would import new row to the leaderboard table.
4. queryExportlb function that exports the first 10 rows of leaderboard table into ArrayList.
5. exportQueryApi function that exports the first 10 rows of questions table into ArrayList according to the diff.


TCPServer.java and socketHandler.java:
These 2 classes are responsible for the connectivity of client-server.


Terminal.java:
This class was added in order to add new questions manually to the database 
or import new questions to the database using API integration from https://opentdb.com/ 
