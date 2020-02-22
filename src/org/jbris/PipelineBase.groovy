package org.jbris;

abstract class PipelineBase implements Serializable {
    
    protected steps
    protected env 
    protected currentBuild

    PipelineBase(steps) {
        this.steps = steps
        this.env = steps.env
        this.currentBuild = steps.currentBuild
    }
    
    def sh(String command) {
        steps.sh command
    }
}
