import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MakeCube extends ListenerAdapter {
    String[] temp3 = {"RRRRRRRRR", "OOOOOOOOO", "WWBWWBWWB", "YBBYBBYBB", "GGWGGWGGW", "YYGYYGYYG"};
    public  Cubot cube =  new Cubot(temp3) ;
        // RRRRRRRRR GGGGGGGGG OOOOOOOOO BBBBBBBBB WWWWWWWWW YYYYYYYYY
    public  String store = "" ;
    public boolean solvedatfirst ;
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
//                if (!cube.isValid()) {
//                    cube = null;
//                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", you have entered the cube incorrectly ( Wrong colors )").queue();
//                } else {
//                    if ( cube.isSolved() ) { solvedatfirst = true ; }
//
//                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ",  I've made a cube for you!").queue();
//                }
            }
            else {
                event.getChannel().sendMessage("Hey " + event.getMember().getEffectiveName() + ", your cubearray is wrong man.").queue();

            }
        }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "show") ) {
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(0Xa80d2c);
                String temp = cube.toString(), cubes = "", temp2 = "";
//                while (temp.contains("| Y")) {
//                    temp2 = temp.substring(0, temp.indexOf("| Y"));
//                    cubes += "\t\t" + temp2 ;
//                    temp = temp.substring(temp.indexOf("| Y"));
//                }
//                cubes += temp.substring(0, temp.indexOf("B |")) ;
//                while (temp.contains("| W")) {
//                    temp2 = temp.substring(0, temp.indexOf("| W"));
//                    cubes += "  " + temp.substring(0, temp.indexOf("| W"));
//                    temp = temp.substring(temp.indexOf("| W"));
//                }

//            event.getChannel().sendMessage("      " + temp + event.getMember().getAsMention()).queue();
//            String cube1 = temp.substring(0, temp.indexOf("| Y"));
////            String cube2 = temp.substring(temp.indexOf("Middle"), temp.indexOf("Right"));
////            String cube3 = temp.substring(temp.indexOf("Right"));
            info.addField(event.getMember().getEffectiveName() + "'s Cube",  "```" +   temp + "```\n", false);
////            info.addBlankField(true);
////
////            info.addField("", cube2, false);
////            info.addBlankField(true);
////
////            info.addField("", cube3, false);
////            info.addBlankField(true);
//
//            info.setFooter("<--" + , event.getMember().getUser().getAvatarUrl());
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "Alg") ) {
            if ( arg[arg.length-1].substring(arg[arg.length-1].length()-1).equals(")")) {
                if (cube != null) {
                        String s = cube.stringalg(content.substring(content.indexOf("(") + 1, content.indexOf(")")));
                        store += s;
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
                if ( !solvedatfirst ) {
                    event.getChannel().sendMessage(cube.solve() + " " + event.getMember().getAsMention()).queue();
                }
                else {
                    String s = cube.reversealg(store, false);
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
