# kafka-streams-practice
Application of kafka stream



Steps for executing the project:

1. Download Kafka from https://kafka.apache.org/quickstart

2. For windows users: Extract it to C:\ drive(Basically the path should not be long)

    Create folder in C drive with name as "kafka-logs"
 
    In file config/server.properties, find log.dirs and replace it with `log.dirs=C:\\kafka-logs`
 
    Create another folder in C drive with name as "zookeeper"
 
    In file config/zookeeper.properties , find dataDir and replace it with `dataDir=C:\\zookeeper`

3. Open powershell in admin mode
  
    cd C:\kafka_2.12-2.4.0

    run the command : .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

4. Open powershell in admin mode

    cd C:\kafka_2.12-2.4.0

    run the command: .\bin\windows\kafka-server-start.bat .\config\server.properties

5. Clone spring boot application and build it using Intellij

6. Open cmd in admin mode

    cd C:\kafka_2.12-2.4.0

    run command to create topic : 
    
    bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic input-topic
    
    bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic square-output
    
7. Run Spring boot application
