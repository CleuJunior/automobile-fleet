pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Replace the repository URL and credentials
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/main']],
                          userRemoteConfigs: [[url: 'https://github.com/CleuJunior/Teste-Desenvolvedor-Java.git']]])
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }

            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}