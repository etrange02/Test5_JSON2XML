package tp5.json2xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMLObject extends XMLValeur {
	
	private List<XMLPaire> paires;
	
	public XMLObject() {
		this.paires = new ArrayList<XMLPaire>();
	}

	@Override
	public String toXML() {
		if (this.paires.size() == 0) {
			return "<object/>";
		} else {
			StringBuffer sb = new StringBuffer("<object>");
			
			Iterator<XMLPaire> iter = this.paires.iterator();
			
			if (iter.hasNext())
				sb.append(iter.next());
			
			sb.append("</object>");
			return sb.toString();
		}
	}

}
