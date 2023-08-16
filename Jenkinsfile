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

       stage('build') {
            steps {
                sh 'npm run ng build'
            }
        }


        stage("Sonarqube Analysis") {
          def scannerHome = tool 'SonarScanner';
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'sonarqube') {
                      sh "${scannerHome}/bin/sonar-scanner"
                        
                    }
                }
            }

        }

       






        

      
        }
        
}
