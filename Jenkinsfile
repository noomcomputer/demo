pipeline {
    agent any

    tools {
        maven 'Default'
        dockerTool 'Default'
    }

    environment {
        VERSION = '1.0.1'
        dockerImage = ''
        // Define your Docker Hub username/repo here
        DOCKERHUB_REPO = "noomcomputer/demo" 
        // The ID of the Jenkins credential you created in Step 1
        DOCKERHUB_CREDENTIAL_ID = "dockerhub-login"
		DOCKERHUB_ACCESS_TOKEN = "dckr_pat_1HfmqKG6CNfx4TTJsejQGLlyX7g"
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
        /*stage('Build image to local repo') {
            steps {
                script {
                    //dockerImage = docker.build("local/my-app:${VERSION}")
					//dockerImage = docker.build("noomcomputer/repository:${VERSION}")
					dockerImage = docker.build("demo:${VERSION}")
                }
            }
        }*/
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
        /*stage('Push image') {
            steps {
                script {
                    // Use the withRegistry block to authenticate before building/pushing
                    //docker.withRegistry('', '${DOCKERHUB_ACCESS_TOKEN}') {
					docker.withRegistry('https://registry-1.docker.io/', 'dckr_pat_1HfmqKG6CNfx4TTJsejQGLlyX7g') {
                        def customImage = docker.build("${DOCKERHUB_REPO}:${VERSION}")
                        
                        // Push the image to Docker Hub
                        customImage.push()
                        //customImage.push("latest") // Optional: push with 'latest' tag
                    }
                }
            }
        }*/
        stage('Push image') {
            steps {
				// The access token is securely passed via the environment variable
				//sh "docker login -u $DOCKERHUB_USR -p $DOCKERHUB_PSW"
				sh 'echo "${DOCKERHUB_ACCESS_TOKEN}" | docker login --username noomcomputer --password-stdin'

				// Build the Docker image (replace 'youruser/yourrepo:latest' with your details)
				//def imageName = '${DOCKERHUB_REPO}:${VERSION}'
				//sh "docker build -t ${imageName} ."
				sh "docker build -t ${DOCKERHUB_REPO}:${VERSION} ."

				// Push the image to Docker Hub
				//sh "docker push ${imageName}"
				sh "docker push ${DOCKERHUB_REPO}:${VERSION}"

				// Log out (optional but good practice)
				sh "docker logout"
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
        /*stage('Deploy to Remote Server') {
            steps {
                // Example of deploying using SSH to a remote host
                // Requires Jenkins SSH plugin and pre-configured SSH credentials
                //sh 'ssh -o StrictHostKeyChecking=no user@remote-server-ip "docker pull your-dockerhub-username/your-app-image-name:latest && docker stop your-app-container || true && //docker rm your-app-container || true && docker run -d --name your-app-container -p 8080:8080 your-dockerhub-username/your-app-image-name:latest"'
				//sh "docker stop demo || true && docker rm demo || true && docker run -d --name demo -p 8083:8083 demo:latest"
				//sh 'ssh -o StrictHostKeyChecking=no root@beonesuccess.com "docker stop demo || true && docker rm demo || true && docker run -d --name demo -p 8083:8083 demo:latest"'
				sh 'ssh -l root beonesuccess.com "bash pwd"'
            }
        }*/
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
                    remote.identity = 'your-private-key-content' // Or use identityFile

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
