package tp5.json2xml;

public class XMLNumber extends XMLValeur {
	
	private String value;
	
	public XMLNumber(String value) {
		this.value = value;
	}
	
	public XMLNumber() {
		this("" + 0);
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toXML() {
		return "<number>" + this.value + "</number>";
	}

}
