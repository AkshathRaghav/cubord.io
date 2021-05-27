import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MakeCube extends ListenerAdapter {
    public static Cubot cube ;
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] arg = event.getMessage().getContentRaw().split(" ");
        if (arg.length == 8 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "make")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Here is your Cube");
            info.setColor(0Xa80d2c);
            arg = new String[]{arg[2], arg[3], arg[4], arg[5], arg[6], arg[7]};
            cube = new Cubot(arg);
            String temp = cube.toString();
            info.addBlankField(true);
            String cube1 = temp.substring(0, temp.indexOf("Middle"));
            String cube2 = temp.substring(temp.indexOf("Middle"), temp.indexOf("Right"));
            String cube3 = temp.substring(temp.indexOf("Right"));
            info.addField("Left Coloumn", cube1, false);
            info.addBlankField(true);

            info.addField("Middle Coloumn", cube2, false);
            info.addBlankField(true);

            info.addField("Right Coloumn", cube3, false);
            info.addBlankField(true);

            info.setFooter("<-- Requested by", event.getMember().getUser().getAvatarUrl());
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }
}
