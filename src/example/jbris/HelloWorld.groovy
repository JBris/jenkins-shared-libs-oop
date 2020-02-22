package example.jbris;

import org.jbris.PipelineBase

class HelloWorld extends PipelineBase {

    HelloWorld(steps) {
        super(steps)
    }
    
    def hello(String name) {
        sh("echo hello ${name}")
    }
}


