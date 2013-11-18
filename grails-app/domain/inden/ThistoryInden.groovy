package inden

import java.text.SimpleDateFormat

class ThistoryInden implements Comparable {

	MstatusInden status
	Date tanggalBuat
	String memo
	Mlogin pembuat

	static mapping = {
		// memo type:'text'
        // sort "tanggalBuat"
	}



    static constraints = {
    }

    String toString() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy H:m:s")
    	return("${status.status} By ${pembuat.username} - ${sdf.format(tanggalBuat)}")
    }

    String toStringTanggalBuat() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy H:mm:s")
    	return(sdf.format(tanggalBuat))
    }

    int compareTo(obj) {
        tanggalBuat.compareTo(obj.tanggalBuat)
    }
}
