package org.jbris;

class Git extends PipelineBase {

    Git(steps) {
        super(steps)
    }
    
    def branch(String branch) {
        def command =    """
                        if [ \$(git ls-remote --heads origin ${branch} | wc -l) -eq 1 ]; then
                            git checkout ${branch} 
                        else 
                            git checkout -B ${branch} 
                        fi
                        """
        return sh(command)
    }

    def add(String file) {
        return sh("git add ${file}")
    }
    
    def status() {
        return sh("git status")
    }

    def commit(String message) {
        return sh("git commit -am \"${message}\" ")
    }

    def tag(String name, String message) {
        return sh("git tag -a ${name} -m \"${message}\" ")
    }

    def setIdentity(String name, String email) {
        def command = """ 
                    git config user.name '${name}'
                    git config user.email '${email}'
                    """
        return sh(command)
    }

    def push(String branch) {
        return sh("git push origin ${branch} --follow-tags")
    }
    def push(String branch, int loops, int randomWait){
        def command =  """
                    set +e
                    i=1
                    while [[ \$i -le ${loops} ]]
						do 
						    echo \"Push attempt \$i\"
							git fetch origin ${branch}
                       		git merge origin/${branch}
                        	git push origin \\${branch} --follow-tags 
                        	[ \"\$?\" -eq 0 ] && break
                        	sleep \$((RANDOM % ${randomWait}))
						   	let i=i+1
						done
					set -e
                    """
        return sh(command)
    }

    def setCredentials(String credentials) {
        steps.withCredentials([steps.usernamePassword(credentialsId: credentials, usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
            sh("git config --local credential.helper \"!f() { echo username=\$GIT_USERNAME; echo password=\$GIT_PASSWORD; }; f\" ")
        }     
    }

}