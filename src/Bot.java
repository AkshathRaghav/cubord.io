import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {
    public static JDA jda ;
    public static String prefix = ">cube" ;
    public static void main(String[] args) throws LoginException {
        jda = JDABuilder.createDefault("Insert Token Here") .build();
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.getPresence().setActivity(Activity.playing("with Cubes for you"));
        jda.addEventListener(new Commands());
        jda.addEventListener(new MakeCube());
//        jda.addEventListener(new Botmoves());

    }
}
