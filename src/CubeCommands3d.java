import net.codejava.Update;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.* ;


public class CubeCommands3d extends ListenerAdapter {
//    RRRRRRRRR GGGGGGGGG OOOOOOOOO BBBBBBBBB WWWWWWWWW YYYYYYYYY
//    RRRR GGGG OOOO BBBB WWWW YYYY
    private String[] temp3d = {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
    private String[] temp2d = {"RRRR", "GGGG", "OOOO", "BBBB", "WWWW", "YYYY"};
    public Cubot cube   = new Cubot(temp3d);

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
            boolean solvedatfirst = false;
            String[] arg2 = new String[]{arg[2].substring(1), arg[3], arg[4], arg[5], arg[6], arg[7]};
            try {
                cube = new Cubot(arg2);
                if (!(cube.isValid())) {
                    cube = null;
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", you have entered the cube incorrectly ( wrong colors )").queue();
                } else {
                    if (cube.isSolved()) {
                        solvedatfirst = true;
                    }
                    Update.setCubeSQL(event.getMember().getId(), " ", content.substring(content.indexOf(arg[2].substring(1))));
                    Update.setSolvedSQL(event.getMember().getId(), String.valueOf(solvedatfirst));
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ",  I've made a cube for you!").queue();
                }
            } catch (IllegalArgumentException e) {
                event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", check your syntax again").queue();
            }
        }
        if (arg.length >= 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "makeSolved")) {
            if (arg.length ==2) {
                cube.makeSolved();
                event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", your cube has been made").queue();
            }
            else {
                if (arg[arg.length - 1].equals("-2x2")) {
                    cube.make2();
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", your cube has been made").queue();
                } else if (arg[arg.length - 1].equals("-3x3")) {
                    cube.make3();
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", your cube has been made").queue();
                } else {
                    event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", re-enter with valid values " + cube.type()).queue();

                }
            }
            Update.setCubeSQL(event.getMember().getId(), "", cube.toarr());
        }
        if (arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "type?")) {
            event.getChannel().sendMessage("Hey " + event.getMember().getAsMention() + ", your cube is a " + cube.type()).queue();

        }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "show") ) {
            String s = Update.getCubeSQL(event.getMember().getId()) ;
            if (!s.equals("Error")) {
                cube = new Cubot(s.split(" "));
                show(event.getMember().getEffectiveName(), event, false, "");
            }
            else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
            }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "indexes")) {
            String s = Update.getCubeSQL(event.getMember().getId()) ;
            if (!s.equals("Error")) {
                cube = new Cubot(s.split(" "));
                EmbedBuilder info = new EmbedBuilder();
                info.setColor(0Xa80d2c);
                String temp = cube.indexString()  ;
                if (cube.type().equals("3x3")) {
                    String cub1 = temp.substring(temp.indexOf("Left"), temp.indexOf("Middle")), cub2 = temp.substring(temp.indexOf("Middle"), temp.indexOf("Right")), cub3 = temp.substring(temp.indexOf("Right"));
                    info.addField("Your cube : ", "\n" +  cub1, false);
                    info.addField("", cub2, false) ;
                    info.addField("", cub3 + "\n", false) ;

                }
                else {
                    info.addField("Your cube : ", "\n" + temp, false);
                }

                event.getAuthor().openPrivateChannel().complete().sendMessage(info.build()).complete();
                info.clear();
            }
            else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }

        }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "getStore") ) {
            String store = Update.getStoreSQL(event.getMember().getId()).replace("null", "") ;
            if (store.isEmpty()) {
                event.getChannel().sendMessage("Nothing executed yet, " + event.getMember().getAsMention()).queue();
            } else {
                event.getChannel().sendMessage(store + " --> " + event.getMember().getAsMention()).queue();
            }
        }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "do") ) {
            String s = Update.getCubeSQL(event.getMember().getId()) ;
            if (!s.equals("Error")) {
                cube = new Cubot(s.split(" "));
                boolean doScramble = false , check = true;
                int n = arg.length - 1, m = arg.length - 2 ;
                if (arg[n].charAt(arg[n].length()-1) == 's' ) { doScramble = true ; }
                else if (!(arg[n].charAt(arg[n].length()-1) == 'd' )){  check = false ; }
                if ( arg[m].charAt(arg[m].length()-1) == ')' && arg[2].charAt(1) == '(') {
                    if ( check ) {
                        String s1 = cube.stringalg(content.substring(content.indexOf("(") + 1, content.indexOf(")")));
                        if (cube.isSolved()){
                            Update.setCubeSQL(event.getMember().getId(), "", cube.toarr());
                            Update.setSolvedSQL(event.getMember().getId(), String.valueOf(true));
                        }
                        else
                        {
                            Update.setCubeSQL(event.getMember().getId(), Update.getStoreSQL(event.getMember().getId()) + s1, cube.toarr());
                        }
                        show(event.getMember().getEffectiveName(), event, doScramble, s1);
                    }
                    else {
                        event.getChannel().sendMessage("Re-enter with valid values " + event.getMember().getAsMention()).queue();
                    }

                }
                else {
                    event.getChannel().sendMessage("Add an alg for me to execute!"   + event.getMember().getAsMention()).queue();
                }
            }
            else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
        }
        else if ( arg.length > 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "rev")  ) {
            String s = Update.getCubeSQL(event.getMember().getId()) ;
            if (!s.equals("Error")) {
                cube = new Cubot(s.split(" "));
                boolean doScramble = false , check = true;
                int n = arg.length - 1, m = arg.length - 2 ;
                if (arg[n].charAt(arg[n].length()-1) == 'y' ) { doScramble = true ; }
                else if (!(arg[n].charAt(arg[n].length()-1) == 'n' )){  check = false ; }
                if ( arg[m].charAt(arg[m].length()-1) == ')' && arg[2].charAt(1) == '(') {
                    if (check) {
                        String s1 = cube.reversealg(content.substring(content.indexOf("(") + 1, content.indexOf(")")), doScramble);
                        if (cube.isSolved()){
                            Update.setCubeSQL(event.getMember().getId(), "", cube.toarr());
                            Update.setSolvedSQL(event.getMember().getId(), String.valueOf(true));
                        }
                        else
                        {
                            Update.setCubeSQL(event.getMember().getId(), Update.getStoreSQL(event.getMember().getId()) + " " + s1, cube.toarr());
                        }

                        show(event.getMember().getEffectiveName(), event, doScramble, s1);
                    } else {
                        event.getChannel().sendMessage("Re-enter with valid values " + event.getMember().getAsMention()).queue();
                    }
                }
            }
            else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
        }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "solve") ) {
            String s = Update.getCubeSQL(event.getMember().getId()) ;
            String store  = Update.getStoreSQL(event.getMember().getId()).replace("null", "") ; ;
            if (!s.equals("Error")) {
                cube = new Cubot(s.split(" "));
                EmbedBuilder info = new EmbedBuilder();
                info.setColor(0Xa80d2c);
                String name;
                boolean solvedatfirst = Boolean.parseBoolean(Update.getsSolvedSQL(event.getMember().getId()));
                if (solvedatfirst && store.length() < 20) {
                    name = cube.reversealg(store, true);
                } else {
                    name = cube.solve();
                }
                Update.setCubeSQL(event.getMember().getId(), "" , cube.toarr()) ;
                Update.setSolvedSQL(event.getMember().getId(), String.valueOf(true));

                info.addField("Solution : ", name, false);
                event.getChannel().sendMessage(info.build()).queue();
                info.clear();
            }
            else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }

        }
        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "solved?") ) {
            String s = Update.getCubeSQL(event.getMember().getId()) ;
            if (!s.equals("Error")) {
                cube = new Cubot(s.split(" "));
                if (cube.isSolved()) {
                    s = "Solved" ; }
                else { s = "Not Solved" ; }
                event.getChannel().sendMessage("Your Cube is " + s + ", " + event.getMember().getAsMention()).queue();

            } else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
        }
        else if ( arg.length > 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "scramble")  ) {
            String s = Update.getCubeSQL(event.getMember().getId()) ;
            if (!s.equals("Error")) {
                cube = new Cubot(s.split(" "));
                boolean doScramble = false, check = true;
                if (arg[3].substring(arg[3].length() - 1).equals("s")) {
                    doScramble = true;
                } else if (!arg[3].substring(arg[3].length() - 1).equals("d")) {
                    check = false;
                }
                if (check) {
                    String x = cube.getScramble(Integer.parseInt(arg[2].substring(1)), doScramble);
                    Update.setCubeSQL(event.getMember().getId(), "" , cube.toarr()) ;
                    Update.setSolvedSQL(event.getMember().getId(), "false") ;
                    event.getChannel().sendMessage("Your scramble's here - " + x + ", " + event.getMember().getAsMention()).queue();
                } else {
                    event.getChannel().sendMessage("Re-enter with valid syntax" + event.getMember().getAsMention()).queue();
                }

            } else {
                event.getChannel().sendMessage("Make a cube first " + event.getMember().getAsMention()).queue();
            }
        }
        else if ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "die")  ) {
            event.getChannel().sendMessage(":( " + event.getMember().getAsMention()).queue();
        }

    }
}
