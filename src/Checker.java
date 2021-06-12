import jdk.swing.interop.SwingInterOpUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.* ;
import java.awt.event.*;
import javax.swing.Timer;

    import static java.lang.Math.abs;

public class CubeCommands extends ListenerAdapter {
//    String[] temp3 = {"RRRRRRRRR", "OOOOOOOOO", "WWBWWBWWB", "YBBYBBYBB", "GGWGGWGGW", "YYGYYGYYG"};
    String[] temp3 = {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
    private final String[] endsstate = {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
    public  Cubot cube =  new Cubot(temp3) ;
    public  String store = "" ;
    public boolean solvedatfirst = cube.isSolved() ;
    Dictionary<String, Long> times = new Hashtable<String, Long>();
    Dictionary<String, Boolean> timestart = new Hashtable<>();
    ArrayList<String> timerlist = new ArrayList<>() ;


    private void show(String name, GuildMessageReceivedEvent event, boolean movedone, String message) {
        EmbedBuilder info = new EmbedBuilder();
        info.setColor(0Xa80d2c);
        String temp = cube.toString(), cubes = "", temp2 = "";

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
            arg = new String[]{arg[2].substring(1), arg[3], arg[4], arg[5], arg[6], arg[7]};
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
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", you have entered the cube incorrectly ( Wrong colors )").queue();
                } else {
                    if ( cube.isSolved() ) { solvedatfirst = true ; }
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ",  I've made a cube for you!").queue();
                }
            }
            else {
                event.getChannel().sendMessage("Hey " + event.getMember().getEffectiveName() + ", your cubearray is wrong man.").queue();

            }
        }
        if (arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "makeSolved")) {
            cube = new Cubot(endsstate) ;
        }

        else if  ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "s") ) {
            times.put(Objects.requireNonNull(event.getMember()).getEffectiveName(), System.currentTimeMillis()) ;
            timestart.put(Objects.requireNonNull(event.getMember()).getEffectiveName(), true) ;

        }
        else if  ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "d") ) {
            String n = Objects.requireNonNull(event.getMember()).getEffectiveName() ;
            if (timestart.get(n) == null) {
                event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", start the stopwatch first").queue();
            }
            else {
                double time = ((double) System.currentTimeMillis() - times.get(n)) / 1000;
                timestart.put(n, false);
                EmbedBuilder info = new EmbedBuilder();
                info.setColor(0Xa80d2c);
                info.addField(n + "'s time : ", String.valueOf(abs(time)) + "seconds", true);

                timestart.remove(n);
                times.remove(n) ;
                System.out.println(timestart) ;


                event.getChannel().sendMessage(info.build()).queue();
                info.clear();
            }

        }
        else if ( arg.length == 3 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "-t")  ) {
            timerlist.add(Objects.requireNonNull(event.getMember()).getEffectiveName()) ;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    event.getChannel().sendMessage("Timer's up, " + timerlist.remove(0)).queue();
                }
            };
            Timer timer = new Timer(Integer.parseInt(String.valueOf(arg[2].charAt(arg[2].length()-1))) * 1000, taskPerformer);
            timer.setRepeats(false);
            timer.start();

        }

        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "show") ) {
           show(event.getMember().getEffectiveName(), event, false , "") ;
        }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "indexes")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(0Xa80d2c);
            String temp = cube.indexString(), cub1 = temp.substring(temp.indexOf("Left"), temp.indexOf("Middle")), cub2 = temp.substring(temp.indexOf("Middle"), temp.indexOf("Right")) , cub3 = temp.substring(temp.indexOf("Right"));

            info.addField("Your cube : ", "\n" +  cub1, false);
            info.addField("", cub2, false) ;
            info.addField("", cub3 + "\n", false) ;
            event.getAuthor().openPrivateChannel().complete().sendMessage(info.build()).complete();
            info.clear();

        }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "getStore") ) {
            if  (store.isEmpty() ) {
                event.getChannel().sendMessage("Nothing executed yet, " + event.getMember().getAsMention()).queue();
            }
            else {
                event.getChannel().sendMessage(store + " --> " + event.getMember().getAsMention()).queue();
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
