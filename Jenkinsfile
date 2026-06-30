pipeline {
    agent any

    environment {
        IMAGE_NAME = "swatigup/food-delivery-backend"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/swatipy/food-delivery-backend.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    env.VERSION = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()

                    sh """
                        docker build --platform linux/amd64 \
                            -t ${IMAGE_NAME}:${env.VERSION} .

                        docker tag ${IMAGE_NAME}:${env.VERSION} ${IMAGE_NAME}:latest
                    """
                }
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Push Image') {
            steps {
                sh """
                    docker push ${IMAGE_NAME}:${env.VERSION}
                    docker push ${IMAGE_NAME}:latest
                """
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(credentials: ['ec2-ssh-key']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no ec2-user@3.15.207.131 '
                            docker pull ${IMAGE_NAME}:${env.VERSION}

                            docker stop food-delivery-backend || true
                            docker rm food-delivery-backend || true

                            docker run -d \
                              --name food-delivery-backend \
                              -p 8080:8080 \
                              ${IMAGE_NAME}:${env.VERSION}
                        '
                    """
                }
            }
        }
    }
}