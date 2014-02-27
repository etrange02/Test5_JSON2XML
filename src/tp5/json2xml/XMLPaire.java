package tp5.json2xml;

public class XMLPaire extends XMLExpression {

	private String nom;
	private XMLExpression valeur;
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setValeur(XMLExpression valeur) {
		this.valeur = valeur;
	}
	
	@Override
	public String toXML() {
		
		return "<item name=\"" + this.nom + "\">" + this.valeur.toXML() + "</item>" ;
	}
}
