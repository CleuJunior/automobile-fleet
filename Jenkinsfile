pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/CleuJunior/Teste-Desenvolvedor-Java.git'
            }
        }
        stage ('Build Image') {
            steps {
                echo 'Iniciando a pipeline'
                sh 'java --version'
                // dockerapp = docker.build("fleetautomobile/api", '-f ./Dockerfile')
            }
        }
    }
}