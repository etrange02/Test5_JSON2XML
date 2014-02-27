package tp5.json2xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMLArray extends XMLValeur {
	
	private List<XMLExpression> array;
	
	public XMLArray() {
		this.array = new ArrayList<XMLExpression>();
	}
	
	public void add(XMLExpression e) {
		this.array.add(e);
	}

	@Override
	public String toXML() {
		if (this.array.size() == 0) {
			return "<array/>";
		} else {
			StringBuffer sb = new StringBuffer("<array>");
			
			Iterator<XMLExpression> iter = this.array.iterator();
			while (iter.hasNext()) {
				sb.append(iter.next().toXML());
			}
			sb.append("</array>");
			return sb.toString();
		}
	}

}
