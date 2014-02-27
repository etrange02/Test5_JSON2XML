package tp5.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONArray extends JSONValeur {
	
	private List<JSONElements> array;
	
	public JSONArray() {
		this.array = new ArrayList<JSONElements>();
	}

	@Override
	public String toXML() {
		if (this.array.size() == 0) {
			return "<array/>";
		} else {
			StringBuffer sb = new StringBuffer("<array>");
			
			Iterator<JSONElements> iter = this.array.iterator();
			
			if (iter.hasNext())
				sb.append(iter.next().toXML());
			while (iter.hasNext()) {
				sb.append(","); /////// Attention, je n'en suis pas sûr
				sb.append(iter.next().toXML());
			}
			sb.append("</array>");
			return sb.toString();
		}
	}

}
