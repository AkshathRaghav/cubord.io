public class Driver {
    public static void main(String[] args) {
        String[] temp =  {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
        Cube3d cube = new Cube3d(temp)  ;
//        System.out.println(cube.isSolved());
        System.out.println(cube.toarr());
    }
}
