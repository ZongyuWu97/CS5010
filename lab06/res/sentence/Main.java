package sentence;


public class Main {
  private static WordNode word;
  private static PunctuationNode punctuation;
  private static WordNode sentence;
  private static EmptyNode empty;
  

  public Main(){
  }
  
  public static void main(String[] args) {
    word = new WordNode("word");
    punctuation = new PunctuationNode(",");
    sentence = new WordNode("This", 
        new PunctuationNode(",",
            new WordNode("is",
                new WordNode("a",
                    new WordNode("sentence",
                        new PunctuationNode(".")
                        )
                    )
                )
            )
        );
    empty = new EmptyNode();
    
    System.out.println(sentence.duplicate());
    System.out.println(sentence.next.duplicate());
    System.out.println(sentence.toString());
    System.out.println(sentence.next.toString());
    
  }

}
