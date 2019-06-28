public class Osoba {

	public static final Osoba INSTANCE = new Osoba();

	private String imie = "Jan";
	private String nazwisko;
	private int wiek = 25;

	private Osoba(){}

	public String getImie(){
		return imie;
	}

	public String getNazwisko(){
		return nazwisko;
	}

	public int getWiek(){
		return wiek;
	}

	public void setImie(String imie){
		this.imie = imie;
	}

	public void setNazwisko(String nazwisko){
		this.nazwisko = nazwisko;
	}

	public void setWiek(int wiek){
		this.wiek = wiek;
	}

}