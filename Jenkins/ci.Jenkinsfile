pipeline {

  agent {
    node {
      label 'docker'
    }
  }
  options {
    timestamps()
  }
  environment {
    REGISTRY_NAME = "acr name"
    VERSION = readMavenPom().getVersion()
  }
  stages {
    stage('Build') {
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
    stage('Build and Publish Image') {
      when {
        branch 'main'
      }
      steps {
        sh """
          docker build -t ${IMAGE} .
          docker tag ${IMAGE} ${IMAGE}:${VERSION}
          docker push ${IMAGE}:${VERSION}
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