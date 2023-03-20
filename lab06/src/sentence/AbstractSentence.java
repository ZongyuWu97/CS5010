package sentence;

abstract class AbstractSentence implements Sentence {
  
  protected final String content;
  
  protected Sentence next;
  
  public AbstractSentence(String content, Sentence next) {
    this.content = content;
    this.next = next;
  }
  
  public AbstractSentence(String content) {
    this.content = content;
    this.next = new EmptyNode();
  }
  
  @Override
  public int getNumberOfWords() {
    return countHelp(0);
  }

  @Override
  public int countHelp(int acc) {
    if (this instanceof WordNode) {
      acc += 1;
    }
    return this.next.countHelp(acc);
  }

  @Override
  public String longestWord() {
    String res = this.content;
    String rest = this.next.longestWord();
    
    if (rest.length() > res.length()) {
      return rest;
    }
    
    return res;
  }

  @Override
  public Sentence merge(Sentence other) {
    AbstractSentence node = (AbstractSentence) this.duplicate();
    Sentence head = node;
    while (!(node.next instanceof EmptyNode)) {
      node = (AbstractSentence) node.next;
    }
    node.next = other;  
    return head;
  }

  @Override
  public Sentence duplicate() {
    if (this instanceof WordNode) {
      return new WordNode(this.content, this.next.duplicate());
    } else {
      return new PunctuationNode(this.content, this.next.duplicate());
    }
  }
  
  @Override
  public String toString() {
    AbstractSentence node = this;
    StringBuffer res = new StringBuffer(node.content);
    while (!(node.next instanceof EmptyNode)) {
      node = (AbstractSentence) node.next;
      if (node instanceof WordNode) {
        res.append(" ");
      } 
      res.append(node.content);
    }
    
    return res.toString();
  }
  
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Sentence)) {
      return false;
    } else if (this == o) {
      return true;
    } else {
      return this.hashCode() == o.hashCode();
    }
  }

}
