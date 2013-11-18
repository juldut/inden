package inden

import grails.converters.JSON

class TindenBarang {

	Mlogin pembuat
	String namaBarang
	String branchShop
	MstatusInden status

	static hasMany = [history : ThistoryInden]

    SortedSet history 

    static constraints = {
    }

    static mapping = {
    }

   

}
