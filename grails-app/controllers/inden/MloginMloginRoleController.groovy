package inden

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class MloginMloginRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [mloginMloginRoleInstanceList: MloginMloginRole.list(params), mloginMloginRoleInstanceTotal: MloginMloginRole.count()]
    }

    def create() {
        [mloginMloginRoleInstance: new MloginMloginRole(params)]
    }

    def save() {
        def mloginMloginRoleInstance = new MloginMloginRole(params)
        if (!mloginMloginRoleInstance.save(flush: true)) {
            render(view: "create", model: [mloginMloginRoleInstance: mloginMloginRoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole'), mloginMloginRoleInstance.id])
        redirect(action: "show", id: mloginMloginRoleInstance.id)
    }

    def show(Long id) {
        def mloginMloginRoleInstance = MloginMloginRole.get(id)
        if (!mloginMloginRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole'), id])
            redirect(action: "list")
            return
        }

        [mloginMloginRoleInstance: mloginMloginRoleInstance]
    }

    def edit(Long id) {
        def mloginMloginRoleInstance = MloginMloginRole.get(id)
        if (!mloginMloginRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole'), id])
            redirect(action: "list")
            return
        }

        [mloginMloginRoleInstance: mloginMloginRoleInstance]
    }

    def update(Long id, Long version) {
        def mloginMloginRoleInstance = MloginMloginRole.get(id)
        if (!mloginMloginRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (mloginMloginRoleInstance.version > version) {
                mloginMloginRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole')] as Object[],
                          "Another user has updated this MloginMloginRole while you were editing")
                render(view: "edit", model: [mloginMloginRoleInstance: mloginMloginRoleInstance])
                return
            }
        }

        mloginMloginRoleInstance.properties = params

        if (!mloginMloginRoleInstance.save(flush: true)) {
            render(view: "edit", model: [mloginMloginRoleInstance: mloginMloginRoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole'), mloginMloginRoleInstance.id])
        redirect(action: "show", id: mloginMloginRoleInstance.id)
    }

    def delete(Long id) {
        def mloginMloginRoleInstance = MloginMloginRole.get(id)
        if (!mloginMloginRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole'), id])
            redirect(action: "list")
            return
        }

        try {
            mloginMloginRoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mloginMloginRole.label', default: 'MloginMloginRole'), id])
            redirect(action: "show", id: id)
        }
    }
}
