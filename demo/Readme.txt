install S/W:
	chocolatey v0.10.15
	jdk v11.0.7
	maven v3.6.3
	git v2.26.2
	nodejs v14.0.0
	oracle vm virtual box 6.0.20
	docker-cli v19.03.3
	docker-toolbox v19.03.1
	docker-desktop v2.3.0.1

install IDE:
	eclipse latest
	visual code studio latest

upgrade chocolatey v0.10.15:
choco outdated or choco upgrade <pkg|all> -whatif
choco upgrade chocolatey
choco upgrade all
choco outdated

upgrade npm v6.14.4:
npm -v
npm install npm@latest -g
npm cache clean -f
npm install -g n
n stable
npm -v

upgrade nodejs v14.0.0:
node -v
cup nodejs
npm update -g
node -v

install angular 9.1.3:
ng version
npm install -g @angular/cli
ng version

create spring boot v2.2.6 + tomcat 9.0.34:
https://start.spring.io/

create angular project:
ng new hello
cd hello
npm install
ng serve --open
http://localhost:4200/
ng build --prod --output-path=../../resources/static

run spring boot:
mvn spring-boot:run
mvn clean package
java -jar target/demo.jar
http://localhost:8083/

build docker image:
docker build -f Dockerfile -t ubuntu1:5000/demo .
docker tag 0.1 ubuntu1:5000/demo
verify created docker image:
docker images
non-secure registry configuration for image builder:
create C:\ProgramData\Docker\config\daemon.json or /etc/docker/daemon.json
{
  "insecure-registries": ["ubuntu1:5000"]
}
systemctl restart docker
push image to private registry:
docker push ubuntu1:5000/demo
pull image from private registry:
docker pull ubuntu1:5000/demo
running docker image:
docker run -p 8083:8083 --name demo ubuntu1:5000/demo --> run with log
docker run -d -p 8083:8083 --name demo ubuntu1:5000/demo --> silent run
http://localhost:8083/
stop/kill running docker image:
docker ps
docker stop <container_id>
docker kill <container_id>
remove docker container:
docker container ls -a
docker container rm <container_id>
remove docker image:
docker rmi ubuntu1:5000/demo:<version>
