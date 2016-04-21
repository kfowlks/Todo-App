
Create Database by running the below command
java -cp lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing


Execute SQL Scripts in the below folder
C:\home2\Todo-App\Todo-App-Web\src\main\resources\db\migration

V1_0__initial_load.sql


Updated the path of the database by editing the applicationContext.xml found in the below directory

C:\home2\Todo-App\Todo-App-Web\src\main\webapp\WEB-INF


Run the below web app by executing the below

gradle Todo-App-Web:run
 