package sample;

import java.util.Comparator;

public class Word {
    private String word_target;
    private String word_explain;
    public Word() {
    }
    public String getWord_target() {
        return word_target;
    }
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }
    public String getWord_explain() {
        return word_explain;
    }
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
    public static Comparator<Word> Word_targetComparator = (Word s1, Word s2) -> {
        String WordName1 = s1.getWord_target().toUpperCase();
        String WordName2 = s2.getWord_target().toUpperCase();

        //ascending order
        return WordName1.compareTo(WordName2);

        //descending order
        //return StudentName2.compareTo(StudentName1);
    };

}
