package whatdohow

import grails.converters.JSON
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
	
	def getflexible(){
				
		String w = params.statewhat
		String d = params.statedo
		String h = params.statehow
		String l = params.location
		
		println "what: " + w + ", do: " + d + ", how: " + h + ", location: " + l
		
		def criteria = [w, d, h, l]
		def combo = ""
		
		println criteria
		println combo
		
		// Build combo string to define search criteria
		for(int i = 0; i < criteria.size(); i++){
			if( criteria[i] ) {
				combo += 1
			} else {
				combo += 0
			}
		}
		
		println combo
		
		// Search statements according to combo string
		def statements = []
		switch ( combo ) {
			
			case "0000":
				statements = []
				break
				
			case "1000":
				statements += Statement.findByStatewhatLike( w + "%" )
				break
				
			case "0100":
				statements += Statement.findByStatedoLike( d + "%" )
				break
				
			case "0010":
				statements += Statement.findByStatehowLike( h + "%" )
				break
			case "0001":
				statements += Statement.findByLocationLike( l + "%" )
				break
			
			case "1100":
				statements += Statement.findByStatewhatLikeAndStatedoLike( w + "%", d + "%" )
				break
				
			case "0110":
				statements += Statement.findByStatedoLikeAndStatehowLike( d + "%", h + "%" )
				break
				
			case "0011":
				statements += Statement.findByStatehowLikeAndLocationLike( h + "%", l + "%")
				break
			
			case "1110":
				statements += Statement.findByStatewhatLikeAndStatedoLikeAndStatehowLike( w + "%", d + "%", h + "%")
				break
				
			case "0111":
				statements += Statement.findByStatedoLikeAndStatehowLikeAndLocation( d + "%", h + "%", l + "%")
				break
				
			case "1111":
				statements += Statement.findByStatewhatLikeAndStatedoLikeAndStatehowLikeAndLocation( w + "%", d + "%", h + "%", l + "%")
				break
				
			default:
				println "No match in criteria selector!"
				
		}
		
		/**
		def result = []
		
		for( s in statements) {
			def r = []
			s.statehow
		}
		*/
		
		render statements as JSON
	}
}
