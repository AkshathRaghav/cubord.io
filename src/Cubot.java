import java.util.ArrayList;

public class Cubot {
    private Cubot3d cube3;
    private Cubot2d cube2;
    private final String[] end3 = {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
    private final String[] end2  = {"RRRR", "GGGG", "OOOO", "BBBB", "WWWW", "YYYY"};

    public Cubot(String[] s) throws IllegalArgumentException {
        if (s[0].length() == 9) { cube3 = new Cubot3d(s) ; }
        else { cube2 = new Cubot2d(s) ; }
    }

    public String solve() {
        if (cube2 == null && cube3 != null) {
            return cube3.solve();
        }
        return cube2.solve();
    }


    public boolean isSolved() {
        if (cube2 == null && cube3 != null) {
            return cube3.isSolved();
        }
        return cube2.isSolved();
    }

    public String toString() {
        if (cube2 == null && cube3 != null) {
            return cube3.toString();
        }
        return cube2.toString();
    }

    public String[][][][] getCube() {
        if (cube2 == null && cube3 != null) {
            return cube3.getCube();
        }
        assert cube2 != null;
        return cube2.getCube();
    }

    public String[][][][] getSolvedCube() {
        if (cube2 == null && cube3 != null) {
            return cube3.getSolvedCube();
        }
        return cube2.getSolvedCube();
    }

    public ArrayList<String> compareToSolved() {
        if (cube2 == null && cube3 != null) {
            return cube3.compareToSolved();
        }
        assert cube2 != null;
        return cube2.compareToSolved();
    }

    public String stringalg(String s) {
        if (cube2 == null && cube3 != null) {
            return cube3.stringAlg(s);
        }
        assert cube2 != null;
        return cube2.stringAlg(s);
    }

    public String getScramble(int n, boolean check) {
        return cube3.getScramble(n, check );
    }

    public String reversealg(String s, boolean check) {
        if (cube2 == null && cube3 != null) {
            return cube3.reverseAlg(s, check);
        }
        assert cube2 != null;
        return cube2.reverseAlg(s, check);
    }

    public String indexString() {
        if (cube2 == null && cube3 != null) {
            return cube3.indexString();
        }
        assert cube2 != null;
        return cube2.indexString();
    }
    public boolean isValid() {
        if (cube3 != null) {
            return cube3.isValid();
        }
        return cube2.isValid();
    }
    public void makeSolved() {
        if (cube2 == null && cube3 != null) {
            cube3 = new Cubot3d(end3) ;
        }
        assert cube2 != null;
        cube2 = new Cubot2d(end2) ;
    }

}
