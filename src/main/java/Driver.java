import net.codejava.Update;

public class    Driver {
    public static void main(String[] args) {
        String[] oldTime ="02:12:00".split(":") ;
        String[] givenTime = "09:00:00".split(":") ;
        String newTime = "" ;
        for (int i = 0  ; i < oldTime.length; i++ ) {
            newTime += (Integer.parseInt(oldTime[i]) + Integer.parseInt(givenTime[i])) + ":";
        }
        System.out.println(newTime);
    }

}
