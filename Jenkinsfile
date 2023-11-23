pipeline {
    agent any
    tools{
        maven 'Maven_3.9.5'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Deploy') {
            steps {
                script {
                    // Identify changed microservices
                    // changedMicroservices = findChanges(includePaths: '**/pom.xml')

                    // Initialize file system watcher
                        watcher = new FileWatcher('**/*.pom')

                        // Start monitoring for changes
                        watcher.start()

                        // Wait for changes to occur
                        watcher.waitUntilChanges()

                        // Identify microservices with changed POM files
                        changedMicroservices = []
                        for (file in watcher.getChangedFiles()) {
                            microservice = file.getName().split('/')[0]
                            changedMicroservices.add(microservice)
                        }
                      }

                    // Build and deploy changed microservices
                    for (microservice in changedMicroservices) {
                        stage(microservice) {
                            steps {
                                // sh "mvn clean package -Dspring.profiles=${microservice}"
                                sh "mvn clean install"
                                buildDockerImage(image: microservice, dockerfile: "${microservice}/Dockerfile")
                                sh "docker tag ${microservice}:latest localhost:${getBasePort(microservice)}/${microservice}:latest"

                                // Remove previous image or container for the changed microservice
                                sh "docker rm -f ${microservice}"
                                sh "docker rmi ${microservice}:latest"

                                // Run Docker container for the changed microservice
                                sh "docker run -d -p ${getBasePort(microservice)}:${getBasePort(microservice)} localhost:${getBasePort(microservice)}/${microservice}:latest"
                            }
                        }
                    // }
                }
            }
        }
    }
}

def getBasePort(microservice) {
  switch (microservice) {
    case 'ApiGateway-Service': return 8181
    case 'Discovery-Server': return 8182
    case 'InventoryService': return 8183
    case 'OrderService': return 8184
    case 'ProductService': return 8185
    // default: return 8080 // Add default port assignment if necessary
  }
}