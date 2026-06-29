pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def VERSION = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()

                    sh """
                        docker build -t food-delivery-backend:${VERSION} .
                        docker tag food-delivery-backend:${VERSION} food-delivery-backend:latest
                    """
                }
            }
        }

        stage('Push Image') {
            steps {
                sh """
                    docker push food-delivery-backend:${VERSION}
                    docker push food-delivery-backend:latest
                """
            }
        }
    }
}