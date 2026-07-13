package reader;

import java.util.ArrayList;
import java.util.List;

import model.Devconfig;

public class Reader {
	
	String deviceId;
	String ip;
	int port;
	
	public List<Devconfig> read(String filePath){
		
		List<Devconfig> list = new ArrayList<>();
		
		Devconfig config = new Devconfig(deviceId, ip, port);
		
		list.add(config);
		
		return list;
		
	}

}
