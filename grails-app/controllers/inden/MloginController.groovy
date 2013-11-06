package inden

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class MloginController {
    static scaffold = true
}
