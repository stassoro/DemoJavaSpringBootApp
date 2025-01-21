pipeline {
    agent any
    options {
        skipDefaultCheckout()
    }
    parameters {
        string(name: 'VERSION', defaultValue: '1.0.0', description: 'pass the version in the following format x.x.x')
    }
    environment {
        REGISTRY_NAME = "acr name" // todo
        IMAGE_NAME = "main-app"
    }
    stages {
        stage('Build App') {
            agent {
                docker {
                    image 'maven:3.8.3-openjdk-17'
                }
            }
            steps {
                withMaven(options: [artifactsPublisher(archiveFilesDisabled: true)]) {
                    sh 'mvn clean install'
                }
            }
            post {
                success {
                    archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
                }
            }
        }
        stage('Build Image') {
            when {
                branch 'main'
            }
            steps {
                sh "docker build -t main-app --build-arg APP_VERSION=${VERSION}.${BUILD_NUMBER} -f docker/main-app.dockerfile ."
            }
        }
        stage('Push Image') {
            when {
                branch 'main'
            }
            steps {
                sh "docker tag main-app:latest main-app:${VERSION}.${BUILD_NUMBER}"
                sh "docker push ${IMAGE_NAME}:${VERSION}.${BUILD_NUMBER}"
                sh "docker push ${IMAGE_NAME}:latest"
            }
        }
    }
}