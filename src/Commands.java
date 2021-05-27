import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public static Cubot cube ;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] arg = event.getMessage().getContentRaw().split(" ") ;

        if (arg.length > 1 &&  (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "help") ) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Help Desk!");
            info.setDescription("Here are the things Cubot can do for you");
            info.setColor(0Xa80d2c) ;
            info.addBlankField(true);

            info.addField(">cube make", "Makes a cube for you, and returns if it was successful", false) ;
            info.addBlankField(true);

            info.setFooter("<-- Requested by", event.getMember().getUser().getAvatarUrl()) ;
            event.getChannel().sendMessage(info.build()).queue();
            info.clear() ;
        }
        else if ( (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "hey") ){
            event.getChannel().sendMessage("Hey, " + event.getMember().getEffectiveName()).queue();
        }

    }
}
