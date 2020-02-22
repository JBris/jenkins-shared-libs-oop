package org.jbris;

class EnvVars extends PipelineBase {

    EnvVars(steps) {
        super(steps)
    }

    def loadEnvironmentVariables(String path) {
        def props = steps.readProperties  file: path
        def keys = props.keySet()
        for(key in keys) {
            def value = props["${key}"]
            steps.env."${key}" = "${value}"
        }
    }

    def export(String fileName = 'env_file') {
        return sh(" env > \"${fileName}\" ")
    }

    def filteredExport(String filter, String fileName = 'env_file') {
        return sh(""" 
            awk 'END { 
                for (name in ENVIRON) {
                    if ( name ${filter} ) { 
                        print name"="ENVIRON[name];
                    } 
                }
            }' < /dev/null > ${fileName}
        """)
    }
}
