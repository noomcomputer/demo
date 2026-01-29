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
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Build image') {
            steps {
                script {
                    //dockerImage = docker.build("local/my-app:${version}")
					//dockerImage = docker.build("noomcomputer/repository:${version}")
					dockerImage = docker.build("demo:${version}")
                }
            }
        }
        stage('Push image') {
            steps {
                script {
                    //docker.withRegistry('https://centos1kubemaster:5000') {
                    //docker.withRegistry('http://centos1kubemaster:30031') {
                    //docker.withRegistry('http://localhost:30031') {
                    //docker.withRegistry('https://registry.gitlab.com') {
                    //docker.withRegistry('https://beonesuccess.com:5000') {
					docker.withRegistry('http://beonesuccess.com:5000') {
                        dockerImage.push()
                    }
                }
            }
        }
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
    }
}
