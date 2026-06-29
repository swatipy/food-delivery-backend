pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/swatipy/food-delivery-backend.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // store VERSION in global env so other stages can use it
                    env.VERSION = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()

                    sh """
                        docker build -t food-delivery-backend:${env.VERSION} .
                        docker tag food-delivery-backend:${env.VERSION} food-delivery-backend:latest
                    """
                }
            }
        }

        stage('Push Image') {
            steps {
                sh """
                    docker push food-delivery-backend:${env.VERSION}
                    docker push food-delivery-backend:latest
                """
            }
        }
    }
}