package tp5.json2xml;

public class XMLString extends XMLValeur {
	
	private String value;
	
	public XMLString(String value) {
		this.value = value;
	}
	
	public XMLString() {
		this("");
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toXML() {
		return "<string>" + this.value + "</string>";
	}

}
