package io.homebeaver.uom;

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

	Integer id;
	String name;
	String description;
	String uomSymbol;

	public UoM(String name, String description) {
		this.id = -1;
		this.name = name;
		this.description = description;
	}
	private UoM(Integer id, String name, String description, String uomSymbol) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.uomSymbol = uomSymbol;
	}
	
	boolean isQuantity() {
		return uomSymbol!=null;
	}
	
    public String toString() {
        return name;
    }

}
