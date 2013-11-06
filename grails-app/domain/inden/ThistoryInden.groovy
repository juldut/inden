package inden

import java.text.SimpleDateFormat

class ThistoryInden {

	MstatusInden status
	Date tanggalBuat
	Mlogin pembuat

    static constraints = {
    }

    String toString() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy")
    	return("${status.status} By ${pembuat.username} - ${sdf.format(tanggalBuat)}")
    }
}
