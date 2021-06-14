import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.* ;
import java.awt.event.*;
import javax.swing.Timer;
import static java.lang.Math.abs;

public class CubeCommands3d extends ListenerAdapter {
//    String[] temp3 = {"RRRRRRRRR", "OOOOOOOOO", "WWBWWBWWB", "YBBYBBYBB", "GGWGGWGGW", "YYGYYGYYG"};
//    RRRRRRRRR GGGGGGGGG OOOOOOOOO BBBBBBBBB WWWWWWWWW YYYYYYYYY
    private String[] temp3d = {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
    private String[] temp2d = {"RRRR", "GGGG", "OOOO", "BBBB", "WWWW", "YYYY"};
    public Cubot cube  ;
    public  String store = "" ;
    public boolean solvedatfirst;
    Dictionary<String, Long> times = new Hashtable<String, Long>();
    Dictionary<String, Boolean> timestart = new Hashtable<>();
    ArrayList<String> timerlist = new ArrayList<>() ;


    private void show(String name, GuildMessageReceivedEvent event, boolean movedone, String message) {
        EmbedBuilder info = new EmbedBuilder();
        info.setColor(0Xa80d2c);
        String temp = cube.toString();

        if (movedone && !message.isEmpty()) {
            info.addField("Moves Executed : ", message, false);
        }
        String r = temp.replaceAll("R", "\uD83D\uDFE5");
        String r1 = r.replaceAll("G", "\uD83D\uDFE9" ) ;
        String r2 = r1.replaceAll("O", "\uD83D\uDFE7" ) ;
        String r3 = r2.replaceAll("B", "\uD83D\uDFE6" ) ;
        String r4 = r3.replaceAll("W", "⬜" ) ;
        String r5 = r4.replaceAll("Y", "\uD83D\uDFE8" ) ;
        String r6 = r5.replaceAll("i", "⬛" );
        info.addField(name + "'s Cube : ",  "```java\n" +   r6 + "```\n", false);
        event.getChannel().sendMessage(info.build()).queue();
        info.clear();
    }
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw() ;
        String[] arg = event.getMessage().getContentRaw().split(" ");
        if (arg.length == 8 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "make")) {
            String[] arg2 = new String[]{arg[2].substring(1), arg[3], arg[4], arg[5], arg[6], arg[7]};
            boolean check = true;
            for (String i : arg2) {
                if (!(i.length() == 9 || i.length() == 4)) {
                    check = false;
                    break;
                }
            }

            if (check) {
                cube = new Cubot(arg2)  ;
                if (!(cube.isValid())){
                    cube = null;
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", you have entered the cube incorrectly ( wrong colors )" ).queue();
                } else {
                    if ( cube.isSolved() ) { solvedatfirst = true ; }
                    store = "";
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ",  I've made a cube for you!").queue();
                }
            }
            else {
                event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", your cubearray is wrong man.").queue();
            }
        }
        if (arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "makeSolved")) {
            cube.makeSolved();
            event.getChannel().sendMessage("Hey " + event.getMember().getEffectiveName() + ", your cube is updated").queue();

        }



        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "show") ) {
            if (cube!= null) {
                show(event.getMember().getEffectiveName(), event, false, "");
            }
            else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
            }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "indexes")) {
            if (cube!=null) {
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(0Xa80d2c);
            String temp = cube.indexString(), cub1 = temp.substring(temp.indexOf("Left"), temp.indexOf("Middle")), cub2 = temp.substring(temp.indexOf("Middle"), temp.indexOf("Right")) , cub3 = temp.substring(temp.indexOf("Right"));
            info.addField("Your cube : ", "\n" +  cub1, false);
            info.addField("", cub2, false) ;
            info.addField("", cub3 + "\n", false) ;
            event.getAuthor().openPrivateChannel().complete().sendMessage(info.build()).complete();
            info.clear();}
            else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }

            }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "getStore") ) {
            if ( cube != null ) {
                if (store.isEmpty()) {
                    event.getChannel().sendMessage("Nothing executed yet, " + event.getMember().getAsMention()).queue();
                } else {
                    event.getChannel().sendMessage(store + " --> " + event.getMember().getAsMention()).queue();
                }
            }
            else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
            }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "do") ) {
            boolean doScramble = false , check = true;
            int n = arg.length - 1, m = arg.length - 2 ;
            if (arg[n].charAt(arg[n].length()-1) == 's' ) { doScramble = true ; }
            else if (!(arg[n].charAt(arg[n].length()-1) == 'd' )){  check = false ; }
            if ( arg[m].charAt(arg[m].length()-1) == ')' && arg[2].charAt(1) == '(') {
                if (cube != null) {
                    if ( check ) {
                        String s = cube.stringalg(content.substring(content.indexOf("(") + 1, content.indexOf(")")));
                        store += " " + s;
                        show(event.getMember().getEffectiveName(), event, doScramble, s);
                    }
                    else {
                        event.getChannel().sendMessage("Re-enter with valid values " + event.getMember().getAsMention()).queue();
                    }
                }
                else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
                }
            }
            else {
                event.getChannel().sendMessage("Add an alg for me to execute!"   + event.getMember().getAsMention()).queue();
            }

        }
        else if ( arg.length > 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "rev")  ) {
            boolean doScramble = false , check = true;
            int n = arg.length - 1, m = arg.length - 2 ;
            if (arg[n].charAt(arg[n].length()-1) == 'y' ) { doScramble = true ; }
            else if (!(arg[n].charAt(arg[n].length()-1) == 'n' )){  check = false ; }
            if ( arg[m].charAt(arg[m].length()-1) == ')' && arg[2].charAt(1) == '(') {
                if (cube != null ) {
                    if (check) {
                        String s = cube.reversealg(content.substring(content.indexOf("(") + 1, content.indexOf(")")), doScramble);
                        store += " " + s;
                        event.getChannel().sendMessage("Moves Executed : " + s + " " + event.getMember().getAsMention()).queue();
                    }
                    else {
                        event.getChannel().sendMessage("Re-enter with valid values " + event.getMember().getAsMention()).queue();
                    }
                }
                else {
                    event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
                }
            }
            else {
                event.getChannel().sendMessage("Re-enter with valid values " + event.getMember().getAsMention()).queue();
            }
        }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "solve") ) {
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(0Xa80d2c);
            String name;

            if (cube != null) {
                if ( solvedatfirst && store.length() < 50 ) {
                    String s = cube.reversealg(store, false);
                    name = cube.stringalg(s) ;
                }
                else {
                    name = cube.solve() ;
                }
                info.addField("Solution : ",  name, false);
                event.getChannel().sendMessage(info.build()).queue();
                info.clear();

            } else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }

        }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "solved?") ) {

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
        else if ( arg.length > 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "scramble")  ) {
            boolean doScramble = false , check = true;
            if (arg[3].substring(arg[3].length()-1).equals("s")) { doScramble = true ; }
            else if (!arg[3].substring(arg[3].length()-1).equals("d")){  check = false ; }
            if (cube != null ) {
                if (check) {
                    String s = cube.getScramble(Integer.parseInt(arg[2].substring(1)), doScramble);
                    event.getChannel().sendMessage("Your scramble's here - " + s + ", " + event.getMember().getAsMention()).queue();
                }
                else {
                    event.getChannel().sendMessage("Re-enter with valid values " + event.getMember().getAsMention()).queue();
                }

            } else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
        }


    }
}