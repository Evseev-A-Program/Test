import java.util.ArrayList;
import java.util.List;

public class ThemesMain {

    public static void main(String[] args) {
        Themes t1 = Themes.getThemes(1, Math.ADDITION.getStr(), "blabalbalbalba");
        Themes t2 = Themes.getThemes(2, "Interface", "blabalbalbalba");
        Themes t3 = Themes.getThemes(3, "Abstract", "blabalbalbalba");
        Themes t4 = Themes.getThemes(3, "Abstract", "blabalbalbalba");

        System.out.println(t3.equals(t4));
        System.out.println(t1.compareTo(t2));

        System.out.println(t3==t4);


    }
}

enum Math {
    ADDITION("ADDITION"),
    SUBSTRACTION("SUBSTRACTION"),
    MULTIPLICATION("MULTIPLICATION"),
    DIVISION("DIVISION");

    private final String str;

    Math(String str) {
        this.str = str;
    }
    public String getStr(){
        return str;
    }
}

enum History{
    ANCIENT("ANCIENT"),
    NEW("NEW"),
    LASTET("LASTET");

    private final String str;

    History(String str) {
        this.str = str;
    }

    public String getStr(){
        return str;
    }
}


class Themes implements Comparable<Themes> {

    private static List<Themes> themes = new ArrayList<>();

    private int number;
    private String name;
    private String text;

    private Themes(int number, String name, String text) {
        this.number = number;
        this.name = name;
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return  false;
        if(this == obj) return true;
        if(!Themes.class.equals(obj.getClass())) return false;
        Themes themes = (Themes) obj;
        if (number != themes.getNumber()) return false;
        if (name != themes.getName()) return false;
        return true;
    }

//    public static Themes getThemes(int number, String name, String text){
//        Themes tm = themes.stream().filter(themes1 -> themes1.name.equals(name) && number == number).findFirst().orElse(null);
//        if(tm == null) tm = new Themes(number, name,text);
//        return tm;
//    }

    public static Themes getThemes (int number, String name, String text){
        Themes tm = new Themes(number, name, text);
        int i = themes.indexOf(tm);
        if(i != -1) return themes.get(i);
        else {
                themes.add(tm);
                return tm;
        }
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = result * 17 + number;
        result = result * 17 + Integer.parseInt(name);
        return result;
    }





    @Override
    public String toString() {
        return "Themes{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Themes themes) {
        return themes.number - this.number;
    }
}