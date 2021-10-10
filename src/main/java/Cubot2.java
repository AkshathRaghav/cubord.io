import java.util.ArrayList;

public class Cubot2 extends Checker2 {

    public Cubot2(String[] s) throws IllegalArgumentException { super(s) ;}
    public boolean isSolved() { return super.isSolved() ;}
    public String toString() {
        return cubie.toString() ;
    }
    public String[][][][] getCube() {
        return cubie.getCube();
    }
    public String[][][][] getSolvedCube() { return super.getSolvedCube() ; }
    public ArrayList<String> compareToSolved()  { return super.compareToSolved() ; }
    public String stringAlg(String s) { return super.stringAlg(s) ; }
    public String getScramble(int n, boolean check) { return super.getScramble(n, check) ; }
    public String reverseAlg(String s, boolean check ) { return  super.reverseAlg(s, check ) ; }
    public String indexString() { return super.indexString() ; }
    public String solve() { return super.solve() ; }
    public String toarr() { return super.cubie.toarr(); }
    public boolean isValid() { return super.isValid();  }
}

