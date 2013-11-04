import inden.Mlogin
import inden.MloginRole
import inden.MloginMloginRole

import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
    	def newUser = new Mlogin(username:'admin', enabled:true, password:'admin').save(flush:true)
    	def newRole = new MloginRole(authority: 'ROLE_ADMIN').save(flush:true)
    	MloginMloginRole.create newUser, newRole, true

    	println("==================== Mlogin ============================")
    	println(Mlogin.findAll() as JSON)
    	println("==================== Mlogin ============================")

    	println("==================== MloginRole ============================")
    	println(MloginRole.findAll() as JSON)
    	println("==================== MloginRole ============================")

    	println("==================== MloginMloginRole ============================")
    	println(MloginMloginRole.findAll() as JSON)
    	println("==================== MloginMloginRole ============================")
    }
    def destroy = {
    }
}
