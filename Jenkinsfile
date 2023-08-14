pipeline{
    agent any
    
    
    stages {
        
        stage('Git Checkout'){
            
            steps{
                
                script{
                    
                    git branch: 'angular', credentialsId: 'github', url: 'https://github.com/ousamabouaziz/Angular_CRUD.git'
                }
            }
        }


        stage('Install Dependencies') {
            steps {
                sh 'npm install'
            }
        }

        stage('test') {
            steps {
                sh 'npm test'
            }
        }

        stage('Run build') {
            steps {
                sh 'ng build'
            }
        }

        
        


        stage("Sonarqube Analysis") {
            steps {
                script {
                    
                    withSonarQubeEnv(credentialsId: 'sonarqube') {
                        sh "npm run sonar"
                    }
                }
            }

        }

       






        

      
        }
        
}
