package main;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Utils {
	public static final DecimalFormat DF = new DecimalFormat("#.####");
	static{
		DF.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.UK));
	}
}
