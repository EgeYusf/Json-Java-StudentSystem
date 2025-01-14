import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

class BaseEntity {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

interface IKayit {
    void kaydet();
    void sil();
}

class Musteri extends BaseEntity implements IKayit {
    private String isim;
    private String soyisim;
    private String yemek; // Müşterinin yemek tercihi
    private Film film;
    private Seans seans;

    public Musteri(String id, String isim, String soyisim, String yemek) {
        this.setId(id);
        this.isim = isim;
        this.soyisim = soyisim;
        this.yemek = yemek;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getYemek() {
        return yemek;
    }

    public void setYemek(String yemek) {
        this.yemek = yemek;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Seans getSeans() {
        return seans;
    }

    public void setSeans(Seans seans) {
        this.seans = seans;
    }

    @Override
    public void kaydet() {
        Gson gson = new Gson();
        List<Musteri> musteriler = Musteri.readFromJson();
        musteriler.add(this);
        try (FileWriter writer = new FileWriter("Musteri.json")) {
            gson.toJson(musteriler, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // JSON formatında konsola yazdırma
        System.out.println("Müşteri Kaydı JSON:");
        System.out.println(gson.toJson(this));
    }

    @Override
    public void sil() {
        Gson gson = new Gson();
        List<Musteri> musteriler = Musteri.readFromJson();
        musteriler.removeIf(musteri -> musteri.getId().equals(this.getId()));
        try (FileWriter writer = new FileWriter("Musteri.json")) {
            gson.toJson(musteriler, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Musteri> readFromJson() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("Musteri.json")) {
            return gson.fromJson(reader, new TypeToken<List<Musteri>>() {}.getType());
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

class Film extends BaseEntity {
    private String ad;
    private int sure;
    private String tur;

    public Film(String id, String ad, int sure, String tur) {
        this.setId(id);
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    // Film bilgilerini JSON dosyasına kaydetme
    public static void saveFilmData(List<Film> filmler) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("Film.json")) {
            gson.toJson(filmler, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Film bilgilerini JSON olarak konsola yazdırma
        System.out.println("Film Verileri JSON:");
        System.out.println(gson.toJson(filmler));
    }

    // JSON dosyasından Film verilerini okuma
    public static List<Film> readFromFilmJson() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("Film.json")) {
            return gson.fromJson(reader, new TypeToken<List<Film>>() {}.getType());
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

class Seans {
    private String saat;
    private Film film;

    public Seans(String saat, Film film) {
        this.saat = saat;
        this.film = film;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Film verileri
        Film film1 = new Film("1", "Recep İvedik", 83, "Komedi");
        Film film2 = new Film("2", "Thor", 115, "Aksiyon");
        Film film3 = new Film("3", "Squid Game", 135, "Aksiyon");
        Film film4 = new Film("4", "Dabbe 3", 135, "Aksiyon");
        Film film5 = new Film("5", "Çakallarla Dans", 135, "Aksiyon");
        Film film6 = new Film("6", "Terminatör 1", 135, "Aksiyon");
        Film film7 = new Film("7", "Avengers", 135, "Aksiyon");
        Film film8 = new Film("8", "Spiderman", 135, "Aksiyon");


        // Filmleri Film.json dosyasına kaydetme
        List<Film> filmler = new ArrayList<>();
        filmler.add(film1);
        filmler.add(film2);
        filmler.add(film3);
        filmler.add(film4);
        filmler.add(film5);
        filmler.add(film6);
        filmler.add(film7);
        filmler.add(film8);


        Film.saveFilmData(filmler); // Filmleri JSON dosyasına kaydeder

        // Seanslar
        Seans seans1 = new Seans("9:00", film1);
        Seans seans2 = new Seans("12:00", film2);
        Seans seans3 = new Seans("15:00", film3);
        Seans seans4 = new Seans("15:00", film4);
        Seans seans5 = new Seans("15:00", film5);
        Seans seans6 = new Seans("15:00", film6);
        Seans seans7 = new Seans("15:00", film7);
        Seans seans8 = new Seans("15:00", film8);
        Seans seans9 = new Seans("11:00", film3);
        Seans seans10 = new Seans("15:00", film5);
        Seans seans11= new Seans("23:00", film4);
        Seans seans12= new Seans("16:00", film8);
        Seans seans13 = new Seans("19:00", film1);
        Seans seans14= new Seans("20:00", film7);
        Seans seans15 = new Seans("21:00", film1);

        // Menü ile kullanıcıya seçenekler sunma
        while (true) {
            System.out.println("\n1. Müşteri ekle");
            System.out.println("2. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Temizleme

            switch (secim) {
                case 1:
                    // Müşteri ekleme
                    System.out.print("Müşteri ID: ");
                    String musteriId = scanner.nextLine();
                    System.out.print("Müşteri Adı: ");
                    String musteriIsim = scanner.nextLine();
                    System.out.print("Müşteri Soyadı: ");
                    String musteriSoyisim = scanner.nextLine();

                    // Yemek tercihi
                    System.out.print("Yiyecek veya İçecek almak ister misiniz? (Evet/Hayır): ");
                    String yemekTercihi = scanner.nextLine();

                    // Müşteri oluşturma
                    Musteri musteri = new Musteri(musteriId, musteriIsim, musteriSoyisim, yemekTercihi);



                    // Film seçimi
                    System.out.println("-----Hangi filmi izlemek istersiniz?-------");
                    System.out.println("1. Recep İvedik 4");
                    System.out.println("2. Thor");
                    System.out.println("4. Squid Game");
                    System.out.println("5. Dabbe 3");
                    System.out.println("6. Çakarllarla Dans 2");
                    System.out.println("7. Terminatör 1");
                    System.out.println("8.Avengers");
                    System.out.println("9.Spiderman");
                    System.out.print("Seçiminizi yapın: ");
                    int filmSecimi = scanner.nextInt();
                    scanner.nextLine(); // Temizleme

                    Film secilenFilm = (filmSecimi == 1) ? film1 : (filmSecimi == 2) ? film2 : film3;
                    musteri.setFilm(secilenFilm);

                    // Seans seçimi
                    System.out.println("Seanslar: ");
                    System.out.println("1. 10:00 - " + film1.getAd());
                    System.out.println("2. 14:00 - " + film2.getAd());
                    System.out.println("3. 18:00 - " + film3.getAd());
                    System.out.print("Seçiminizi yapın: ");
                    int seansSecimi = scanner.nextInt();
                    scanner.nextLine(); // Temizleme

                    Seans secilenSeans = (seansSecimi == 1) ? seans1 : (seansSecimi == 2) ? seans2 : seans3;
                    musteri.setSeans(secilenSeans);

                    // Müşteri kaydını JSON'a ekleme
                    musteri.kaydet();
                    break;

                case 2:
                    // Çıkış
                    System.out.println("Çıkılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçenek!");
            }
        }
    }
}