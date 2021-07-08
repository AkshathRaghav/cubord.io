import java.util.ArrayList;

public class Checker2d extends Moves2d{
    private final String[] temp =  {"RRRR", "GGGG", "OOOO", "BBBB", "WWWW", "YYYY"};
    private final Cube2 end = new Cube2(temp) ;
    private final String[] movesString = {"R", "R'", "L", "L'", "U", "U'", "D", "D'", "F", "F'", "B" , "B'", "U2", "F2", "R2", "L2",  "M2"} ;
    public Checker2d(String[] s) throws IllegalArgumentException {
        super(s);
    }
    public String toarr() { return super.toarr() ; }
    public boolean isSolved() {
        return cubie.solved(end) ;
    }
    public String[][][][] getSolvedCube() {
        return end.getCube() ;
    }
    public ArrayList<String> compareToSolved() {
        String[][][][] endarray = end.getCube() ;
        ArrayList<String> change = new ArrayList<String>() ;
        for (int j = 0; j < cubearray.length; j++) {
            for (int i = 0; i < cubearray.length; i++) {
                for (int k = 0; k < cubearray.length; k++) {
                    String a, x, c;
                     if ( !( cubearray[i][j][k][1].equals(endarray[i][j][k][1]) && cubearray[i][j][k][2].equals(endarray[i][j][k][2]) || cubearray[i][j][k][0].equals(endarray[i][j][k][0]) )) { change.add("( " +i + " " + j + " " + k + " : T/b - " + endarray[i][j][k][0] + " . Side - " + endarray[i][j][k][1]  + " . F/B - " + endarray[i][j][k][2] + " ) " ) ;
                    }
                }
            }
        }
        return change ;
    }
    public String getScramble(int n, boolean check) {
        StringBuilder s = new StringBuilder(" ");
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * movesString.length);
            s.append(movesString[x]).append(" ");
        }
        if (check) {
            return stringAlg(s.toString());
        }
        else { return s.toString() ; }
    }

    // If you add non-executable moves, Cubot will ignore
    public String stringAlg(String str) {
        String s = str.trim();
        while (str.contains(" ")) {
            choose(str.substring(0, str.indexOf(" ")));
            str = str.substring(str.indexOf(" ") +1 ) ;
        }
        choose(str);
        return s ;

    }
    public String reversealg(String str, boolean check) {
        String s = "" ;
        while (str.contains(" ")) {
            s = reversedo(str.substring(0, str.indexOf(" "))) + " " + s ;
            str = str.substring(str.indexOf(" ") +1 ) ;
        }
        s = reversedo(str) + " " + s ;
        if (check) {
            stringAlg(s);
        }
        return s;
    }

    /**
     * Helper method for {@link #bottom()}
     * <br>
     * slots the piece at [i][j][k][color] in the bottom layer
     *
     * @param i      1st Dimensional Index
     * @param j      2nd Dimensional Index
     * @param k      3rd Dimensional Index
     * @param color  4th Dimensional Index
     * @return       String containing solution for each piece in this phase of {@link #solve()}
     */
    public String slotter(int i , int j, int k, int color) {
        String s = "" ;
        if ( i == 1 ) {
            if (color == 1) {
                if (j == 0) {
                    if (k == 0) {
                        s += Fdash() + " " ;
                        if (cubearray[0][1][0][1].equals("W")) {
                            s += stringAlg("L' U L") + " ";
                        }
                    } else {
                        s += B() + " " ;
                        if (cubearray[0][1][1][1].equals("W")) {
                            s += stringAlg("L U' L'")+ " ";
                        }
                    }
                }
                else {
                    if (k == 0) {
                        s += F() + " " ;
                        if (cubearray[0][0][0][1].equals("W")) {
                            s += stringAlg("R U' R'")+ " ";
                        }
                    } else {
                        s += Bdash() + " " ;
                        if (cubearray[0][0][1][1].equals("W")) {
                            s += stringAlg("R' U R")+ " ";
                        }
                    }
                }
            }
            else {
                if (j == 0) {
                    if (k == 0) {
                        s += L() + " " ;
                        if (cubearray[0][0][1][2].equals("W")) {
                            s += stringAlg("F U' F'")+ " ";
                        }
                    } else {
                        s += Ldash() + " " ;
                        if (cubearray[0][0][0][2].equals("W")) {
                            s += stringAlg("B' U B")+ " ";
                        }
                    }
                }
                else {
                    if (k == 0) {
                        s += Rdash() + " " ;
                        if (cubearray[0][1][1][2].equals("W")) {
                            s += stringAlg("F' U F")+ " ";
                        }
                    } else {
                        s += R() + " " ;
                        if (cubearray[0][1][0][2].equals("W")) {
                            s += stringAlg("B U' B")+ " ";
                        }
                    }
                }
            }
        }
        else {
            while ( !(cubearray[0][1][0][1].equals("W") ||  cubearray[0][1][0][2].equals("W") ||  cubearray[0][1][0][0].equals("W")) ) { s += U() + " "  ; }
            while ( (cubearray[1][1][0][0].equals("W")) ) { s += D() + " " ; }

            if ( cubearray[0][1][0][1].equals("W") ) {
                s += stringAlg("R U R'") + " " ;
            }
            else if ( cubearray[0][1][0][2].equals("W") ) {
                s += stringAlg("U R U' R'") + " " ;
            }
            else {
                s += stringAlg("R U R'") + " "  + slotter(1, 1, 0, 1) + " " ;
            }
        }
        return s  ;
    }

    /**
     * Checks if the bottom layer is filled with white pieces
     *
     * @return   boolean : <br> true if {@link #bottom()} has executed correctly, false otherwise
     */
    public boolean bottomFilled() {
        int cnt = 0  ;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (cubearray[1][i][j][0].equals("W")) {
                    cnt++;
                }
            }
        }
        if (cnt == 4) {
            return true;
        }
        return false;
    }

    /**
     * Fills up the bottom layer with white pieces - First step of Ortega
     *
     * @return  String containing solution for this phase of {@link #solve()}
     */
    public String bottom() {
        String s = "" ;
        while ( !bottomFilled() ) {
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (cubearray[k][i][j][2].equals("W")) { s += slotter(k, i, j, 2); }
                        else if (cubearray[k][i][j][1].equals("W")) { s += slotter(k, i, j, 1); }
                        else if (cubearray[0][i][j][0].equals("W")) { s += slotter(0, i, j, 1);}
                    }
                }
            }
        }
        return s;
    }

    /**
     * Tells us the number of pieces on top layer with Yellow facing upwards
     *
     * @return   int : number of pieces with Yellow on top
     */
    public int ollTop() {
        int top = 0 ;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (cubearray[0][i][j][0].equals("Y")) {
                    top++;
                }
            }
        }
        return top;
    }

    /**
     * Tells us the number of pieces on top layer with Yellow facing sideways/frontwards
     *
     * @return   int : number of pieces with Yellow on the side/front
     */
    public int ollSide() {
        int side = 0 ;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (cubearray[0][i][j][1].equals("Y") || cubearray[0][i][j][2].equals("Y")) {
                    side++;
                }
            }
        }
        return side;
    }

    /**
     * Orients all the pieces with Yellow on top, in the top layer
     *
     * @return   String containing solution for this phase of {@link #solve()}
     */
    public String oll() {
        String s = "";
        int side = ollSide(), top = ollTop();
        if (side == 3 && top == 1) {
            while (!cubearray[0][0][0][0].equals("Y")) {
                s += U() + " ";
            }
            if (cubearray[0][0][1][1].equals("Y")) {
                s += stringAlg("U U R U2 R' U' R U' R'") + " ";
            } else {
                s += stringAlg("R U R' U R U2 R'") + " ";
            }
        } else if (side == 2 && top == 2) {
            while (!(cubearray[0][0][1][0].equals("Y"))) {
                s += U() + " ";
            }
            if (cubearray[0][1][0][0].equals("Y")) {
                while ( !(cubearray[0][0][1][0].equals("Y") && cubearray[0][0][0][2].equals("Y"))) { s += U() + " "; }
                s += stringAlg("F R U' R' U' R U R' F'") + " ";
            } else {
                while (!(cubearray[0][0][1][0].equals("Y") && cubearray[0][1][1][0].equals("Y"))) {
                    s += U() + " ";
                }
                if (cubearray[0][0][0][2].equals("Y")) {
                    s += stringAlg("U F R U R' U' F'") + " ";
                } else {
                    s += stringAlg("U R U R' U' R' F R F'") + " ";
                }
            }
        } else if (side == 4) {
            while (!(cubearray[0][0][0][2].equals("Y") && cubearray[0][1][0][2].equals("Y"))) {
                s += U() + " ";
            }
            if (cubearray[0][0][1][2].equals("Y")) {
                s += stringAlg("R2 U2 R U2 R2") + " ";
            } else {
                s += stringAlg("U R U2 R2 U' R2 U' R2 U2 R") + " ";
            }
        } else if ( !(top == 4)){ s += "One of the cube pieces must be overturned"; }
        return s ;
    }

    /**
     * Checks if the side/front colors of each piece in the bottom layer is solved
     *
     * @return   boolean : true if solved, false otherwise
     */
    public boolean bottomSolved() {
        String[][][][] endArr = end.getCube() ;
        int count  =0 ;
        while (!endArr[1][0][0][2].equals(cubearray[1][0][0][2])) { count++;  D(); }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (!(endArr[1][i][j][0].equals(cubearray[1][i][j][0]) && endArr[1][i][j][1].equals(cubearray[1][i][j][1]) && endArr[1][i][j][2].equals(cubearray[1][i][j][2]))) {
                    return false;
                }
            }
        }
        for (int i  =0 ; i < count ; i++ ) { Ddash() ; }
        return true ;
    }

    /**
     * Checks if there is a bar ( 2 pieces with same color in the 4th Dimensional Index ) in the top layer
     *
     * @return    boolean : true if there is a bar, false otherwise
     */
    public boolean topBar() {
        int[] indexes = {0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0 ,0 ,0};
        boolean check = false;
        for (int i = 0; i < indexes.length - 2; i += 4) {
            if (!check) {
                if (cubearray[0][indexes[i]][indexes[i + 1]][1].equals(cubearray[0][indexes[i + 2]][indexes[i + 3]][1])) { return true ; }
            } else {
                if (cubearray[0][indexes[i]][indexes[i + 1]][2].equals(cubearray[0][indexes[i + 2]][indexes[i + 3]][2])) { return true ; }
            }
            check = !check;
        }
        return false ;
    }

    /**
     * Checks if there is a bar ( 2 pieces with same color in the 4th Dimensional Index ) in the bottom layer
     *
     * @return    boolean : true if there is a bar, false otherwise
     */
    public boolean bottomBar() {
        int[] indexes = {0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0,0 };
        boolean check = false;
        for (int i = 0; i < indexes.length - 2; i += 4) {
            if (!check) {
                if (cubearray[1][indexes[i]][indexes[i + 1]][1].equals(cubearray[1][indexes[i + 2]][indexes[i + 3]][1])) { return true ; }
            } else {
                if (cubearray[1][indexes[i]][indexes[i + 1]][2].equals(cubearray[1][indexes[i + 2]][indexes[i + 3]][2])) { return true ; }
            }
            check = !check;
        }
        return false ;
    }

    /**
     * The last phase of the {@link #solve()}
     *
     * @return   String containing solution for this phase of {@link #solve()}
     */
    public String pll() {
        String s = "" ;
        boolean topBar = topBar() , bottomBar = bottomBar() ;

        if ( this.isSolved() ) { return "" ; }
        else if ( bottomSolved() ) {
            if (!topBar) {
                s += stringAlg("R U' R' U' F2 U' R U R' D R2");
            } else {
                while (!cubearray[0][0][0][1].equals(cubearray[0][0][1][1])) {
                    s += U() + " ";
                }
                s += stringAlg("R U2 R' U' R U2 L' U R' U' L") ;
            }
        }
        else if ( topBar ) {
            while (!cubearray[0][0][0][2].equals(cubearray[0][1][0][2])) {
                s += U() + " ";
            }
            if ( bottomBar ) {
                while (!cubearray[1][0][0][2].equals(cubearray[1][1][0][2])) {
                    s += D() + " ";
                }
                System.out.println(toString());

                s += stringAlg("R2 U' B2 U2 R2 U' R2");
            }
            else {
                s += stringAlg("R U' R F2 R' U R'");
            }
        }
        else if ( !topBar && !bottomBar ) {
            s += stringAlg("R2 F2 R2");
        }
        else if ( bottomBar && !topBar ) {
            while (!cubearray[1][0][0][2].equals(cubearray[1][1][0][2])) {
                s += D() + " ";
            }
            s += stringAlg("R U2 R' U' R U2 L' U R' U' L");
            s += pll();
        }

        return s ;
    }

    /**
     * Solves the {@code Cube2} using the Ortega Method and orients it with Green side facing forward
     *
     * @throws Error     If Cubot is unable to solve the cube, then
     *                   an CubeError will be raised
     *                   <br>
     *                   Common causes are :
     *                   <br>
     *                   Input Error
     *                   <br>
     *                   Cube piece is twisted
     * @return String containing the solution
     */
    public String solve() throws Error {
        String s =  bottom() + oll() + pll();
        int count =0 ;
        while ( !(cubearray[0][0][0][2].equals(cubearray[1][0][0][2]) &&  cubearray[0][1][0][2].equals(cubearray[1][1][0][2]))) {
            s += " " + U()  ;
            count++ ;
            if ( count > 3 ) { throw new Error("CubeError : Unable to finish solve") ; }
        }
        while ( !(cubearray[0][0][0][2].equals("G") && cubearray[0][1][0][2].equals("G") ) ) {  U();  Ddash() ; }
        return shorten(s).trim();
    }
    /**
     * Shortens the String given to it as an input
     *
     * @param s     String input to be shortened
     * @return      String containing the shortened String input by a few moves
     */
    private String shorten(String s ) {
        s = s.replaceAll("U U U U", "" ) ;
        s = s.replaceAll("U U U" , "U'") ;
        s = s.replaceAll("D D D D", "" ) ;
        s = s.replaceAll("D D D" , "D'") ;

        return s ;
    }

    private void choose(String ch) {
        switch (ch) {
            case "R" -> R();
            case "R'" -> Rdash();
            case "L'" -> Ldash();
            case "L" -> L();
            case "U" -> U();
            case "U'" -> Udash();
            case "F" -> F();
            case "F'" -> Fdash();
            case "B" -> B();
            case "B'" -> Bdash();
            case "D" -> D();
            case "D'" -> Ddash();
            case "R2" -> R2();
            case "L2" -> L2();
            case "U2", "U2'" -> U2();
            case "F2" -> F2();
            case "B2" -> B2();
            case "D2" -> D2();

        }
    }
    private String reversedo(String s) {
        String monthString ="" ; // random thing i copied, just rename later
        switch (s) {
            case "R":  monthString = "R'";
                break;
            case "R'":  monthString = "R";
                break;
            case "L":  monthString = "L'";
                break;
            case "L'":  monthString = "L";
                break;
            case "U":  monthString = "U'";
                break;
            case "U'":  monthString = "U";
                break;
            case "D":  monthString = "D'";
                break;
            case "D'":  monthString = "D";
                break;
            case "F":  monthString = "F'";
                break;
            case "F'": monthString = "F";
                break;
            case "B": monthString = "B'";
                break;
            case "B'": monthString = "B";
                break;

            default: monthString = s ;
        }
        return monthString ;


    }
    public boolean isValid() {
        return cubie.isValid() ;
    }

    public String indexString() {
        return cubie.indexedString();
    }
}
