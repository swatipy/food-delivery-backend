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

        stage('Build Docker Image') {
            steps {
                script {
                    env.VERSION = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()

                    sh """
                        docker build -t ${IMAGE_NAME}:${env.VERSION} .
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
    }
}