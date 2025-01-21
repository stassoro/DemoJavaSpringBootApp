pipeline {
  agent {
    node {
      label 'docker'
    }
  }
  options {
    timestamps()
  }
  parameters {
    string(name: 'VERSION', defaultValue: '1.0.0', description: 'pass the version in the following format x.x.x')
  }
  environment {
    REGISTRY_NAME = "acr name" // todo
  }
  stages {
    stage('Build App') {
      agent {
        docker {
          image 'maven:3.8.3-openjdk-17'
        }
      }
      steps {
        withMaven() {
          sh 'mvn clean install'
        }
      }
      post {
        success {
          archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
        }
      }
    }
    stage('Build and push Image') {
      when {
        branch 'main'
      }
      steps {
        sh """
          docker build -t main-app --build-arg APP_VERSION=${VERSION}.${BUILD_NUMBER} -f docker/main-app.dockerfile .
          docker tag main-app:latest main-app:${VERSION}.${BUILD_NUMBER}
          docker push ${IMAGE}:${VERSION}.${BUILD_NUMBER}
          docker push ${IMAGE}:latest
        """
      }
    }
  }
  post {
    failure {
      mail to: 'test@organization.com',
          subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
          body: "Something is wrong with ${env.BUILD_URL}"
    }
  }
}