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

        tindenBarangInstance.pembuat = springSecurityService.currentUser
        tindenBarangInstance.status = MstatusInden.findByStatus('REQUEST')

        if (!tindenBarangInstance.save(flush: true)) {
            render(view: "create", model: [tindenBarangInstance: tindenBarangInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tindenBarang.label', default: 'TindenBarang'), tindenBarangInstance.id])
        redirect(action: "show", id: tindenBarangInstance.id)
    }

    def show(Long id) {
        def tindenBarangInstance = TindenBarang.get(id)
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
}
