package inden

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import grails.converters.JSON

@Secured(['ROLE_USER', 'ROLE_ADMIN', 'ROLE_EDPHO'])
class TindenBarangController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService

    def index() {
        // println("========================= currentUser ==============================")
        // println(springSecurityService.currentUser as JSON)
        // println("========================= currentUser ==============================")

        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tindenBarangInstanceList: TindenBarang.list(params), tindenBarangInstanceTotal: TindenBarang.count()]
    }

    def create() {
        [tindenBarangInstance: new TindenBarang(params)]
    }

    def save() {
        def tindenBarangInstance = new TindenBarang(params)

        def statusRequest = MstatusInden.findByStatus('REQUEST')
        def currUser = springSecurityService.currentUser

        tindenBarangInstance.pembuat = currUser
        tindenBarangInstance.status = statusRequest
        def newHistory = new ThistoryInden(
            status: statusRequest, 
            pembuat: currUser, 
            memo: params.memo,
            tanggalBuat: new Date()
        )
        tindenBarangInstance.addToHistory(newHistory)


        if (!tindenBarangInstance.save(flush: true)) {
            render(view: "create", model: [tindenBarangInstance: tindenBarangInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), tindenBarangInstance.id])
        redirect(action: "show", id: tindenBarangInstance.id)
    }

    def show(Long id) {
        def tindenBarangInstance = TindenBarang.get(id)

        // tindenBarangInstance.history.sort { it.tanggalBuat }

        if (!tindenBarangInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), id])
            redirect(action: "list")
            return
        }

        [tindenBarangInstance: tindenBarangInstance]
    }

    def edit(Long id) {
        def tindenBarangInstance = TindenBarang.get(id)
        if (!tindenBarangInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), id])
            redirect(action: "list")
            return
        }

        [tindenBarangInstance: tindenBarangInstance]
    }

    def update(Long id, Long version) {
        def tindenBarangInstance = TindenBarang.get(id)
        if (!tindenBarangInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tindenBarangInstance.version > version) {
                tindenBarangInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tindenBarang.label', default: 'TindenBarang')] as Object[],
                          "Another user has updated this TindenBarang while you were editing")
                render(view: "edit", model: [tindenBarangInstance: tindenBarangInstance])
                return
            }
        }

        tindenBarangInstance.properties = params

        if (!tindenBarangInstance.save(flush: true)) {
            render(view: "edit", model: [tindenBarangInstance: tindenBarangInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), tindenBarangInstance.id])
        redirect(action: "show", id: tindenBarangInstance.id)
    }

    def delete(Long id) {
        def tindenBarangInstance = TindenBarang.get(id)
        if (!tindenBarangInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), id])
            redirect(action: "list")
            return
        }

        try {
            tindenBarangInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), id])
            redirect(action: "show", id: id)
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDPHO'])
    def propose() {
        def tempList = TindenBarang.createCriteria().list {
            eq("status", MstatusInden.findByStatus("REQUEST"))
        }
        [tindenBarangInstanceList: tempList, tindenBarangInstanceTotal: TindenBarang.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDPHO'])
    def savePropose() {
        Date sekarang = new Date()

        def newHistory = new ThistoryInden(
            status: MstatusInden.findByStatus('PROPOSED'), 
            pembuat: springSecurityService.currentUser, 
            memo: '',
            tanggalBuat: sekarang
        )

        int jumBarang = params.barangCount

        for (i in 0..jumBarang) {
            if (params.arrbarang."${i}") {
                // println("id yg dipilih : " + params.arrbarang."${i}")

                def tindenBarangInstance = TindenBarang.get(params.arrbarang."${i}")
                if (!tindenBarangInstance) {
                }
                tindenBarangInstance.status = MstatusInden.findByStatus('PROPOSED')
                tindenBarangInstance.addToHistory(newHistory)
                tindenBarangInstance.save(flush:true)

            }
            else {

            }
        }

        redirect(action:'list')
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDPHO'])
    def reject(Long id) {
        def tindenBarangInstance = TindenBarang.get(id)
        if (!tindenBarangInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), id])
            redirect(action: "list")
            return
        }

        [tindenBarangInstance: tindenBarangInstance]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDPHO'])
    def saveReject(Long id, Long version) {
        def tindenBarangInstance = TindenBarang.get(id)
        if (!tindenBarangInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tindenBarangInstance.version > version) {
                tindenBarangInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tindenBarang.label', default: 'TindenBarang')] as Object[],
                          "Another user has updated this TindenBarang while you were editing")
                render(view: "reject", model: [tindenBarangInstance: tindenBarangInstance])
                return
            }
        }

        // tindenBarangInstance.properties = params
        def statusRequest = MstatusInden.findByStatus('REJECT')
        def currUser = springSecurityService.currentUser
        tindenBarangInstance.status = statusRequest
        def newHistory = new ThistoryInden(
            status: statusRequest, 
            pembuat: currUser, 
            memo: params.memo,
            tanggalBuat: new Date()
        )
        tindenBarangInstance.addToHistory(newHistory)


        if (!tindenBarangInstance.save(flush: true)) {
            render(view: "reject", model: [tindenBarangInstance: tindenBarangInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), tindenBarangInstance.id])
        redirect(action: "show", id: tindenBarangInstance.id)
    }

    def receive() {
        def tempList = TindenBarang.createCriteria().list {
            eq("status", MstatusInden.findByStatus("PROPOSED"))
        }
        [tindenBarangInstanceList: tempList, tindenBarangInstanceTotal: TindenBarang.count()]
    }
    def saveReceive() {
        Date sekarang = new Date()

        def newHistory = new ThistoryInden(
            status: MstatusInden.findByStatus('RECEIVED'), 
            pembuat: springSecurityService.currentUser, 
            memo: '',
            tanggalBuat: sekarang
        )

        int jumBarang = params.barangCount

        for (i in 0..jumBarang) {
            if (params.arrbarang."${i}") {
                // println("id yg dipilih : " + params.arrbarang."${i}")

                def tindenBarangInstance = TindenBarang.get(params.arrbarang."${i}")
                if (!tindenBarangInstance) {
                }
                tindenBarangInstance.status = MstatusInden.findByStatus('RECEIVED')
                tindenBarangInstance.addToHistory(newHistory)
                tindenBarangInstance.save(flush:true)

            }
            else {

            }
        }

        redirect(action:'list')
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDPHO'])
    def editStatus(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tindenBarangInstanceList: TindenBarang.list(params), tindenBarangInstanceTotal: TindenBarang.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_EDPHO'])
    def saveEditStatus() {

        def selectedStatus = MstatusInden.get(params?.status?.id)
        if (!selectedStatus) {
            flash.message = "Status not found with id : ${params?.status?.id}"
            redirect(action: "editStatus")
            return
        }

        Date sekarang = new Date()

        def newHistory = new ThistoryInden(
            status: selectedStatus, 
            pembuat: springSecurityService.currentUser, 
            memo: '',
            tanggalBuat: sekarang
        )

        int jumBarang = params.barangCount

        for (i in 0..jumBarang) {
            if (params.arrbarang."${i}") {
                // println("id yg dipilih : " + params.arrbarang."${i}")

                def tindenBarangInstance = TindenBarang.get(params.arrbarang."${i}")
                if (!tindenBarangInstance) {
                }
                tindenBarangInstance.status = newHistory.status
                tindenBarangInstance.addToHistory(newHistory)
                tindenBarangInstance.save(flush:true)

            }
            else {

            }
        }

        redirect(action:'list')
    }
}
