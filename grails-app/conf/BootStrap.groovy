import inden.Mlogin
import inden.MloginRole
import inden.MloginMloginRole
import inden.MstatusInden
import inden.TindenBarang
import inden.ThistoryInden

import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
    	def newUser = new Mlogin(username:'admin', enabled:true, password:'1').save(flush:true)
    	def newRole = new MloginRole(authority: 'ROLE_ADMIN').save(flush:true)
    	MloginMloginRole.create newUser, newRole, true

        newUser = new Mlogin(username:'edpho', enabled:true, password:'1').save(flush:true)
        newRole = new MloginRole(authority: 'ROLE_EDPHO').save(flush:true)
        MloginMloginRole.create newUser, newRole, true

        newUser = new Mlogin(username:'edpjbr', enabled:true, password:'1').save(flush:true)
        newRole = new MloginRole(authority: 'ROLE_USER').save(flush:true)
        MloginMloginRole.create newUser, newRole, true

    	// println("==================== Mlogin ============================")
    	// println(Mlogin.findAll() as JSON)
    	// println("==================== Mlogin ============================")

    	// println("==================== MloginRole ============================")
    	// println(MloginRole.findAll() as JSON)
    	// println("==================== MloginRole ============================")

    	// println("==================== MloginMloginRole ============================")
    	// println(MloginMloginRole.findAll() as JSON)
    	// println("==================== MloginMloginRole ============================")

        def statusRequest = new MstatusInden(status: 'REQUEST').save(flush:true)
        def newStatus = new MstatusInden(status: 'PROPOSED').save(flush:true)
        newStatus = new MstatusInden(status: 'REJECT').save(flush:true)
        newStatus = new MstatusInden(status: 'RECEIVED').save(flush:true)

        def newIndenBarang = new TindenBarang()
        newIndenBarang.pembuat = newUser
        newIndenBarang.namaBarang = 'ADAPTOR'
        // newIndenBarang.keterangan = 'POWER LEMAH'
        newIndenBarang.branchShop = '0302'
        newIndenBarang.status = statusRequest
        newIndenBarang.save(flush:true)

        newIndenBarang = new TindenBarang()
        newIndenBarang.pembuat = newUser
        newIndenBarang.namaBarang = 'MOBO 478'
        // newIndenBarang.keterangan = 'MATI TOTAL'
        newIndenBarang.branchShop = '0302'
        newIndenBarang.status = statusRequest
        newIndenBarang = newIndenBarang.save(flush:true)

        def newHistory = new ThistoryInden(
            status: MstatusInden.findByStatus('REJECT'), 
            pembuat: Mlogin.findByUsername('edpho'), 
            memo: 'DIGANTI CPU BARU',
            tanggalBuat: new Date()
        )
        newIndenBarang.addToHistory(newHistory)
        newIndenBarang.status = MstatusInden.findByStatus('REJECT')
        newIndenBarang.save(flush: true)


        newIndenBarang = new TindenBarang()
        newIndenBarang.pembuat = newUser
        newIndenBarang.namaBarang = 'KEYBOARD'
        // newIndenBarang.keterangan = 'TOMBOL ASDF TIDAK BISA'
        newIndenBarang.branchShop = '0302'
        newIndenBarang.status = statusRequest
        newIndenBarang.save(flush:true)


    }
    def destroy = {
    }
}
