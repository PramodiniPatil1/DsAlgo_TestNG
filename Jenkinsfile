pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'     // Change to your configured Maven name in Jenkins
        jdk 'Java 21'           // Change to your configured JDK name in Jenkins
    }

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/PramodiniPatil1/DsAlgo_TestNG.git',
                    credentialsId: 'your-jenkins-credential-id'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Allure Report Generation') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
