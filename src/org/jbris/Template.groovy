package org.jbris;

class Template extends PipelineBase {
    
    Template(steps) {
        super(steps)
    }
    
    def envsubst(String substitutedVars, String template, String out) {
        return sh(" envsubst '${substitutedVars}' < \"${template}\" > \"${out}\" ")
    }

}
