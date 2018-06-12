package demo;

import convert.NumberToTextConverter;

public class TestConverter {

	public static void main(String[] args) {
		NumberToTextConverter converter = new NumberToTextConverter();
		converter.addConversion(0,"Zero");
		converter.addConversion(1,"One");
		converter.addConversion(2,"Two");
		converter.addConversion(3,"Three");
		converter.addConversion(4,"Four");
		converter.addConversion(5,"Five");
		converter.addConversion(6,"Six");
		converter.addConversion(7,"Seven");
		converter.addConversion(8,"Eight");
		converter.addConversion(9,"Nine");
		converter.addConversion(10,"Ten");
		converter.addConversion(11,"Eleven");
		converter.addConversion(12,"Twelve");
		converter.addConversion(13,"Thirteen");
		converter.addConversion(14,"Fourteen");
		converter.addConversion(15,"Fifteen");
		converter.addConversion(16,"Sixteen");
		converter.addConversion(17,"Seventeen");
		converter.addConversion(18,"Eighteen");
		converter.addConversion(19,"Nineteen");		
		converter.addConversion(20,"Twenty");
		converter.addConversion(30,"Thirty");
		converter.addConversion(40,"Fourty");
		converter.addConversion(50,"Fifty");
		converter.addConversion(60,"Sixty");
		converter.addConversion(70,"Seventy");
		converter.addConversion(80,"Eighty");
		converter.addConversion(90,"Ninety");
		converter.addConversion(100,"Hundred");
		converter.addConversion(1000,"Thousand");
		converter.addConversion(1000000,"Million");		
		
		
		System.out.println(converter.convert(123456789));
		//Expected output is
		//One Hundred Twenty Three Million Four Hundred Fifty Six Thousand Seven Hundred Eighty Nine
		
	}
}
