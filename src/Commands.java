import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] arg = event.getMessage().getContentRaw().split(" ") ;

        if (arg.length > 1 &&  (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "help") ) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Help Desk!");
            info.setDescription("Here are the things Cubot can do for you");
            info.setColor(0Xa80d2c) ;
            info.addBlankField(true);
            info.addField(":c make", "Makes a cube for you, and returns if it was successful", false) ;
            info.addField(":c show", "Prints your cube", false) ;
            info.addField(":c do -(<Insert moves>) -(s/d)", "Executes the moves. -s --> shows cube, -d --> doesn't show cube", false) ;
            info.addField(":c solve", "Solves the cube and returns the solution ( if something goes wrong, it returns why )", false) ;
            info.addField(":c rev -(<Insert moves>) -(y/n)", "Reverses the moves. -y --> executes the reverse as well, -n --> just returns the reverse alg", false) ;
            info.addField(":c s", "Starts stopwatch", false) ;
            info.addField(":c n", "Stops stopwatch. Approximates and returns your time.", false) ;
            info.addField(":c -t -(<Time>)", "Enter <Time> and timer starts. Reminds you when the time's up.", false) ;
            info.addField(":c solved", "Tells you if you have solved it or not", false) ;
            info.addField(":c indexes", "Sends you the indexed cube through DM", false) ;


            info.setFooter("<-- Requested by" + event.getMember().getEffectiveName(), event.getMember().getUser().getAvatarUrl()) ;
            event.getChannel().sendMessage(info.build()).queue();
            info.clear() ;
        }
        
    }
}
