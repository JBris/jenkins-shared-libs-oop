package org.jbris;

class File extends PipelineBase {
    
    File(steps) {
        super(steps)
    }
    
    def copy(String file, String destination) {
        return sh("if [ -f \"${file}\" ]; then cp \"${file}\" \"${destination}\"; fi ")
    }

    def move(String file, String destination) {
        return sh("if [ -f \"${file}\" ]; then mv \"${file}\" \"${destination}\"; fi ")
    }

    def remove(String file) {
        return sh("if [ -f \"${file}\" ]; then rm \"${file}\"; fi ")
    }

    def dos2unix(String file) {
        return sh("dos2unix \"${file}\" ")
    }
}
