package inden

import grails.converters.JSON

class TindenBarang {

	Mlogin pembuat
	String namaBarang
	String keterangan
	String branchShop
	MstatusInden status

	static hasMany = [history : ThistoryInden]

	static mapping = {
		keterangan type:'text'
	}

    static constraints = {
    }

    def beforeInsert() {
    	catatHistory()
    }

    def afterUpdate() {
    	// catatHistory()
    }

    def catatHistory() {

    	def newHistory = new ThistoryInden(status: this.status, pembuat: this.pembuat, tanggalBuat: new Date())
        this.addToHistory(newHistory)
        
    }
}
