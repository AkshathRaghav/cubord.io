import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import net.dv8tion.jda.api.EmbedBuilder;
        import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
        import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Filter extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] arg = event.getMessage().getContentRaw().split(" ") ;

        if ( (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "filter") ) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Help Desk!");
            info.setDescription("Here are the things Cubot can do for you");
            info.setColor(0Xa80d2c) ;
            info.addBlankField(true);

            info.addField(">cube hey", "Sends a hey back to you", false) ;
            info.addBlankField(true);

            info.setFooter("<-- Requested by", event.getMember().getUser().getAvatarUrl()) ;
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(info.build()).queue();
//            info.clear() ;
        }
//        else if ( (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "hey") ) {
//            event.getChannel().sendMessage("Hey Daddy, " + event.getMember().getEffectiveName()).queue();
//        }

    }
}
