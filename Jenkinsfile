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
        }*/
        /*stage('Push image') {
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
                //sh 'ssh -o StrictHostKeyChecking=no user@remote-server-ip "docker pull your-dockerhub-username/your-app-image-name:latest && docker stop your-app-container || true && //docker rm your-app-container || true && docker run -d --name your-app-container -p 8080:8080 your-dockerhub-username/your-app-image-name:latest"'
				//sh "docker stop demo || true && docker rm demo || true && docker run -d --name demo -p 8083:8083 demo:latest"
				//sh 'ssh -o StrictHostKeyChecking=no root@beonesuccess.com "docker stop demo || true && docker rm demo || true && docker run -d --name demo -p 8083:8083 demo:latest"'
				sh 'ssh -l root beonesuccess.com "bash pwd"'
            }
        }
        /*stage('Deploy via SSH Agent') {
            steps {
                // The 'remote-server-ssh-key' ID must match your Jenkins credential ID
                sshagent(credentials: ['remote-server-ssh-key']) {
                    // Use standard 'sh' (shell) commands with the -p flag for the port
                    //sh 'scp -P 2222 ./your-artifact.jar remote-username@your-remote-host-ip:/path/to/remote/directory'
                    //sh 'ssh -p 2222 remote-username@your-remote-host-ip "bash /path/to/remote/directory/deploy.sh"'
					sh 'ssh -p 2522 root@beonesuccess.com "bash pwd"'
                }
            }
        }*/
        /*stage('Deploy via SSH Agent') {
            steps {
                script {
                    // Define remote configuration including a specific port
                    def remote = [:]
                    remote.host = 'beonesuccess.com'
                    remote.port = 2522 // Specify your custom port here
                    remote.user = 'root'
                    //remote.identity = 'your-private-key-content' // Or use identityFile
					remote.identity = '-----BEGIN OPENSSH PRIVATE KEY-----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-----END OPENSSH PRIVATE KEY-----'

                    remote.allowAnyHosts = true // Use with caution, see Jenkins docs for secure host checking

                    // Example: Copy a file
                    //sshPut remote: remote, from: 'your-artifact.jar', into: '/path/to/remote/directory'

                    // Example: Execute a command/script
                    //sshScript remote: remote, script: 'bash /path/to/remote/directory/deploy.sh'
					sshScript remote: remote, script: 'bash pwd'
                }
            }
        }*/
    }
}
