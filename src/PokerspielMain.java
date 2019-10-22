import java.util.Arrays;
import java.util.Random;

public class PokerspielMain {

	static int[] Kartendeck = new int[52];
	static final int KARTEN_PRO_FARBE = 13;
	static final int ANZAHL_SYMBOLE = 4;
	static int[] Handkarten = new int[5];
//	static int[] Handkarten = {0,9,10,11,12};
	static Random rn = new Random();	
	static int anzVersuche = 100;
	static int royalFlushZaehler = 0;
	static int straightFlushZaehler = 0;
	static int vierlingZaehler = 0;
	static int fullHouseZaehler = 0;
	static int flushZaehler = 0;
	static int straightZaehler = 0;
	static int drillingZaehler = 0;
	static int zweiPaarezaehler = 0;
	static int paarZaehler = 0;
	static int höchsteKarteZaehler = 0;

	static void FillKartendeck() {
		for(int i = 0; i < Kartendeck.length; i++)
		{
			Kartendeck[i] = i;
		}
		Arrays.toString(Kartendeck);
	}
	
	static void Mischen() {
		for (int i = 0; i < Kartendeck.length; i++)
		{
			int rnd = rn.nextInt(Kartendeck.length);
			int ausStapelZiehen = Kartendeck[i];
			Kartendeck[i] = Kartendeck[rnd];
			Kartendeck[rnd] = ausStapelZiehen;
		}
		Arrays.toString(Kartendeck);
	}
	
	static void Kartenziehen() {
		for (int i = 0; i < Handkarten.length; i++)
		{	
			Handkarten[i] = Kartendeck[i];
		}
		Arrays.sort(Handkarten);
		Arrays.toString(Handkarten); 

	}	
	
	public static int symbolBerechnen(int Karte) {
		return Karte % KARTEN_PRO_FARBE;
	}
	
	public static int farbeBerechnen(int Karte) {
		return Karte / KARTEN_PRO_FARBE;
	}
		
	public static boolean Paar(int gleicheZahlenParameter) {
		int gleicheKartenZaehler = 0;
		for (int i = 0; i < Handkarten.length - 1; i++)		//-1 weil handkarten.length gibt die absolute länge vom array an => ohne -1, ein unnötiger schleifendurchlauf 
		{
			for (int j = i + 1; j < Handkarten.length; j++)
			{
				if(symbolBerechnen(Handkarten[i]) == symbolBerechnen(Handkarten[j]))
				{
					gleicheKartenZaehler++;
				}
			}
		}
		if (gleicheKartenZaehler == gleicheZahlenParameter) {
			return true;
		}
		return false;
	}
	

	public static boolean Straight() {
		for(int i = 1; i < Handkarten.length; i++)
		{
			if(symbolBerechnen(Handkarten[i - 1]) != ((symbolBerechnen(Handkarten[i])) - 1))
			{
				return false;
			}
		} return true;
	} 
	
	public static boolean Flush() {
		for (int i = 1; i < Handkarten.length; i++)
		{
			if (farbeBerechnen(Handkarten[i - 1]) != farbeBerechnen(Handkarten[i]))
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean Royal() {
		if (symbolBerechnen(Handkarten[0]) == 0 && symbolBerechnen(Handkarten[1]) == 9 && symbolBerechnen(Handkarten[2]) == 10 &&
				symbolBerechnen(Handkarten[3]) == 11 && symbolBerechnen(Handkarten[4]) == 12)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static void auswertung()
		{
			if (Royal() && Flush())
			{
				royalFlushZaehler++;
//				System.out.println("Royal Flush");
			}
			else if (Straight() && Flush())
			{
				straightFlushZaehler++;
//				System.out.println("Straight Flush"); 
			} 
			else if (Paar(6))
			{
				vierlingZaehler++;
//				System.out.println("Vierling");
			}
			else if (Paar(4))
			{
				fullHouseZaehler++;
//				System.out.println("Full House");
			} 
			else if (Flush())
			{
				flushZaehler++;
//				System.out.println("Flush");
			} 
			else if (Straight() || Royal())
			{
				straightZaehler++;
//				System.out.println("Straight"); 
			} 
			else if (Paar(3))
			{
				drillingZaehler++;
//				System.out.println("Drilling");
			}
			else if (Paar(2))
			{
				zweiPaarezaehler++;
//				System.out.println("Zwei Paare");
			} 	
			else if (Paar(1))
			{
				paarZaehler++;
//				System.out.println("ein Paar");
			}
			else 
			{
				höchsteKarteZaehler++;
			}
		}
	
	public static void wahrscheinlichkeitsBerechnung() {
		
		double paarWahrscheinlichkeit = paarZaehler * 100.000000000 / anzVersuche;
		double zweiPaareWahrscheinlichkeit = zweiPaarezaehler * 100.000000000 / anzVersuche;
		double drillingWahrscheinlichkeit = drillingZaehler * 100.000000000 / anzVersuche;
		double straightWahrscheinlichkeit = straightZaehler * 100.000000000 / anzVersuche;
		double flushWahrscheinlichkeit = flushZaehler * 100.000000000 / anzVersuche;
		double fullhouseWahrscheinlichkeit = fullHouseZaehler * 100.000000000 / anzVersuche;
		double vierlingWahrscheinlichkeit = vierlingZaehler * 100.000000000 / anzVersuche;
		double straightFlushWahrscheinlichkeit = straightFlushZaehler * 100.000000000 / anzVersuche;
		double royalFlushWahrscheinlichkeit = royalFlushZaehler * 100.000000000 / anzVersuche;
		double höchsteKarteWahrscheinlichkeit = höchsteKarteZaehler * 100.000000000 / anzVersuche;
		
		System.out.println(paarZaehler+" Paar(e) bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit ein Paar zu ziehen = "+paarWahrscheinlichkeit+"%    				Wikipedia sagt 42,26%");
		System.out.println(zweiPaarezaehler+" mal Zwei Paar(e) bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit Zwei Paare zu ziehen = "+zweiPaareWahrscheinlichkeit+"%     			Wikipedia sagt 4,75%");
		System.out.println(drillingZaehler+" Drilling(e) bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit einen Drilling zu ziehen = "+drillingWahrscheinlichkeit+"%       			Wikipedia sagt 2,11%");
		System.out.println(straightZaehler+" Straße(n) bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit eine Straße zu ziehen = "+straightWahrscheinlichkeit+"%        			Wikipedia sagt 0,392%");
		System.out.println(flushZaehler+" Flush bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit einen Flush zu ziehen = "+flushWahrscheinlichkeit+"%             			Wikipedia sagt 0,197%");
		System.out.println(fullHouseZaehler+" Full House bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit ein Full House zu ziehen = "+fullhouseWahrscheinlichkeit+"%        			Wikipedia sagt 0,144%");
		System.out.println(vierlingZaehler+" Vierling(e) bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit einen Vierling zu ziehen = "+vierlingWahrscheinlichkeit+"%				Wikipedia sagt 0,0240%");
		System.out.println(straightFlushZaehler+" Straight Flush bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit einen Straight Flush zu ziehen = "+straightFlushWahrscheinlichkeit+"%			Wikipedia sagt 0,00139%");
		System.out.println(royalFlushZaehler+" Royal Flush bei "+anzVersuche+ " Versuchen => Wahrscheinlichkeit einen Royal Flush zu ziehen = "+royalFlushWahrscheinlichkeit+"%				Wikipedia sagt 0,000154");
		System.out.println(höchsteKarteZaehler+" mal keine Kartenkombination => Wahrscheinlichkeit, dass die höchse Karte die entscheidung trifft = "+höchsteKarteWahrscheinlichkeit+"% 	Wikipedia sagt 50,12%");
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < anzVersuche; i++) {
			FillKartendeck();
			Mischen();
			Kartenziehen();
			auswertung();
		}	
			wahrscheinlichkeitsBerechnung();
	}	
}	
