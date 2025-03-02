package io.homebeaver.uom;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*

aus ADempiere:
                        Name            					TypeIsActiv
109	    11	0	6PK	6Pk	6-Pack									Y	N	0	4
50000	11	0	ml	ml	Mililiter								Y	N	4	4
50002	11	0	mg	mg	milligram								Y	N	6	6
50003	11	0	L	L	litre									Y	N	2	0
50001	11	0	Kg	Kg	kilogram							WE	Y	N	2	0
50005	11	0	t	t	Metric Ton	A metric ton is 1000 Kg	WE	Y	N	3	0
100	0	0	EA	Ea 	Each										Y	Y	0	4
101	0	0	HR	h  	Hour										Y	N	2	2
102	0	0	DA	d  	Day											Y	N	2	2
103	0	0	MJ	m  	Minutes	(lowest unit for resorce assigments)		Y	N	0	0
104	0	0	WD	D  	Work Day	8 hour							Y	N	2	2
105	0	0	WK	w  	Week										Y	N	2	2
106	0	0	MO	m  	Month	30 days								Y	N	2	2
107	0	0	WM	M  	Working Month	20 days						Y	N	2	2
108	0	0	YR	y  	Year										Y	N	2	2

SELECT C_UOM_ID,AD_Client_ID,AD_Org_ID,X12DE355,UOMSymbol,Name,Description,UOMType,IsActive,IsDefault,StdPrecision,CostingPrecision
 FROM C_UOM

SELECT * FROM AD_Ref_List WHERE AD_Ref_List_ID=53514;
 */
public class UoM {
	public static UoM create_ml() {
		return new UoM(50000, "Mililiter", null, "ml");
	}
	public static UoM create_Kg() {
		return new UoM(50001, "kilogram", null, "Kg");
	}
	public static UoM create_mg() {
		return new UoM(50002, "milligram", null, "mg");
	}
	public static UoM create_L() {
		return new UoM(50003, "litre", null, "L");
	}
	public static UoM create_t() {
		return new UoM(50005, "Metric Ton", "A metric ton is 1000 Kg", "t");
	}
	public static UoM create_h() {
		return new UoM(101, "Hour", null, "h");
	}
	public static UoM create_m() {
		return new UoM(-1, "Meter", "https://de.wikipedia.org/wiki/Meter", "m");
	}
	public static UoM create_A() {
		return new UoM(-1, "Ampere", "https://de.wikipedia.org/wiki/Ampere", "A");
	}

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String UOMSYMBOL = "uomSymbol";

	/**
	 * factory to create an object from JSON
	 * @param jsonString
	 * @return UoM instance
	 */
	public static UoM internalize(String jsonString) {
		JSONParser parser = new JSONParser();
		Reader reader = new StringReader(jsonString);
		UoM uom = null;
		try {
			Object jsonObject = parser.parse(reader); // throws IOException, ParseException
			uom = create((JSONObject)jsonObject);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uom;
	}

	/**
	 * factory to create an instance from JSON object
	 * @param jsonObject
	 * @return UoM instance
	 */
	public static UoM create(JSONObject jsonObject) {
		Long id = (Long) jsonObject.get(ID);
		String name = (String) jsonObject.get(NAME);
		String description = (String) jsonObject.get(DESCRIPTION);
		String uomSymbol = (String) jsonObject.get(UOMSYMBOL);
//		System.out.println("UoM.create(JSONObject): Id = "+id + ", Name = " + name + ", UomSymbol = "+ uomSymbol);
		return new UoM(id.intValue(), name, description, uomSymbol);
	}
	
	Integer id;
	String name;
	String description;
	String uomSymbol;

	public UoM(String name, String description) {
		this.id = -1;
		this.name = name;
		this.description = description;
	}
	public UoM(Integer id, String name, String description, String uomSymbol) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.uomSymbol = uomSymbol;
	}
	
	public void set(String k, Object v) {
		if(ID.equals(k)) {
			id = (Integer)v;
		} else if(NAME.equals(k)) {
			name = (String)v;
		} else if(DESCRIPTION.equals(k)) {
			description = (String)v;
		} else if(UOMSYMBOL.equals(k)) {
			uomSymbol = (String)v;
		} 
	}
	
	public URI getURI() {
		if(description==null) return null;
		try {
			return new URI(description);
//			Locale defaultLocale = JComponent.getDefaultLocale();
//			return new URI(url.replace("{language}", defaultLocale.getLanguage()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("not URI:"+description);
		}
		return null;
	}
	
	boolean isQuantity() {
		return uomSymbol!=null;
	}
	
    public String toString() {
        return isQuantity() ? uomSymbol+":"+name : name;
    }

    public JSONObject externalize(JSONObject obj) {
    	obj.put(ID, id);
    	obj.put(NAME, name);
    	obj.put(DESCRIPTION, description);
    	obj.put(UOMSYMBOL, uomSymbol);
    	return obj;
    }
    public String externalize() {
    	// Ergebnis als JSON
/*
UoM(50000, "Mililiter", null, "ml") ==>
{"id":50000,"name":"Mililiter","description":null,"uomSymbol":"ml"}
A ==>		
{"id":-1,"name":"Ampere","description":"https://de.wikipedia.org/wiki/Ampere","uomSymbol":"A"}
 */
    	JSONObject obj = externalize(new JSONObject());
    	return obj.toJSONString();
    }
    
}
