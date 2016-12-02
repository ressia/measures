package com.measureModel.units.providers;

import java.util.HashMap;

import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.units.BaseUnit;
import com.measureModel.units.NotProportionalDerivedUnit;
import com.measureModel.units.ProportionalDerivedUnit;
import com.measureModel.units.UnitBehavior;

public class UnitsProvider {
	
	private static UnitsProvider uniqueInstance = new UnitsProvider();
	
	private HashMap units;
		
	private UnitsProvider() {
		units = new HashMap();
		initialize();
	}
	
	public static UnitsProvider newInstance() {
		return uniqueInstance;
	}
	
	
	public void initialize() {
		addUnit(createMeter());
		addUnit(createMillimeter());
		addUnit(createCentimeter());
		addUnit(createDecimeter());
		addUnit(createDecameter());
		addUnit(createHectometer());
		addUnit(createKilometer());
		addUnit(createPeso());
		addUnit(createDollar());
		addUnit(createEuro());
		addUnit(createCentavo());
		addUnit(createQuarter());
		addUnit(createEuroCent());
		addUnit(createKelvin());
		addUnit(createCelsius());
		addUnit(createFahrenheit());
		addUnit(createSecond());
		addUnit(createMinute());
	}
	
	public void addUnit(UnitBehavior aUnit) {
		units.put(aUnit.name(), aUnit);
	}
	
	public UnitBehavior celsius() {
		return (UnitBehavior)units.get(celsiusName());
	}

	public String celsiusName() {
		return "gradoCentigrado";
	}
	
	public UnitBehavior cent() {
		return (UnitBehavior)units.get(centName());
	}

	public String centName() {
		return "cent";
	}
	
	public UnitBehavior centimeter() {
		return (UnitBehavior)units.get(centimeterName());
	}

	public String centimeterName() {
		return "centimeter";
	}
	
	public UnitBehavior createCelsius() {
		return new NotProportionalDerivedUnit(kelvin(),new CelsiusToKelvinConversionFunction(),new KelvinToCelsiusConversionFunction(), celsiusName(),celsiusName()+ "s","C");
	}
	
	public UnitBehavior createCentavo() {
		return new ProportionalDerivedUnit(peso(),new Fraction(1,100),centName(),centName()+ "s","$ cent");
	}
	
	public UnitBehavior createCentimeter() {
		return new ProportionalDerivedUnit(meter(),new Fraction(1,100),centimeterName(),centimeterName()+ "s","cm");
	}
	
	public UnitBehavior createDecameter() {
		return new ProportionalDerivedUnit(meter(),new SmallInteger(10),decameterName(),decameterName()+ "s","dcm");
	}
	
	public UnitBehavior createDecimeter() {
		return new ProportionalDerivedUnit(meter(),new Fraction(1,10),decimeterName(),decimeterName()+ "s","dm");
	}
	
	public UnitBehavior createDollar() {
		return new BaseUnit(dollarName(), dollarName() + "s" ,"u$s");
	}
	
	public UnitBehavior createEuro() {
		return new BaseUnit(euroName(), "E");
	}
	
	public UnitBehavior createEuroCent() {
		return new ProportionalDerivedUnit(euro(),new Fraction(1,100),euroCentName(),euroCentName()+ "s","E cent");
	}
	
	public UnitBehavior createFahrenheit() {
		return new NotProportionalDerivedUnit(kelvin(),new KelvinToFahrenheitConversionFunction(),new FahrenheitToKelvinConversionFunction(),fahrenheitName(),fahrenheitName()+ "s","F");
	}
	
	public UnitBehavior createHectometer() {
		return new ProportionalDerivedUnit(meter(),new SmallInteger(100),hectometerName(),hectometerName()+ "s","hm");
	}
	
	public UnitBehavior createKelvin() {
		return new BaseUnit(kelvinName(),"K");
	}
	
	public UnitBehavior createKilometer() {
		return new ProportionalDerivedUnit(meter(),new SmallInteger(1000),kilometerName(),kilometerName()+ "s","km");
	}
	
	public UnitBehavior createMeter() {
		return new BaseUnit(meterName(),"m");
	}
	
	public UnitBehavior createMillimeter() {
		return new ProportionalDerivedUnit(meter(),new Fraction(1,1000),millimeterName(),millimeterName()+ "s","ml");
	}
	
	public UnitBehavior createMinute() {
		return new ProportionalDerivedUnit(second(),new SmallInteger(60),minuteName(),minuteName()+ "s","m");
	}
	
	public UnitBehavior createPeso() {
		return new BaseUnit(pesoName(),pesoName() + "s");
	}
	
	public UnitBehavior createQuarter() {
		return new ProportionalDerivedUnit(dollar(),new Fraction(1,4),quarterName(),quarterName()+ "s","u$s quarter");
	}
	
	public UnitBehavior createSecond() {
		return new BaseUnit(secondName(),"sec");
	}
	
	public UnitBehavior decameter() {
		return (UnitBehavior)units.get(decameterName());
	}
	
	public String decameterName() {
		return "decameter";
	}
	
	public UnitBehavior decimeter() {
		return (UnitBehavior)units.get(decimeterName());
	}
	
	public String decimeterName() {
		return "decimeter";
	}
	
	public UnitBehavior dollar() {
		return (UnitBehavior)units.get(dollarName());
	}
	
	public String dollarName() {
		return "dollar";
	}
	
	public UnitBehavior euro() {
		return (UnitBehavior)units.get(euroName());
	}
	
	public String euroName() {
		return "euro";
	}
	
	public UnitBehavior euroCent() {
		return (UnitBehavior)units.get(euroCentName());
	}
	
	public String euroCentName() {
		return "euro cent";
	}
	
	public UnitBehavior fahrenheit() {
		return (UnitBehavior)units.get(fahrenheitName());
	}
	
	public String fahrenheitName() {
		return "fahrenheit";
	}
	
	public UnitBehavior hectometer() {
		return (UnitBehavior)units.get(hectometerName());
	}
	
	public String hectometerName() {
		return "hectometer";
	}
	
	public UnitBehavior kelvin() {
		return (UnitBehavior)units.get(kelvinName());
	}
	
	public String kelvinName() {
		return "kelvin";
	}
	
	public UnitBehavior kilometer() {
		return (UnitBehavior)units.get(kilometerName());
	}
	
	public String kilometerName() {
		return "kilometer";
	}
	
	public UnitBehavior meter() {
		return (UnitBehavior)units.get(meterName());
	}
	
	public String meterName() {
		return "meter";
	}
	
	public UnitBehavior millimeter() {
		return (UnitBehavior)units.get(millimeterName());
	}
	
	public String millimeterName() {
		return "millimeter";
	}
	
	public UnitBehavior minute() {
		return (UnitBehavior)units.get(minuteName());
	}
	
	public String minuteName() {
		return "minute";
	}
	
	public UnitBehavior peso() {
		return (UnitBehavior)units.get(pesoName());
	}
	
	public String pesoName() {
		return "peso";
	}
	
	public UnitBehavior quarter() {
		return (UnitBehavior)units.get(quarterName());
	}
	
	public String quarterName() {
		return "quarter";
	}
	
	public UnitBehavior second() {
		return (UnitBehavior)units.get(secondName());
	}
	
	public String secondName() {
		return "second";
	}

}
