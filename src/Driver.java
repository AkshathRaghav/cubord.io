public class Driver {
    public static void main(String[] args) {
//        String[] temp =  {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
        String[] temp2 =  {"RRRR", "GGGG", "OOOO", "BBBB", "WWWW", "YYYY"};
        Cubot cube = new Cubot(temp2) ;
//        System.out.println(cube.isSolved());
        cube.stringalg("R U");
//        cube.stringalg("U' R'") ;
//        cube.reversealg("R U", true) ;
        System.out.println(cube);
        System.out.println(cube.toarr());
    }

}
