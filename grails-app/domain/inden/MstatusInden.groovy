package inden

class MstatusInden {

	String status

    static constraints = {
    	status blank:false
    }

    String toString() {
    	return(status)
    }
}
