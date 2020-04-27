package goalvent;

import java.util.Comparator;

public class EventComparator implements Comparator<EventDTO> {

	@Override
	public int compare(EventDTO o1, EventDTO o2) {
		if(o1.getNum() > o2.getNum()) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
