package goalvent;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class BoardComparator implements Comparator<BoardDTO> {

	@Override
	public int compare(BoardDTO o1, BoardDTO o2) {
//		return o1.getNum() > o2.getNum() ? 1:0;
		if(o1.getNum() > o2.getNum()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
}
