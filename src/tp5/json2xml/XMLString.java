package tp5.json2xml;

public class XMLString extends XMLValeur {
	
	private String value;

	@Override
	public String toXML() {
		return "<string>" + this.value + "</string>";
	}

}
