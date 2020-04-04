package goalvent;

import java.util.Vector;

import goalvent_interface.Follow;

public class FollowDAO implements Follow {

	@Override
	public int follow(String id, String follow) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int upFollow(String id, String follow) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<String> follower(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<String> following(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int confirmFollow(String myId, String otherId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
