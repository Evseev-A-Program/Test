import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnecdoteMain {
    public static void main(String[] args) {
        Anecdote anecdote = new Anecdote(){
            @Override
            public String getAnecdote(List anecdotes){
                return (String) anecdotes.get(new Random().nextInt(anecdotes.size() - 1) + 1);
            }
        };

        anecdote.add("1");
        anecdote.add("2");
        anecdote.add("3");
        anecdote.add("4");
        anecdote.add("5");
        anecdote.add("6");
        System.out.println(anecdote.getAnecdote(anecdote.anecdotes));

    }


}

class Anecdote{
    public List<String> anecdotes = new ArrayList<>();

    public Anecdote() {
    }

    public void add(String str){
        this.anecdotes.add(str);
    }

    public String getAnecdote(List anecdotes){
        return "";
    }
}