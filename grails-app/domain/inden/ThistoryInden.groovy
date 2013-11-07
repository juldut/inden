package inden

import java.text.SimpleDateFormat

class ThistoryInden {

	MstatusInden status
	Date tanggalBuat
	String memo
	Mlogin pembuat

	static mapping = {
		// memo type:'text'
	}

    static constraints = {
    }

    String toString() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy H:m:s")
    	return("${status.status} By ${pembuat.username} - ${sdf.format(tanggalBuat)}")
    }
}
