public class test{
      public static void main(String[] args) {
        SpellChecker try1 = new SpellChecker("./words.txt");
        System.out.println(try1.getIncorrectWords("./test.txt"));
        System.out.println(try1.getSuggestions("odg"));
    }
}