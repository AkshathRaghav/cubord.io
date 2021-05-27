import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Botmoves extends ListenerAdapter {
    public static Cubot cube ;
    // RRRRRRRRR GGGGGGGGG OOOOOOOOO BBBBBBBBB WWWWWWWWW YYYYYYYYY

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw() ;
        String[] arg = event.getMessage().getContentRaw().split(" ");
        if (arg.length > 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "exalg") && arg[arg.length-1].equals(")")) {
            event.getChannel().sendMessage("Moves Executed : " + cube.stringalg(content.substring(content.indexOf("(")+1, content.indexOf(")"))) + " " +  event.getMember().getAsMention() ).queue();
        }

    }
}
