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
                    def artifactVersion = '1.0.0' 
                    def artifactFolder = "dist/*-${artifactVersion}"
                    
                    // Deploy artifact files to Nexus3 repository
                    withCredentials(credentialsId: 'nexus') {
                        sh "curl --upload-file ${artifactFolder}/* ${NEXUS_URL}"
                    }
                }
            }
        }



      stage('docker build'){
            
            steps{
                
                script{

                    docker.build("${JOB_NAME}:v1.${env.BUILD_ID}", "-f Dockerfile .")
                    //sh 'docker image build -f Dockerfile -t ${JOB_NAME}:v1.${BUILD_ID}'
                    sh 'docker image tag $JOB_NAME:v1.$BUILD_ID oussemabouaziz/$JOB_NAME:v1.$BUILD_ID'
                    
                }
            }
        }


         stage('docker push'){
            
            steps{
                
                script{
                   
                    withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhub')]) {

                        sh 'docker login -u oussemabouaziz -p ${dockerhub} '
                        sh 'docker image push oussemabouaziz/$JOB_NAME:v1.$BUILD_ID'
                        
    
                    }
                }
            }
        }





        

      
        }
        
}
