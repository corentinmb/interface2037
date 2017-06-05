docker rm -f systemeexpert && docker rm -f serveurquestion && docker rm -f rabbitmq && docker rm -f mysql-serv-q

cd ServeurQuestion/
mvn clean install && docker build -f Dockerfile -t serveurquestion .

cd ..\

cd SystemeExpert/
mvn clean install && docker build -f Dockerfile -t systemeexpert  .

docker run -d --name=rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
docker run -d -p 3306:3306 --name=mysql-serv-q --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_DATABASE=interface2037" mysql
docker run -d --name=serveurquestion --link mysql-serv-q:mysql --link rabbitmq:rabbitmq -p 8081:8081 serveurquestion

docker run -d --name=systemeexpert --link serveurquestion:serveurquestion -p 8082:8082 systemeexpert