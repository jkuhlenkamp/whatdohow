package whatdohow

import org.springframework.dao.DataIntegrityViolationException

class StatementController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [statementInstanceList: Statement.list(params), statementInstanceTotal: Statement.count()]
    }

    def create() {
        [statementInstance: new Statement(params)]
    }

    def save() {
        def statementInstance = new Statement(params)
        if (!statementInstance.save(flush: true)) {
            render(view: "create", model: [statementInstance: statementInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'statement.label', default: 'Statement'), statementInstance.id])
        redirect(action: "show", id: statementInstance.id)
    }

    def show(Long id) {
        def statementInstance = Statement.get(id)
        if (!statementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'statement.label', default: 'Statement'), id])
            redirect(action: "list")
            return
        }

        [statementInstance: statementInstance]
    }

    def edit(Long id) {
        def statementInstance = Statement.get(id)
        if (!statementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'statement.label', default: 'Statement'), id])
            redirect(action: "list")
            return
        }

        [statementInstance: statementInstance]
    }

    def update(Long id, Long version) {
        def statementInstance = Statement.get(id)
        if (!statementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'statement.label', default: 'Statement'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (statementInstance.version > version) {
                statementInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'statement.label', default: 'Statement')] as Object[],
                          "Another user has updated this Statement while you were editing")
                render(view: "edit", model: [statementInstance: statementInstance])
                return
            }
        }

        statementInstance.properties = params

        if (!statementInstance.save(flush: true)) {
            render(view: "edit", model: [statementInstance: statementInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'statement.label', default: 'Statement'), statementInstance.id])
        redirect(action: "show", id: statementInstance.id)
    }

    def delete(Long id) {
        def statementInstance = Statement.get(id)
        if (!statementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'statement.label', default: 'Statement'), id])
            redirect(action: "list")
            return
        }

        try {
            statementInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'statement.label', default: 'Statement'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'statement.label', default: 'Statement'), id])
            redirect(action: "show", id: id)
        }
    }
}
