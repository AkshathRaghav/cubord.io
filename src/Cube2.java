/**
 * Cube2 class creates and handles the
 * basic output and methods which do not involve
 * changing the cube[][][][] or Cube2 object
 *
 * @author  Akshath Raghav
 * @version 2.0
 * @since   2021-06-15
 * @see <a href="https://github.com/AkshathRaghav/cubot.io">cubot.io</a>
 */
public class Cube2 {
    private final String[][][][] cube;
    private int w = 0, g = 0, r = 0, o = 0, b = 0, y = 0;

    /**
     * Constructs a {@code Cube2} and stories it in the cube[][][][] array
     *
     * @param temp   String[] input from user containing the colors on each side
     * @exception    IllegalArgumentException on input error
     * @see          IllegalArgumentException
     * {@link #isValid()}
     * {@link #checker(String)}
     * */
    public Cube2(String[] temp) throws IllegalArgumentException {
        cube = new String[2][2][2][3];
        String[][] storing = new String[6][4];
        for (int i = 0; i < 6; i++) {
            if (temp[i].length() != 4) {
                throw new IllegalArgumentException("InputError");
            }
            try {
                for (int j = 0; j < 4; j++) {
                    storing[i][j] = temp[i].substring(j, j + 1);
                }
            } catch (StringIndexOutOfBoundsException e) {throw new IllegalArgumentException("InputError"); }
        }
        String[] finalcolors = { storing[5][2], storing[0][1], storing[1][0], storing[5][0], storing[0][0],
                storing[3][1], storing[4][0], storing[0][3], storing[1][2], storing[4][2], storing[0][2], storing[3][3],
                storing[5][3], storing[2][0], storing[1][1], storing[5][1], storing[2][1], storing[3][0], storing[4][1],
                storing[2][2], storing[1][3], storing[4][3], storing[2][3], storing[3][2] };
        int count = 0;
        for (int j = 0; j < cube.length; j++) {
            for (int i = 0; i < cube.length; i++) {
                for (int k = 0; k < cube.length; k++) {
                    cube[i][j][k][0] = finalcolors[count];
                    cube[i][j][k][1] = finalcolors[count + 1];
                    cube[i][j][k][2] = finalcolors[count + 2];
                    count += 3;
                }
            }
        }
    }

    /**
     * Private method to check if {@code Cube2} is made without any error
     *
     * @return true, if everything is fine. false, otherwise
     * {@link #checker(String)}
     */
    boolean isValid() {
        for (int j = 0; j < cube.length; j++) {
            for (int i = 0; i < cube.length; i++) {
                for (int k = 0; k < cube.length; k++) {
                    checker(cube[i][j][k][1]);
                    checker(cube[i][j][k][2]);
                    checker(cube[i][j][k][0]);
                }
            }
        }
        return (w == 4) && (g == 4) && (r == 4) && (o == 4) && (b == 4) && (y == 4);
    }

    /**
     * adds 1 to each color variable ( an instance of the object ) depending on the input
     *
     * @param s  One of the 6 colors
     */
    private void checker(String s) {
        switch (s) {
            case "W" -> w++;
            case "G" -> g++;
            case "R" -> r++;
            case "O" -> o++;
            case "B" -> b++;
            case "Y" -> y++;
        }
    }

    /**
     * Gives a String[] object which can be used to make other {@code Cube2} or {@code Cubot} objects
     *
     * @return String[], as defined above
     */
    public String[] cubeToArr() {
        String s = "" ;
        String store = toString() ;
        String[] storeArr = store.split("\n"), charStore = new String[6];
        for (int i =0 ; i < storeArr.length; i++ ) {
            if (i > 3 || i < 2 ) {
                String temp = storeArr[i].substring(storeArr[i].indexOf("|") + 1) ;
                int index = 5 ;
                if ( i > 3  ) { index = 4 ;}
                charStore[index] += temp.substring(0, temp.indexOf("|")).replaceAll(" ", "");
            }
            else {
                String[] temp  = storeArr[i].split(" | ");
                int count = -1 ;
                for (String k : temp) {
                    if (k.equals("|")) { count ++ ; }
                    else {
                        charStore[count] += k;
                    }
                }
            }
        }
        for (int i = 0; i < charStore.length; i++ ) {
            charStore[i] = charStore[i].replaceAll("null", " ").substring(1);
        }
        return charStore ;
    }

    /**
     * Checks if the cube is solved with respect to another cube ( of the same type )
     *
     * @param other     The {@code Cube2} object to be compared to
     * @return          true, if solved. false, otherwise
     */
    public boolean solved(Cube2 other) {
        for (int j = 0; j < cube.length; j++) {
            for (int i = 0; i < cube.length; i++) {
                for (int k = 0; k < cube.length; k++) {
                    if (!(cube[i][j][k][1].equals(other.cube[i][j][k][1]) && cube[i][j][k][2].equals(other.cube[i][j][k][2]) && cube[i][j][k][0].equals(other.cube[i][j][k][0]))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * Gives a String with the indexes as well as the colors on each side of each cube piece, or cubie for short
     *
     * @return String
     */
    public String indexString() {
        String s = "" ;
        String[] lis = {"Left",  "Right"};
        for (int j = 0; j < cube.length; j++) {
            s += lis[j] + " : From top row to bottom row - " + "\n --------------------------------------";
            for (int i = 0; i < cube.length; i++) {
                s += "\n";
                for (int k = 0; k < cube.length; k++) {
                    s += i + " " + j + "  " + k + ":" + "\t\t|\t\t";
                    s += "T/b: " + cube[i][j][k][0] + " F/b: " + cube[i][j][k][2] + " Side: " + cube[i][j][k][1] + "\t\t|\t\t";
                }
                s += "\n";
            }
            s += " -------------------------------------- \n\n\n";
        }
        return s ;
    }


    /**
     * prints the Cube2 as if all faces have been opened up while facing upwards, with Green face in the center
     *
     * @return String containing the same
     */
    public String toString() {
        String s = "";
        for (int count  =0 ; count < 3 ; count++) {
            for (int i =0 ; i < 2 ; i++ ) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 2; k++) {
                        if ( count != 1  ) {
                            if (j == 1) {
                                if ( count == 0 ) {
                                    s += cube[0][k][1 - i][0].charAt(0) ;
                                }
                                else if ( count == 2){
                                    s += cube[1][k][i][0].charAt(0);
                                }
                            }
                            else {
                                s += "i";
                            }
                        }
                        else {
                            if (j == 0) {
                                s += cube[i][0][1 - k][1].charAt(0) ;
                            } else if (j == 1) {
                                s += cube[i][k][0][2].charAt(0);
                            } else if (j == 2) {
                                s += cube[i][1][k][1].charAt(0) ;
                            } else {
                                s += cube[i][1 - k][1][2].charAt(0) ;
                            }
                        }
                    }
                    s += "  ";
                }
                s += "\n";

            }
        }
        return s;
    }
    /**
     * Used to get a String[][][][] which contains the {@code Cube2} in its base form
     *
     * @return String[][][][]
     */
    public String[][][][] getCube() {
        return cube;
    } // returns the 4-D cube array


    public String toarr() {
        String s = "" ;
        String store = toString() ;
        String[] storeArr = store.split("\n"), charStore = new String[6];
        for (int i =0 ; i < storeArr.length; i++ ) {
            if (i < 2 || i >3 ) {
                String temp = storeArr[i].replaceAll("i", "");
                int index = 5 ;
                if ( i > 3  ) { index = 4 ;}
                charStore[index] += temp.replaceAll(" ", "");
            }
            else {
                String[] temp  = storeArr[i].split("  ");
                int secount = 0 ;
                for (String k : temp) {
                    if (k.equals("ii")) {  secount = 0; }
                    else {
                        charStore[secount] += k;
                        secount++ ;
                    }
                }
            }

        }
        for (String j : charStore) {
            s += j.replaceAll("null", " ");
        }
        return s.substring(1) ;
    }
}


