import java.util.Map.Entry;
import java.util.TreeMap;

public class Convert_Numbers {

	/* Roman Map */
	private final static TreeMap<Integer, String> roman_map = new TreeMap<Integer, String>();
	private final static TreeMap<Integer, String> roman_map_weird = new TreeMap<Integer, String>();
	private final static TreeMap<Integer, String> roman_map_normal = new TreeMap<Integer, String>();
	
	private static int base_int = 99;
	private static String roman_string = "";
	private static int roman_int = 0;

	static {
		/*
		  Example 3: Arabic -> Roman for 1..10
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10
		 -> I, II, III, IV, V, VI, VII, VIII, IX, X
		 
		 I	1
		 V	5
		 X	10
		 L	50
		 C	100
		 D	500
		 M	1000

		 */

		/* create a roman to int map */		
		/* had to reverse order for roman to in */
		roman_map.put(1000, "M");
		roman_map.put(900, "CM"); /* catch */
		roman_map.put(500, "D");
		roman_map.put(400, "CD"); /* catch */
		roman_map.put(100, "C");
		roman_map.put(90,  "XC"); /* catch */
		roman_map.put(50,  "L");
		roman_map.put(40,  "XL"); /* catch */
		roman_map.put(10,  "X");
		roman_map.put(9,   "IX"); /* catch */
		roman_map.put(5,   "V");
		roman_map.put(4,   "IV"); /* catch */
		roman_map.put(1,   "I");  
		
		/* weird ones */
		roman_map_weird.put(900, "CM"); /* catch */
		roman_map_weird.put(400, "CD"); /* catch */
		roman_map_weird.put(90,  "XC"); /* catch */
		roman_map_weird.put(40,  "XL"); /* catch */
		roman_map_weird.put(9,   "IX"); /* catch */
		roman_map_weird.put(4,   "IV"); /* catch */
		
		/* normal ones */
		roman_map_normal.put(1000, "M");
		roman_map_normal.put(500, "D");
		roman_map_normal.put(100, "C");
		roman_map_normal.put(50,  "L");
		roman_map_normal.put(10,  "X");
		roman_map_normal.put(5,   "V");
		roman_map_normal.put(1,   "I");  

	}

	public static void main(String[] args) {

		roman_string = toRoman(base_int);

		/* test int to roman */
		System.out.println("Int To Roman Example : " + base_int);
		System.out.println("-------------------------------------");
		System.out.println(roman_string);
		System.out.println("");
		
		
		System.out.println("Roman To Int Example : " + roman_string);

		/* work it out */
		roman_int = romanToNumeric();
		
		System.out.println("-------------------------------------");
		System.out.println(roman_int);
		System.out.println("");
	}

	public final static String toRoman(int number) {
		
		/* get the closest value in the array lowest */
		/* e.g. 15 = 10 */
		int closestValue = roman_map.floorKey(number);
		
		/* if the number is the same then return value of roman_map */
		if (number == closestValue) {
			return roman_map.get(number);
		}
		
		/* remove value found */
		number -= closestValue;
		
		/* else get closest roman_map index value and re-enter method with remaining value */ 
		return roman_map.get(closestValue) + toRoman(number);
	}


	public final static int romanToNumeric() {
		
		/* create a new temp holder */
		int num = 0;

		/* work through list */
		num += workThroughRoman (roman_map_weird);
		num += workThroughRoman (roman_map_normal);
		
		/* return final number */
		return num;
	}
	
	public static final int workThroughRoman(TreeMap<Integer, String> list) {
		
		/* create a temp int holder */
		int num = 0;
		
		/* for each entry in the map */
		for(Entry<Integer, String> entry : list.entrySet()) {
			
			/* fetch the inter and roman key value */
			int value     = entry.getKey().intValue();    	// 1
			String  key   = entry.getValue();  				// I
			
			/* create a temp boolean for do..while execution */
			boolean entryExists = true;

			do {
				/* if roman string contains string */
				if(roman_string.contains(key)) {
					
					/* increment the value with entry value */
					num += value;
					roman_string = roman_string.replaceFirst(key, "");
				}
				/* else doesnt exist kick out of loop */
				else {
					entryExists = false;
				}
			}
			while(entryExists);
			
		}
		
		return num;
	}
}
