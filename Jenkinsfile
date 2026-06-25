pipeline {

    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/swatipy/food-delivery-backend.git'
            }
        }


        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }


        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }


        stage('Build Docker Image') {
            steps {
                sh 'docker build -t food-delivery-backend .'
            }
        }

    }
}