package tp5.json;

public class JSONNumber extends JSONValeur {
	
	private int value;

	@Override
	public String toXML() {
		return "<number>" + this.value + "</number>";
	}

}
