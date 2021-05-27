import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {
    public static JDA jda ;
    public static String prefix = ">cube" ;
    public static void main(String[] args) throws LoginException {
        jda = JDABuilder.createDefault("ODQ3MTExNDEwNjE3Njc5ODk0.YK5T-g.X0Madg30Dr2_dOvolYI2-EKevoQ") .build();
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.getPresence().setActivity(Activity.playing("with Cubes for you"));
        jda.addEventListener(new Commands());
        jda.addEventListener(new MakeCube());
    }
}
