package tp5.json2xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMLObjet extends XMLValeur {
	
	private List<XMLExpression> paires;
	
	public XMLObjet() {
		this.paires = new ArrayList<XMLExpression>();
	}
	
	public void add(XMLExpression e) {
		this.paires.add(e);
	}

	@Override
	public String toXML() {
		if (this.paires.size() == 0) {
			return "<object/>";
		} else {
			StringBuffer sb = new StringBuffer("<object>");
			
			Iterator<XMLExpression> iter = this.paires.iterator();
			
			while (iter.hasNext())
				sb.append(iter.next());
			
			sb.append("</object>");
			return sb.toString();
		}
	}

}
