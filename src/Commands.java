import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] arg = event.getMessage().getContentRaw().split(" ") ;

        if (arg.length > 1 &&  (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "help") ) {
            EmbedBuilder info = new EmbedBuilder();

            info.setTitle("CUBORD COMMAND LIST", null);

             info.setColor(Color.red);
            info.setColor(new Color(0xF40C0C));
            info.setColor(new Color(255, 0, 54));
            info.addBlankField(false);

            info.addField(":c make", "Makes a cube for you, and returns if it was successful", false) ;
            info.addField(":c makeSolved", "Replaces your cube with a Solved cube", false) ;
            info.addField(":c makeSolved (-2x2/-3x3)", "Makes solved cube of given type", false) ;
            info.addField(":c type?", "Returns wether your cube is 2x2 or 3x3", false) ;


            info.addField(":c show", "Prints your cube", false) ;
            info.addField(":c indexes", "Sends you the indexed cube as a DM", false) ;

            info.addField(":c do -(<Insert moves>) -(s/d)", "Executes the moves. -s --> shows cube, -d --> doesn't show cube", false) ;
            info.addField(":c rev -(<Insert moves>) -(y/n)", "Reverses the moves. -y --> executes the reverse as well, -n --> just returns the reverse alg", false) ;
            info.addField(":c getStore", "Returns all the moves done on the Cube at the start of the session", false) ;


            info.addField(":c solve", "Solves the cube and returns the solution ( if something goes wrong, it returns why )", false) ;
            info.addField(":c solved?", "Returns state of Cube", false) ;

            info.addField(":c scramble -(<No. of Moves>) -(s/d)", "Gets you a scramble of requested length. -y --> executes the scramble, -d --> doesn't scramble your Cube \n Erases your store history", false) ;

            info.addField(":c s", "Starts stopwatch", false) ;
            info.addField(":c n", "Stops stopwatch. Approximates and returns your time.", false) ;
            info.addField(":c -t -(<time>)", "Enter <Time> and timer starts. Reminds you when the time's up.", false) ;

            info.addField(":c die", "Don't do this :/", false) ;

            info.addBlankField(false);
            info.addField("Watch this video for a demo - https://www.youtube.com/watch?v=xvFZjo5PgG0")
            info.addField("Cubord is an extension of the Cubot library for Discord", "Read more about it here - https://github.com/AkshathRaghav/cubot.io", false) ;

            info.setImage("https://cdn.discordapp.com/attachments/797420478574362634/855449686982787082/cubot.io.png");


            info.setFooter("<-- Requested by" + event.getMember().getEffectiveName(), event.getMember().getUser().getAvatarUrl()) ;
            event.getChannel().sendMessage(info.build()).queue();
            info.clear() ;
        }

    }
}
