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
//    IMAGE = readMavenPom().getArtifactId()
//    VERSION = readMavenPom().getVersion()
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
          sh 'mvn clean compile'
        }
      }
      post {
        success {
          archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
        }
      }
    }
    stage('Test') {
      when {
        anyOf {
          branch 'PR-*'
          branch 'master'
        }
      }
      steps {
        script {

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