pipeline {
    agent any
    tools {
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
                    def changedMicroservices = findChangedMicroservices()

                    // Build and deploy changed microservices
                    changedMicroservices.each { microservice ->
                        stage("Build and Deploy ${microservice}") {
                            steps {
                                echo "Building Docker image for ${microservice}"
                                sh "mvn clean install"
                                def dockerImageTag = "${microservice}:${BUILD_NUMBER}"

                                // Build Docker image
                                sh "docker build -t ${dockerImageTag} -f ${microservice}/Dockerfile ."

                                // Remove previous container if it exists
                                sh "docker rm -f ${microservice}"

                                // Remove previous image if it exists
                                sh "docker rmi ${dockerImageTag}"

                                // Tag Docker image
                                sh "docker tag ${dockerImageTag} localhost:${getBasePort(microservice)}/${dockerImageTag}"

                                // Run Docker container
                                sh "docker run -d -p ${getBasePort(microservice)}:${getBasePort(microservice)} localhost:${getBasePort(microservice)}/${dockerImageTag}"
                            }
                        }
                    }
                }
            }
        }
    }
}

def findChangedMicroservices() {
    def changedMicroservices = []
    echo "Checking for changed microservices..."

    for (changeLogSet in currentBuild.changeSets) {
        //echo "Analyzing changeset..."
        for (entry in changeLogSet.items) {
            def affectedFiles = entry.affectedFiles.collect { it.path }
            //echo "Affected files: ${affectedFiles.join(', ')}"

            affectedFiles.each { file ->
                def matcher = file =~ /^(.+?)\/src\//
                if (matcher) {
                    def microserviceName = matcher[0][1]
                    //echo "Detected change in microservice: ${microserviceName}"
                    changedMicroservices.add(microserviceName)
                }
            }
        }
    }

    //echo "Changed microservices: ${changedMicroservices}"
    return changedMicroservices.unique()
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