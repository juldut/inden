package inden

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class MloginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [mloginInstanceList: Mlogin.list(params), mloginInstanceTotal: Mlogin.count()]
    }

    def create() {
        [mloginInstance: new Mlogin(params)]
    }

    def save() {
        def mloginInstance = new Mlogin(params)
        if (!mloginInstance.save(flush: true)) {
            render(view: "create", model: [mloginInstance: mloginInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'mlogin.label', default: 'Mlogin'), mloginInstance.id])
        redirect(action: "show", id: mloginInstance.id)
    }

    def show(Long id) {
        def mloginInstance = Mlogin.get(id)
        if (!mloginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mlogin.label', default: 'Mlogin'), id])
            redirect(action: "list")
            return
        }

        [mloginInstance: mloginInstance]
    }

    def edit(Long id) {
        def mloginInstance = Mlogin.get(id)
        if (!mloginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mlogin.label', default: 'Mlogin'), id])
            redirect(action: "list")
            return
        }

        [mloginInstance: mloginInstance]
    }

    def update(Long id, Long version) {
        def mloginInstance = Mlogin.get(id)
        if (!mloginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mlogin.label', default: 'Mlogin'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (mloginInstance.version > version) {
                mloginInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'mlogin.label', default: 'Mlogin')] as Object[],
                          "Another user has updated this Mlogin while you were editing")
                render(view: "edit", model: [mloginInstance: mloginInstance])
                return
            }
        }

        mloginInstance.properties = params

        if (!mloginInstance.save(flush: true)) {
            render(view: "edit", model: [mloginInstance: mloginInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'mlogin.label', default: 'Mlogin'), mloginInstance.id])
        redirect(action: "show", id: mloginInstance.id)
    }

    def delete(Long id) {
        def mloginInstance = Mlogin.get(id)
        if (!mloginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mlogin.label', default: 'Mlogin'), id])
            redirect(action: "list")
            return
        }

        try {
            mloginInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'mlogin.label', default: 'Mlogin'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mlogin.label', default: 'Mlogin'), id])
            redirect(action: "show", id: id)
        }
    }
}
