package org.jbris;

class Bats extends PipelineBase {

    Bats(steps) {
        super(steps)
    }

    def test(String testFile, String out) {
        sh ("bats \"${testFile}\" > \"${out}\" ")
    }

    def publishResults(String resultsFile) {
        if (steps.fileExists(resultsFile)) {
            steps.archiveArtifacts artifacts: resultsFile, fingerprint: true
            steps.step([ $class: "TapPublisher", testResults: resultsFile ])
        } 
    }
}