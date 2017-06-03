docker rm -f systemeexpert && docker rm -f serveurquestion && docker rm -f rabbitmq && docker rm -f mysql-serv-q

cd ServeurQuestion/
mvn clean install && docker build -f Dockerfile -t serveurquestion .

cd ..

cd SystemeExpert/
mvn clean install && docker build -f Dockerfile -t systemeexpert  .

docker run -d --net=host --name=rabbitmq rabbitmq
docker run -d --net=host -p 3306:3306 --name=mysql-serv-q --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_DATABASE=interface2037" mysql
docker run -d --net=host --name=serveurquestion -p 8081:8081 serveurquestion

docker run -d --net=host --name=systemeexpert -p 8082:8082 systemeexpert