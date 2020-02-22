package org.jbris;

class Hook extends PipelineBase {

    Hook(steps) {
        super(steps)
    }

    def createPreHookProperties(String workspace, String step) {
        return sh("touch ${workspace}/pre_hook_${step}.properties")
    }

    def callPreHook(String workspace, String step) {
        return sh("[[ -f \"${workspace}/pre_hook_${step}.sh\" ]] && \"./${workspace}/pre_hook_${step}.sh\" ")
    }

    def viewPreHookProperties(String workspace, String step) {
        return sh("cat \"${workspace}/pre_hook_${step}.properties\" ")
    }

    def createPostHookProperties(String workspace, String step) {
        return sh("touch ${workspace}/post_hook_${step}.properties")
    }

    def callPostHook(String workspace, String step) {
        return sh("[[ -f \"${workspace}/post_hook_${step}.sh\" ]] && \"./${workspace}/post_hook_${step}.sh\" ")
    }

    def viewPostHookProperties(String workspace, String step) {
        return sh("cat \"${workspace}/post_hook_${step}.properties\" ")
    }
}
