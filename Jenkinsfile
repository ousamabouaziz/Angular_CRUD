pipeline{

  
   agent any
   
       environment {
        NEXUS_URL = 'http://localhost:8081/repository/angular-release/'
        NEXUS_CREDENTIALS_ID = 'nexus'
    }


  
    
    
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


       stage('Deploy to Nexus3') {
            steps {
                script {
                    def artifactVersion = '0.0.0' 
                    def artifactFolder = "dist/*-${artifactVersion}"
                    
                    // Deploy artifact files to Nexus3 repository
                    withCredentials((credentialsId: NEXUS_CREDENTIALS_ID) {
                        sh "curl --upload-file ${artifactFolder}/* ${NEXUS_URL}"
                    }
                }
            }
        }
    }
}







        

       






        

      
        }
        
}
