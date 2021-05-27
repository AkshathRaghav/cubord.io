import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MakeCube extends ListenerAdapter {
    public static Cubot cube ;
    // RRRRRRRRR GGGGGGGGG OOOOOOOOO BBBBBBBBB WWWWWWWWW YYYYYYYYY

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] arg = event.getMessage().getContentRaw().split(" ");
        if (arg.length == 8 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "make")) {

            arg = new String[]{arg[2], arg[3], arg[4], arg[5], arg[6], arg[7]};
            boolean check = true;
            for (String i : arg) {
                if (i.length() != 9) {
                    check = false;
                    break;
                }
            }
            if (check) {
                cube = new Cubot(arg);
                if (!cube.isValid()) {
                    cube = null;
                    event.getChannel().sendMessage("Hey " + event.getMember().getEffectiveName() + ", you have entered the cube incorrectly ( Wrong colors )").queue();

                } else {
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ",  I've made a cube for you!").queue();

                }
            }
            else {
                event.getChannel().sendMessage("Hey " + event.getMember().getEffectiveName() + ", your cubearray is wrong man.").queue();

            }
        }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "show") ) {
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(0Xa80d2c);
            String temp = cube.toString();
            String cube1 = temp.substring(0, temp.indexOf("Middle"));
            String cube2 = temp.substring(temp.indexOf("Middle"), temp.indexOf("Right"));
            String cube3 = temp.substring(temp.indexOf("Right"));
            info.addField("", cube1, false);
            info.addBlankField(true);

            info.addField("", cube2, false);
            info.addBlankField(true);

            info.addField("", cube3, false);
            info.addBlankField(true);

            info.setFooter("<--" + event.getMember().getEffectiveName() + "'s Cube", event.getMember().getUser().getAvatarUrl());
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }

    }
}
