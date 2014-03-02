package tp5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tp5.json2xml.XMLArray;
import tp5.json2xml.XMLErreur;
import tp5.json2xml.XMLExpression;
import tp5.json2xml.XMLFalse;
import tp5.json2xml.XMLNull;
import tp5.json2xml.XMLNumber;
import tp5.json2xml.XMLObjet;
import tp5.json2xml.XMLPaire;
import tp5.json2xml.XMLString;
import tp5.json2xml.XMLTrue;

public class JSONReader {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("You must put a filename in parameter");
			System.exit(0);
		}
		if (!args[0].endsWith(".json")) {
			System.out.println("You must put a .json filename in parameter");
			System.exit(0);
		}
		
		String outputName = args[0].substring(0, args[0].lastIndexOf('.'));
		outputName = outputName.concat(".result.xml");
		
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			fr = new FileReader(new File(args[0]));
			fw = new FileWriter(outputName);
			String res;
			
			try {
				JSONParser parser = new JSONParser();
				XMLExpression e = new JSONReader().parse(parser.parse(fr));
				res = e.toXML();
			} catch(ParseException e) {
				res = "<ERREUR/>";
			}
			
			System.out.println(res);
			fw.write(res);
			
			fr.close();
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public XMLExpression parse(Object o) {
		XMLExpression res;
		if (o instanceof JSONArray) {
			res = parseArray((JSONArray) o);
		} else if (o instanceof JSONObject) {
			res = parseObject((JSONObject) o);
		} else {
			res = new XMLErreur();
		}
		return res;
	}
	
	public XMLExpression parseArray(JSONArray jsonArray) {		
		Iterator<Object> iter = jsonArray.iterator();
		XMLArray array = new XMLArray();
		Object o = null;
		
		while (iter.hasNext()) {
			o = iter.next();
			
			if (o == null) {
				array.add(new XMLNull());
			} else if (o instanceof JSONArray) {
				array.add(parseArray((JSONArray) o));
			} else if (o instanceof JSONObject) {
				array.add(parseObject((JSONObject) o));
			} else if (o instanceof Boolean) {
				if (o.toString().equals("true")) {
					array.add(new XMLTrue());
				} else if (o.toString().equals("false")) {
					array.add(new XMLFalse());
				} else {
					array.add(new XMLErreur());
				}
			} else if (o instanceof Long) {
				array.add(new XMLNumber(o.toString()));
			} else if (o instanceof String) {	
				String s = (String) o;
				array.add(new XMLString(s));
			} else {
				System.out.println(o.getClass().toString());
				array.add(new XMLErreur());
			}
		}

		return array;
	}
	
	public XMLExpression parseObject(JSONObject jsonObject) {
		Iterator iter = jsonObject.entrySet().iterator();		
		
		XMLObjet object = new XMLObjet();
		XMLPaire p = null;
		Map.Entry entry = null;
		
		while (iter.hasNext()) {
			entry = (Entry) iter.next();
			p = new XMLPaire();
			p.setNom(String.valueOf(entry.getKey()));
			
			if (entry.getValue() == null) {
				p.setValeur(new XMLNull());
			} else if (entry.getValue() instanceof JSONArray) {
				p.setValeur(parseArray((JSONArray) entry.getValue()));
			} else if (entry.getValue() instanceof JSONObject) {
				p.setValeur(parseObject((JSONObject) entry.getValue()));
			} else if (entry.getValue() instanceof Boolean) {
				if (entry.getValue().toString().equals("true")) {
					p.setValeur(new XMLTrue());
				} else if (entry.getValue().toString().equals("false")) {
					p.setValeur(new XMLFalse());
				} else {
					p.setValeur(new XMLErreur());
				}
			} else if (entry.getValue() instanceof Long) {
				p.setValeur(new XMLNumber(String.valueOf(entry.getValue())));
			} else {
				p.setValeur(new XMLString(String.valueOf(entry.getValue())));
			}
			
			object.add(p);
		}
		
		return object;
	}
	
	private boolean isInteger(String s) {
		if (    s.length() > 0 && Character.isDigit(s.charAt(0))
				|| s.length() > 1 && (s.charAt(0) == '+' || s.charAt(0) == '-' )
			)
		{
			for (int i = 1; i < s.length(); ++i) {
				if (!Character.isDigit(s.charAt(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
