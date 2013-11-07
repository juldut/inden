package inden

import grails.converters.JSON

class TindenBarang {

	Mlogin pembuat
	String namaBarang
	String branchShop
	MstatusInden status

	static hasMany = [history : ThistoryInden]

    static constraints = {
    }

    def beforeInsert() {
    	catatHistory()
    }

    def afterUpdate() {
    	// catatHistory()
    }

    def catatHistory() {

    	def newHistory = new ThistoryInden(
            status: this.status, 
            pembuat: this.pembuat, 
            memo: '',
            tanggalBuat: new Date()
        )
        this.addToHistory(newHistory)
        
    }


}
