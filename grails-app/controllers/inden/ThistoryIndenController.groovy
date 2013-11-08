package inden

import org.springframework.dao.DataIntegrityViolationException

class ThistoryIndenController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [thistoryIndenInstanceList: ThistoryInden.list(params), thistoryIndenInstanceTotal: ThistoryInden.count()]
    }

    def create() {
        [thistoryIndenInstance: new ThistoryInden(params)]
    }

    def save() {
        def thistoryIndenInstance = new ThistoryInden(params)
        if (!thistoryIndenInstance.save(flush: true)) {
            render(view: "create", model: [thistoryIndenInstance: thistoryIndenInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'thistoryInden.label', default: 'ThistoryInden'), thistoryIndenInstance.id])
        redirect(action: "show", id: thistoryIndenInstance.id)
    }

    def show(Long id) {
        def thistoryIndenInstance = ThistoryInden.get(id)
        if (!thistoryIndenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thistoryInden.label', default: 'ThistoryInden'), id])
            redirect(action: "list")
            return
        }

        [thistoryIndenInstance: thistoryIndenInstance]
    }

    def edit(Long id) {
        def thistoryIndenInstance = ThistoryInden.get(id)
        if (!thistoryIndenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thistoryInden.label', default: 'ThistoryInden'), id])
            redirect(action: "list")
            return
        }

        [thistoryIndenInstance: thistoryIndenInstance]
    }

    def update(Long id, Long version) {
        def thistoryIndenInstance = ThistoryInden.get(id)
        if (!thistoryIndenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thistoryInden.label', default: 'ThistoryInden'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (thistoryIndenInstance.version > version) {
                thistoryIndenInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'thistoryInden.label', default: 'ThistoryInden')] as Object[],
                          "Another user has updated this ThistoryInden while you were editing")
                render(view: "edit", model: [thistoryIndenInstance: thistoryIndenInstance])
                return
            }
        }

        thistoryIndenInstance.properties = params

        if (!thistoryIndenInstance.save(flush: true)) {
            render(view: "edit", model: [thistoryIndenInstance: thistoryIndenInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'thistoryInden.label', default: 'ThistoryInden'), thistoryIndenInstance.id])
        redirect(action: "show", id: thistoryIndenInstance.id)
    }

    def delete(Long id) {
        def thistoryIndenInstance = ThistoryInden.get(id)
        if (!thistoryIndenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thistoryInden.label', default: 'ThistoryInden'), id])
            redirect(action: "list")
            return
        }

        try {
            thistoryIndenInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'thistoryInden.label', default: 'ThistoryInden'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'thistoryInden.label', default: 'ThistoryInden'), id])
            redirect(action: "show", id: id)
        }
    }
}
