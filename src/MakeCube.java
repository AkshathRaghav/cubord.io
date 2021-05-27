import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MakeCube extends ListenerAdapter {
    public static Cubot cube ;
    // RRRRRRRRR GGGGGGGGG OOOOOOOOO BBBBBBBBB WWWWWWWWW YYYYYYYYY
    public static String store = "" ;
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw() ;
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
//        String[] arg = event.getMessage().getContentRaw().split(" ");
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "Alg") ) {
            if ( arg[arg.length-1].substring(arg[arg.length-1].length()-1).equals(")")) {
                if (cube != null) {
//                    event.getChannel().sendMessage(  content.substring(content.indexOf("(") + 1, content.indexOf(")")) + " "  + event.getMember().getAsMention()).queue();

                    String s = cube.stringalg(content.substring(content.indexOf("(") + 1, content.indexOf(")"))) ;
                     store += s ;
                    event.getChannel().sendMessage("Moves Executed : " + s + " " + event.getMember().getAsMention()).queue();
                } else {
                    event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
                }
            }
            else {
                event.getChannel().sendMessage("Add an alg for me to execute!"   + event.getMember().getAsMention()).queue();


            }

        }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "solveCube") ) {
            if (cube != null) {
                if ( store.isEmpty()) {
                    event.getChannel().sendMessage(cube.solve() + " " + event.getMember().getAsMention()).queue();
                }
                else {
                    String s = cube.reversealg(store);
                    event.getChannel().sendMessage("Solution : " + cube.stringalg(s) + " " + event.getMember().getAsMention()).queue();
                }
            } else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
        }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "isSolved") ) {
            if (cube != null) {
                String s = "";
                if (cube.isSolved()) {
                    s = "Solved" ; }
                else { s = "Not Solved" ; }
                event.getChannel().sendMessage("Your Cube is " + s + ", " + event.getMember().getAsMention()).queue();

            } else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
        }

    }
}
