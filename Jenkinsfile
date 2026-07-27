pipeline {
    agent any

    environment {
        IMAGE_NAME = "usmaan12345/springboot-app"
        TAG = "latest"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

       stage('Build JAR') {
    steps {
        sh '''
            chmod +x mvnw
            ./mvnw clean package
        '''
    }
}

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$TAG .'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push $IMAGE_NAME:$TAG'
            }
        }
    }
    stage('Deploy') {
    steps {
        sh '''
        docker pull usmaan12345/springboot-app:latest

        docker stop spring-app || true
        docker rm spring-app || true

        docker run -d \
        --name spring-app \
        --network app-network \
        -p 8081:8080 \
        usmaan12345/springboot-app:latest
        '''
    }
}

    post {
        always {
            cleanWs()
        }
    }
}