package org.jbris;

class Email extends PipelineBase {
    
    Email(steps) {
        super(steps)
    }

    def sendResults(String emailNotificationList) {
        steps.emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}",
            to: emailNotificationList,
            subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
    }
}
