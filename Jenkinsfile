pipeline {
    agent any

    tools {
        maven 'Default'
        dockerTool 'Default'
    }

    environment {
        version = '1.0.1'
        dockerImage = ''
    }

    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "MAVEN_HOME = ${MAVEN_HOME}"
                '''
                sh 'docker version'
            }
        }

		/*stage('SonarQube analysis') {
            steps {
				withSonarQubeEnv(installationName: 'SonarQube') {
				//withSonarQubeEnv(credentialsId: 'sonarqube-root', installationName: 'SonarQube') {
					// maven
					//sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.5.0.1254:sonar'
					//sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
					
					// gradle
					sh 'chmod +x gradlew'
					sh './gradlew sonarqube --stacktrace'
				}
            }
		}*/
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        /*stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }*/
        stage('Build image') {
            steps {
                script {
                    //dockerImage = docker.build("local/my-app:${version}")
					//dockerImage = docker.build("noomcomputer/repository:${version}")
					dockerImage = docker.build("demo:${version}")
                }
            }
        }
        /*stage('Push image') {
            steps {
                script {
                    //docker.withRegistry('https://centos1kubemaster:5000') {
                    //docker.withRegistry('http://centos1kubemaster:30031') {
                    //docker.withRegistry('http://localhost:30031') {
                    //docker.withRegistry('https://registry.gitlab.com') {
                    docker.withRegistry('https://beonesuccess.com:5000') {
                        dockerImage.push()
                    }
                }
            }
            steps {
                script {
                    // Push the image to the registry using pre-configured credentials
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials-id') { // Replace 'docker-hub-credentials-id'
                        docker.image("your-dockerhub-username/your-app-image-name:latest").push()
                    }
                }
            }
        }*/
        /*stage('Deployment') {
            steps {
                withKubeConfig([credentialsId: 'kebernetes-access-token', serverUrl: 'https://192.168.217.128:8443']) {
					sh '/usr/local/bin/kubectl version'
					sh '/usr/local/bin/kubectl config view'
					sh '/usr/local/bin/kubectl get pods --namespace=jenkins'
					sh '/usr/local/bin/kubectl get svc --namespace=jenkins'
                    //sh '/usr/local/bin/kubectl delete -f k8s.deployment.yml --namespace=jenkins'
                    sh '/usr/local/bin/kubectl apply -f k8s.deployment.yml --namespace=jenkins'
                }
            }
        }*/
        stage('Deploy to Remote Server') {
            steps {
                // Example of deploying using SSH to a remote host
                // Requires Jenkins SSH plugin and pre-configured SSH credentials
                //sh 'ssh -o StrictHostKeyChecking=no user@remote-server-ip "docker pull your-dockerhub-username/your-app-image-name:latest && docker stop your-app-container || true && docker rm your-app-container || true && docker run -d --name your-app-container -p 8080:8080 your-dockerhub-username/your-app-image-name:latest"'
				sh "docker stop demo || true && docker rm demo || true && docker run -d --name demo -p 8083:8083 demo:latest"
				//sh 'ssh -o StrictHostKeyChecking=no root@beonesuccess.com "docker stop demo || true && docker rm demo || true && docker run -d --name demo -p 8083:8083 demo:latest"'
            }
        }
    }
}
