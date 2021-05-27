import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//This class just contains functions which return some generic information about the current mysteries
public class Mysteries {

	public final static int[] PATER = {2, 7, 20, 33, 46, 59};
	public final static int[] GLORIA = {6, 18, 31, 44, 57, 70};
	public final static int[] OMIJESU = {19, 32, 45, 58, 71};
	
	public static String getMystery (String mysteries, int num) {
		switch (mysteries) {
			case "joyful":
				switch(num) {
					case 1:
						return "annunciation";
					case 2:
						return "visitation";
					case 3:
						return "nativity";
					case 4:
						return "presentation";
					case 5:
						return "finding";
					default: break;
				}
			case "sorrowful":
				switch(num) {
					case 1:
						return "agony";
					case 2:
						return "scourging";
					case 3:
						return "crowning";
					case 4:
						return "carrying";
					case 5:
						return "crucifixion";
					default: break;
				}
			case "glorious":
				switch(num) {
					case 1:
						return "resurrection";
					case 2:
						return "ascension";
					case 3:
						return "descent";
					case 4:
						return "assumption";
					case 5:
						return "coronation";
					default: break;
				}
			default: break;
		}
		return "NotFound";
	}
	
	public static String getFullMystery (String mysteries, int num) {
		switch (mysteries) {
			case "joyful":
				switch(num) {
					case 1:
						return "Annunciation";
					case 2:
						return "Visitation";
					case 3:
						return "Nativity of Our Lord";
					case 4:
						return "Presentation at the Temple";
					case 5:
						return "Finding of Jesus at the Temple";
					default: break;
				}
			case "sorrowful":
				switch(num) {
					case 1:
						return "Agony in the Garden";
					case 2:
						return "Scourging at the Pillar";
					case 3:
						return "Crowning of Thorns";
					case 4:
						return "Carrying of the Cross";
					case 5:
						return "Crucifixion";
					default: break;
				}
			case "glorious":
				switch(num) {
					case 1:
						return "Resurrection";
					case 2:
						return "Ascension";
					case 3:
						return "Descent of the Holy Spirit";
					case 4:
						return "Assumption of the Virgin Mary";
					case 5:
						return "Coronation of the Virgin Mary";
					default: break;
				}
			default: break;
		}
		return "NotFound";
	}
	
	public static String getPrayer (int number) {
		if(number == 1) {
			return "symbolum_apostolorum";
		}
		if(Main.arrayContains(PATER, number)) {
			return "pater_noster";
		}
		if(Main.arrayContains(GLORIA, number)) {
			return "gloria_patri";
		}
		if(Main.arrayContains(OMIJESU, number)) {
			return "fatima";
		}
		if(number == 72) {
			return "salve_regina";
		}
		return "ave_maria";
	}
	
	public static String getPrayerText(String file) {
		try {
			//FileReader reader = new FileReader(file);
			String text = Files.readString(Path.of(file));
			//reader.close();
			return text;
		} catch (IOException e) {
			System.err.println("Error: file " + file + " not found!");
		}
		return "null";
	}
	
}
