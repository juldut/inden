package inden

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN', 'ROLE_EDPHO'])
class MstatusIndenController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [mstatusIndenInstanceList: MstatusInden.list(params), mstatusIndenInstanceTotal: MstatusInden.count()]
    }

    def create() {
        [mstatusIndenInstance: new MstatusInden(params)]
    }

    def save() {
        def mstatusIndenInstance = new MstatusInden(params)
        if (!mstatusIndenInstance.save(flush: true)) {
            render(view: "create", model: [mstatusIndenInstance: mstatusIndenInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'mstatusInden.label', default: 'MstatusInden'), mstatusIndenInstance.id])
        redirect(action: "show", id: mstatusIndenInstance.id)
    }

    def show(Long id) {
        def mstatusIndenInstance = MstatusInden.get(id)
        if (!mstatusIndenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstatusInden.label', default: 'MstatusInden'), id])
            redirect(action: "list")
            return
        }

        [mstatusIndenInstance: mstatusIndenInstance]
    }

    def edit(Long id) {
        def mstatusIndenInstance = MstatusInden.get(id)
        if (!mstatusIndenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstatusInden.label', default: 'MstatusInden'), id])
            redirect(action: "list")
            return
        }

        [mstatusIndenInstance: mstatusIndenInstance]
    }

    def update(Long id, Long version) {
        def mstatusIndenInstance = MstatusInden.get(id)
        if (!mstatusIndenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstatusInden.label', default: 'MstatusInden'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (mstatusIndenInstance.version > version) {
                mstatusIndenInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'mstatusInden.label', default: 'MstatusInden')] as Object[],
                          "Another user has updated this MstatusInden while you were editing")
                render(view: "edit", model: [mstatusIndenInstance: mstatusIndenInstance])
                return
            }
        }

        mstatusIndenInstance.properties = params

        if (!mstatusIndenInstance.save(flush: true)) {
            render(view: "edit", model: [mstatusIndenInstance: mstatusIndenInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'mstatusInden.label', default: 'MstatusInden'), mstatusIndenInstance.id])
        redirect(action: "show", id: mstatusIndenInstance.id)
    }

    def delete(Long id) {
        def mstatusIndenInstance = MstatusInden.get(id)
        if (!mstatusIndenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mstatusInden.label', default: 'MstatusInden'), id])
            redirect(action: "list")
            return
        }

        try {
            mstatusIndenInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'mstatusInden.label', default: 'MstatusInden'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mstatusInden.label', default: 'MstatusInden'), id])
            redirect(action: "show", id: id)
        }
    }
}
