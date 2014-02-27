package tp5.json2xml;

public class XMLNumber extends XMLValeur {
	
	private int value;

	@Override
	public String toXML() {
		return "<number>" + this.value + "</number>";
	}

}
