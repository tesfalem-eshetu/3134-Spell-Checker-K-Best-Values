import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SpellChecker implements SpellCheckerInterface {
//instanitate private instance varibales 
    private String DicName;
    private HashSet<String> DicSet = new HashSet<>();
    private List<String> ErrorList = new ArrayList<String>();
    private String FileToBeChecked;
    //will be used later for spelling replament 
    char[] alphabets = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
     'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
      'u', 'v', 'w', 'x', 'y', 'z'};
      
    private Set<String> SuggestionSet = new HashSet<String>();

    //consturcor accepts filename/path and will parse it 
    //will add the elements from the input file to a HashSet
    //it uses buffer to facillitate qucick access to data

    public SpellChecker(String filename) {

        DicName = filename;
        Scanner scanner = null;
        try {
        scanner = new Scanner(new BufferedReader(new FileReader(DicName)));
        while (scanner.hasNext()) {
            String NextWord = scanner.next().replaceAll("\\p{Punct}", "");
            NextWord = NextWord.toLowerCase();
            DicSet.add(NextWord);
            }
            scanner.close(); 
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//this method recives file and will scan for each elements in the 
//file to check against the provided dictionary 
//it similary uses buffer for quik data access

    public List<String> getIncorrectWords(String filename) {
        FileToBeChecked = filename;
        Scanner scanner = null;
        try {
scanner = new Scanner(new BufferedReader(new FileReader(FileToBeChecked)));
while (scanner.hasNext()) {
String NextWord = scanner.next().replaceAll("\\p{Punct}", "");

NextWord = NextWord.toLowerCase();
    if (!DicSet.contains(NextWord)) {
        ErrorList.add(NextWord);
             }
        }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ErrorList;
    }

//this method give plausibe word suggestion in reference to the 
//provided dictionary up on the initalization of the 
//object. it uses three diffent methods to suggest alternative 
//this method heavily relies on the StringBuilder method of 
// sice it is more memory efficinet way 

    public Set<String> getSuggestions(String word){

        StringBuilder inputWord = new StringBuilder(word);
        if (DicSet.contains(inputWord.toString())){
            System.out.println("Input is a correct word");
        }
        else {
            for (int i = 0; i < word.length(); i++) {
                for (int j = 0; j < alphabets.length; j++) {

    StringBuilder tempWord = new StringBuilder(inputWord);
    String NewSuggestion = String.valueOf(tempWord.insert(i, alphabets[j]));
                    if (DicSet.contains(NewSuggestion)) {
                        SuggestionSet.add(NewSuggestion);
                    }
                }
            }
            for (int i = 0; i < word.length(); i++) {
             StringBuilder tempWord = new StringBuilder(inputWord);
            String NewSuggestion = String.valueOf(tempWord.delete(i, i + 1));
                if (DicSet.contains(NewSuggestion)) {
                    SuggestionSet.add(String.valueOf(NewSuggestion));
                }
            }
            for (int i = 0; i < word.length()-1; i++) {
StringBuilder tempWord = new StringBuilder(inputWord);
StringBuilder WordSection = new StringBuilder(tempWord.substring(i, i + 2));
tempWord = tempWord.replace(i, i + 2, WordSection.reverse().toString());

                if (DicSet.contains(tempWord.toString())) {
                    SuggestionSet.add(String.valueOf(tempWord));
                }
            }
        }
        if(SuggestionSet.size() ==0){
            return null;
        }else return SuggestionSet;
    }
}