import net.codejava.Update;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.internal.entities.UserById;

import javax.security.auth.login.LoginException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

public class Bot extends ListenerAdapter {
    public static JDA jda;
    public final static String prefix = ":c";
    private String[] temp3d = {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
    private String[] temp2d = {"RRRR", "GGGG", "OOOO", "BBBB", "WWWW", "YYYY"};
    public Cubot cube = new Cubot(temp3d);
    ArrayList<String> timerlist = new ArrayList<>() ;

    public EmbedBuilder show(SlashCommandEvent event, String name, Cubot cube, boolean movedone, String message) {
        EmbedBuilder x = new EmbedBuilder();
        EmbedBuilder info = new EmbedBuilder();
        info.setColor(0Xa80d2c);
        String temp = cube.toString();

        if (movedone && !message.isEmpty()) {
            info.addField("Moves Executed : ", message, false);
        }
        String r = temp.replaceAll("R", "\uD83D\uDFE5");
        String r1 = r.replaceAll("G", "\uD83D\uDFE9");
        String r2 = r1.replaceAll("O", "\uD83D\uDFE7");
        String r3 = r2.replaceAll("B", "\uD83D\uDFE6");
        String r4 = r3.replaceAll("W", "⬜");
        String r5 = r4.replaceAll("Y", "\uD83D\uDFE8");
        String r6 = r5.replaceAll("i", "⬛");
        info.addField(name + "'s Cube : ", "```java\n" + r6 + "```\n", false);
        return info;
    }


    public static void main(String[] args) throws LoginException, InterruptedException {
        jda = JDABuilder.createDefault("ODQ3MTExNDEwNjE3Njc5ODk0.YK5T-g.vZvju_qtkTfubJFZtaaQO6-CIcI").addEventListeners(new Bot()).setActivity(Activity.playing("with Cubes for you")).setStatus(OnlineStatus.DO_NOT_DISTURB).build();
        jda.awaitReady();
        jda.addEventListener(new Commands());
        jda.addEventListener(new Timing());
        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(
                new CommandData("help", "Help command"),
                new CommandData("commands", "List of commands"),
                new CommandData("make", "Create your custom cube. Check the docs on how to set input!")
                        .addOptions(new OptionData(STRING, "left_face", "Face1").setRequired(true),
                                new OptionData(STRING, "front_face", "Face2").setRequired(true),
                                new OptionData(STRING, "right_face", "Face3").setRequired(true),
                                new OptionData(STRING, "back_face", "Face4").setRequired(true),
                                new OptionData(STRING, "bottom_face", "Face5").setRequired(true),
                                new OptionData(STRING, "top_face", "Face6").setRequired(true)),
                new CommandData("make_solved", "Makes a solved cube for you").addOptions(
                        new OptionData(STRING, "cube_type", "3 - 3x3 or 2 - 2x2 or empty - Updates present cube")),
                new CommandData("show", "Visualize your cube!").addOptions(
                        new OptionData(STRING, "show_type", "s - Everyone can see, empty - Only you can see")),
                new CommandData("type", "Find out what type of cube you have right now!"),
                new CommandData("get_store", "Get a record of the moves you have performed on your cube"),
                new CommandData("solved", "Tells you if your cube is solved"),
                new CommandData("indexes", "Sends you the Cube indexes and colors").addOptions(
                        new OptionData(STRING, "show_type", "dm - Sends to DM, empty - Sends on chat")),
                new CommandData("cube_string",  "Sends you the cube like an input ( Use it to make another cube )"),
                new CommandData("solve", "Solves your cube and sends you the solution"),
                new CommandData("do", "Executes your moves on the cube").addOptions(
                        new OptionData(STRING, "moves", "Your moves").setRequired(true),
                        new OptionData(STRING, "show_type", "s - Shows your changed cube, empty - Doesn't send on chat")
                ),
                new CommandData("rev", "Executes your moves on the cube").addOptions(
                        new OptionData(STRING, "moves", "Your moves").setRequired(true),
                        new OptionData(STRING, "mode", "s - Returns only the reversed algorithm, empty - executes it on cube as well")
                ),
                new CommandData("scramble", "Scrambles your cube").addOptions(
                        new OptionData(STRING, "number", "Number of moves").setRequired(true),
                        new OptionData(STRING, "mode", "s - Returns only the scramble, empty - executes it on cube as well")
                ),
                new CommandData("add_time", "Adds your score time and calculates your average").addOptions(
                        new OptionData(STRING, "mins", "Number of minutes").setRequired(true),
                        new OptionData(STRING, "secs", "Number of minutes").setRequired(true),
                        new OptionData(STRING, "ms", "Number of minutes").setRequired(true)
                ),
                new CommandData("get_best", "Tells you your best time!"),
                new CommandData("get_avg", "Tells you your average time!"),
                new CommandData("speak", "Cubord speaks!"),
                new CommandData("die", "But why?"),
                new CommandData("support", "Sends your feedback to Cubord Dev").addOptions(
                        new OptionData(STRING, "text", "Your feedback").setRequired(true)
                )

        ).queue();
    }


    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getGuild() == null)
            return;
        if (event.getName().equals("make")) {
            try {
                make(event, event.getOption("left_face").getAsString(), event.getOption("front_face").getAsString(),
                        event.getOption("right_face").getAsString(),
                        event.getOption("back_face").getAsString(),
                        event.getOption("bottom_face").getAsString(),
                        event.getOption("top_face").getAsString()
                );
            }
            catch (NullPointerException E) {
                System.out.println(event.getCommandId());
            }
        } else if (event.getName().equals("make_solved")) {
        String s = "";
        try {
            s = event.getOption("cube_type").getAsString();
        } catch (NullPointerException e) {
            s = null;
        }
        makeSolved(event, s);
        } else if (event.getName().equals("show")) {
            String s = "";
            try {
                s = event.getOption("show_type").getAsString();
            } catch (NullPointerException e) {
                s = null;
            }
            show(event, s);
        } else if (event.getName().equals("indexes")) {
            String s = "";
            try {
                s = event.getOption("show_type").getAsString();
            } catch (NullPointerException e) {
                s = null;
            }
            indexes(event, s);
        } else if (event.getName().equals("type")) {
            type(event);
        } else if (event.getName().equals("get_store")) {
            getStore(event);
        } else if (event.getName().equals("help")) {
            help(event);
        } else if (event.getName().equals("commands")) {
            commands(event);
        } else if (event.getName().equals("solved")) {
            solved(event);
        } else if (event.getName().equals("cube_string")) {
            cubeString(event);
        } else if (event.getName().equals("solve")) {
            solve(event);
        } else if (event.getName().equals("speak")) {
            event.reply("https://imgur.com/yllp3rZ").queue();
        } else if (event.getName().equals("do")) {
            String s = "" ;
            try {
                s = event.getOption("show_type").getAsString();
            } catch (NullPointerException e) {
                s = null;
            }
            to_do(event, event.getOption("moves").getAsString(),s);
        } else if (event.getName().equals("rev")) {
            String s = "" ;
            try {
                s = event.getOption("mode").getAsString();
            } catch (NullPointerException e) {
                s = null;
            }
            rev_do(event, event.getOption("moves").getAsString(), s);
        } else if (event.getName().equals("scramble")) {
            String s = "" ;
            try {
                s = event.getOption("mode").getAsString();
            } catch (NullPointerException e) {
                s = null;
            }
            scramble(event, event.getOption("number").getAsString(), s);
        } else if (event.getName().equals("add_time")) {
            addTime(event, event.getOption("mins").getAsString(), event.getOption("secs").getAsString(), event.getOption("ms").getAsString());
        } else if (event.getName().equals("get_best")) {
            getBest(event);
        } else if (event.getName().equals("get_avg")) {
            getAvg(event);
        } else if (event.getName().equals("die")) {
            die(event);
        } else if (event.getName().equals("support")) {
            support(event, event.getOption("text").getAsString());
        } else {
            System.out.println(event.getCommandId());
            event.reply("I can't handle that command right now :(").setEphemeral(true).queue();
        }


    }

    public void make(SlashCommandEvent event, String f1, String f2, String f3, String f4, String f5, String f6) {
        boolean solvedatfirst = false;
        String[] arg2 = new String[]{f1, f2, f3, f4, f5, f6};

        try {
            cube = new Cubot(arg2);
            if (!(cube.isValid())) {
                cube = null;
                event.reply("CubeError : Incorrect Input Format. Check the Docs on how to make a cube").setEphemeral(true).queue();
            } else {
                if (cube.isSolved()) {
                    solvedatfirst = true;
                }
                try {
                    String s = event.getMember().getId();
                    Update.setCubeSQL(s, " ", f1 + " " + f2 + " " + f3 + " " + f4 + " " + f5 + " " + f6);
                    Update.setSolvedSQL(s, String.valueOf(solvedatfirst));
                    Update.setTimeSQL(s, "0", true);
                    Update.setTimeSQL(s, "0", false);
                    event.reply("Cube Created!").setEphemeral(true).queue();
                } catch (Error e) {
                    event.reply("Sorry, try again later!").setEphemeral(true).queue();
                }
            }
        } catch (IllegalArgumentException e) {
            event.reply("CubeError : Incorrect Input Format. Check the Docs on how to make a cube").setEphemeral(true).queue();
        }
    }

    public void makeSolved(SlashCommandEvent event, String check) {
        boolean tempCheck = true ;
        if (check == null) {
            cube.makeSolved();
        } else if (check.equals("3")) {
            cube.make3();
        } else if (check.equals("2")) {
            cube.make2();
        } else {
            event.reply("CubeError : No Such Input Choice").setEphemeral(true).queue();
            tempCheck = false ;
        }
        if ( tempCheck ) {
            try {
                String s = event.getMember().getId();
                Update.setCubeSQL(s, "", cube.toarr());
                Update.setSolvedSQL(s, String.valueOf(true));
                Update.setTimeSQL(s, "0", true);
                Update.setTimeSQL(s, "0", false);
                event.reply("Cube Created!").setEphemeral(true).queue();

            } catch (Error e) {
                event.reply("Sorry, try again later!").setEphemeral(true).queue();
            }
        }
    }

    public void show(SlashCommandEvent event, String check) {
        String s = Update.getCubeSQL(event.getMember().getId());
        if (!s.equals("Error")) {
            cube = new Cubot(s.split(" "));
            MessageEmbed x = (MessageEmbed) show(event, event.getMember().getEffectiveName(), cube, false, "").build() ;
            if (check == null) {
                event.replyEmbeds(x).setEphemeral(true).queue();
            } else if (check.toLowerCase(Locale.ROOT).equals("s")) {
                event.replyEmbeds(x).queue();
            } else {
                event.reply("CubeError : No Such Input Choice").setEphemeral(true).queue();
            }
        } else {
            event.reply("Sorry, try again later!").setEphemeral(true).queue();
        }

    }
    public void indexes(SlashCommandEvent event, String check) {
        String s = Update.getCubeSQL(event.getMember().getId()) ;
        if (!s.equals("Error")) {
            cube = new Cubot(s.split(" "));
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(0Xa80d2c);
            String temp = cube.indexString();
            if (cube.type().equals("3x3")) {
                String cub1 = temp.substring(temp.indexOf("Left"), temp.indexOf("Middle")), cub2 = temp.substring(temp.indexOf("Middle"), temp.indexOf("Right")), cub3 = temp.substring(temp.indexOf("Right"));
                info.addField("Your cube : ", "\n" + cub1, false);
                info.addField("", cub2, false);
                info.addField("", cub3 + "\n", false);
            } else {
                info.addField("Your cube : ", "\n" + temp, false);
            }
            if (check == null) {
                event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
            } else if (check.equals("dm")) {
                event.getUser().openPrivateChannel().complete().sendMessage(info.build()).complete();
                info.clear();
                event.reply("Check your DMs").setEphemeral(true).queue();
            } else {
                event.reply("CubeError : No Such Input Choice").setEphemeral(true).queue();
            }
        }
        else {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }

    public void type(SlashCommandEvent event) {
        String s = Update.getCubeSQL(event.getMember().getId());
        if (!s.equals("Error")) {
            event.reply("Cubord is holding a " + cube.type() + " for you").setEphemeral(true).queue();
        } else {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }

    public void getStore(SlashCommandEvent event) {
        String store = Update.getStoreSQL(event.getMember().getId()).replace("null", "");
        if (store.isEmpty()) {
            event.reply("Nothing executed yet!").setEphemeral(true).queue();
        } else {
            event.reply(store).setEphemeral(true).queue();
        }
    }
    public void solved(SlashCommandEvent event) {
        String s = Update.getCubeSQL(event.getMember().getId());
        if (!s.equals("Error")) {
            cube = new Cubot(s.split(" "));
            if (cube.isSolved()) {
                s = "solved";
            } else {
                s = "not solved";
            }
            event.reply("Your cube is " + s).setEphemeral(true).queue();
        } else {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }

    public void cubeString(SlashCommandEvent event) {
        String s = Update.getCubeSQL(event.getMember().getId()) ;
        if (!s.equals("Error")) {
            cube = new Cubot(s.split(" "));
            event.reply("Here is your cubeString - " + cube.toarr()).setEphemeral(true).queue();
        } else {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }

    public void solve(SlashCommandEvent event) {
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
                try {
                    name = cube.solve();
                } catch (Error e) {
                    name = "Cube could not solved fully. Check cube again or Report Bug by using '/support <scramble>'.\nAlternatively, use /makeSolved to force solve!";
                }
            }
            try {
                Update.setCubeSQL(event.getMember().getId(), "", cube.toarr());
                Update.setSolvedSQL(event.getMember().getId(), String.valueOf(true));
                info.addField("Solution : ", name, false);
                event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
                info.clear();
            } catch (Error e) {
                event.reply("Sorry, try again later!").setEphemeral(true).queue();
            }
        }
        else {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }

    public void to_do(SlashCommandEvent event, String n, String todo) {
        String s = Update.getCubeSQL(event.getMember().getId()) ;
        if (!s.equals("Error")) {
            cube = new Cubot(s.split(" "));
            boolean doScramble = true , check = true;
            if ( todo == null ) { doScramble = false ; }
            else if ( !todo.toLowerCase(Locale.ROOT).equals("s")) { check = false ; }
            if (check) {
                String s1 = cube.stringalg(n);
                try {
                    if (cube.isSolved()) {
                        Update.setCubeSQL(event.getMember().getId(), "", cube.toarr());
                        Update.setSolvedSQL(event.getMember().getId(), String.valueOf(true));
                    } else {
                        Update.setCubeSQL(event.getMember().getId(), Update.getStoreSQL(event.getMember().getId()) + s1, cube.toarr());
                    }
                    if ( doScramble ) {
                        event.replyEmbeds((MessageEmbed) show(event, event.getMember().getEffectiveName(), cube, doScramble, s1).build() ).setEphemeral(true).queue();
                    }
                    else {
                        EmbedBuilder info = new EmbedBuilder();
                        info.setColor(0Xa80d2c);
                        info.addField("Moves Executed : ", n, false) ;
                        event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
                    }
                } catch (Error e) {
                    event.reply("Sorry, try again later!").setEphemeral(true).queue();
                }
            }
            else {
                event.reply("CubeError : No Such Input Choice").setEphemeral(true).queue();
            }
        }
        else {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }

    public void rev_do(SlashCommandEvent event, String n, String todo) {
        String s = Update.getCubeSQL(event.getMember().getId());
        if (!s.equals("Error")) {
            cube = new Cubot(s.split(" "));
            boolean doScramble = false , check = true;
            if ( todo == null ) { doScramble = true ; }
            else if ( !todo.toLowerCase(Locale.ROOT).equals("s")) { check = false ; }
            if (check) {
                String s1 = cube.reversealg(n, doScramble);
                try {
                    if (cube.isSolved()) {
                        Update.setCubeSQL(event.getMember().getId(), "", cube.toarr());
                        Update.setSolvedSQL(event.getMember().getId(), String.valueOf(true));
                    } else {
                        Update.setCubeSQL(event.getMember().getId(), Update.getStoreSQL(event.getMember().getId()) + " " + s1, cube.toarr());
                    }

                    if ( doScramble ) {
                        event.replyEmbeds((MessageEmbed) show(event, event.getMember().getEffectiveName(), cube, doScramble, s1).build() ).setEphemeral(true).queue();
                    }
                    else {
                        EmbedBuilder info = new EmbedBuilder();
                        info.setColor(0Xa80d2c);
                        info.addField("Reversed Algorithm : ", s1, false) ;
                        event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
                    }
                } catch (Error e) {
                    event.reply("Sorry, try again later!").setEphemeral(true).queue();
                }
            }
            else { event.reply("CubeError : No Such Input Choice").setEphemeral(true).queue(); }
        }
        else {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }
    public void scramble(SlashCommandEvent event, String n, String todo) {
        String s = Update.getCubeSQL(event.getMember().getId()) ;
        if (!s.equals("Error")) {
            cube = new Cubot(s.split(" "));
            boolean doScramble = false , check = true;
            if ( todo == null ) { doScramble = true ; }
            else if ( !todo.toLowerCase(Locale.ROOT).equals("s")) { check = false ; }
            if (check) {
                String x = cube.getScramble(Integer.parseInt(n), doScramble);
                try {
                    Update.setCubeSQL(event.getMember().getId(), "", cube.toarr());
                    Update.setSolvedSQL(event.getMember().getId(), "false");
                    event.reply(x).setEphemeral(true).queue();
                } catch (Error e) {
                    event.reply("Sorry, try again later!").setEphemeral(true).queue();
                }
            }
            else {
                event.reply("CubeError : No Such Input Choice").setEphemeral(true).queue();
            }
        }
        else {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }

    public void addTime(SlashCommandEvent event, String n1, String n2, String n3) {
        try {
            int oldTime = Integer.parseInt(Update.getTimeSQL(Objects.requireNonNull(event.getMember()).getId(), true));
            String newTime = "" ;
            int giveTime = ( Integer.parseInt(n1) * 60000 ) + ( Integer.parseInt(n2) * 1000 )  +  Integer.parseInt(n3)   ;
            if (giveTime > 3000) {
                if (!(oldTime == 0)) {
                    newTime = String.valueOf(((giveTime + oldTime) / 2));
                } else {
                    newTime = String.valueOf(giveTime);
                }

                try {
                    Update.setTimeSQL(event.getMember().getId(), newTime, true);
                    try {
                        int best = Integer.parseInt(Update.getTimeSQL(event.getMember().getId(), false));
                        System.out.println(best);
                        System.out.println(giveTime);
                        if (best > giveTime) {
                            Update.setTimeSQL(event.getMember().getId(), newTime, false);
                        }
                        event.reply("Time added!").setEphemeral(true).queue();
                    } catch (NullPointerException r) {
                        event.reply("Time added! But I couldn't check if its your best solve time. Sorry :(").setEphemeral(true).queue();
                    }
                } catch (Error e) {
                    event.reply("Sorry, try again later!").setEphemeral(true).queue();
                }
            }
            else {
                event.reply("https://tenor.com/view/are-you-sure-john-cena-ru-sure-about-dat-gif-14258954").setEphemeral(true).queue();
            }
        }
        catch (NumberFormatException e) {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }

    }
    public void getAvg(SlashCommandEvent event) {
        try {
            int given = Integer.parseInt(Update.getTimeSQL(event.getMember().getId(), true));
            if (given == 0) {
                event.reply("You have to add a time first!").setEphemeral(true).queue();
            } else {
                String fin = ((given / 1000) / 60) + " min " + ((given / 1000) % 60) + " secs";
                event.reply("Your average time is : " + fin).setEphemeral(true).queue();
            }
        }
        catch (NumberFormatException e) {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }
    public void getBest(SlashCommandEvent event) {
        try {
            int given = Integer.parseInt(Update.getTimeSQL(event.getMember().getId(), false));
            if (given == 0) {
                event.reply("You have to add a time first!").setEphemeral(true).queue();
            } else {
                String fin = ((given / 1000) / 60) + " min " + ((given / 1000) % 60) + " secs";
                event.reply("Your best time is : " + fin).setEphemeral(true).queue();
            }
        }
        catch (NumberFormatException e) {
            event.reply("Make a cube first!").setEphemeral(true).queue();
        }
    }
    public void timer(SlashCommandEvent event) {
        event.reply("Use ' :c -t -(mins) -(secs) '").setEphemeral(true).queue();
    }

    public void say(SlashCommandEvent event) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("cubord.io", "https://github.com/AkshathRaghav/cubord.io");

        info.setColor(Color.red);
        info.setColor(new Color(0xF40C0C));
        info.setColor(new Color(255, 0, 54));
        info.addField("Hello there!", "I'm cubord, and I cube!", true) ;
        info.setImage("https://cdn.discordapp.com/attachments/812010489248088088/866530400582762506/finalcover.png");

        info.setThumbnail("https://cdn.discordapp.com/attachments/812010489248088088/879789517492600872/Comp_Cube.png");
        event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
        info.clear() ;
    }
    public void support(SlashCommandEvent event, String text) {
        event.reply("Feedback Sent!").setEphemeral(true).queue();
        jda.openPrivateChannelById(725033707047485501L).complete().sendMessage(text).complete() ;
    }


    public void die(SlashCommandEvent event) {
        event.reply("やめて ください \uD83E\uDD24").setEphemeral(true).queue();
    }
    public void help(SlashCommandEvent event) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("cubord.io", "https://github.com/AkshathRaghav/cubord.io");

        info.setColor(Color.red);
        info.setColor(new Color(0xF40C0C));
        info.setColor(new Color(255, 0, 54));

        info.addField("Cubord is a quick and easy way for you to cube through your keyboard", "Watch the official [demo](https://www.youtube.com/watch?v=xvFZjo5PgG0)! \n\n To get started, use /help or :c help \n", false);

        info.addField("Commands", "A full list of commands is available [here](https://github.com/AkshathRaghav/cubord.io/tree/master/Docs)", true);
        info.addField("Support", "Click [here](https://discord.gg/YBBUf24Yf5) if you're having trouble or have any questions.\n", true);
        info.addField("Github", " \n Cubord is an implementation of [cubot.io](https://github.com/AkshathRaghav/cubot.io), an Open Source java library for the Rubiks Cube.\n Visit the Github [Repo](https://github.com/AkshathRaghav/cubord.io). \n", false);
        info.setImage("https://cdn.discordapp.com/attachments/812010489248088088/866530400582762506/finalcover.png");

        info.setThumbnail("https://cdn.discordapp.com/attachments/812010489248088088/879789517492600872/Comp_Cube.png");

        event.replyEmbeds((MessageEmbed) info.build()).addActionRow(Button.link("https://github.com/AkshathRaghav/cubord.io/tree/master/Docs", "Commands"), Button.link("https://discord.gg/YBBUf24Yf5", "Support"),Button.link("https://github.com/AkshathRaghav/cubord.io", "Github")).setEphemeral(true).queue();
        info.clear() ;

    }

    public void commands(SlashCommandEvent event) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Command List   \uD83D\uDCD6", "https://github.com/AkshathRaghav/cubord.io");

        info.setColor(Color.red);
        info.setColor(new Color(0xF40C0C));
        info.setColor(new Color(255, 0, 54));

        info.addField("Hello there!", "I've split the list of all the commands that I have to offer you into 2 lists! \n Need help? Check out my [website](https://github.com/AkshathRaghav/cubord.io)!", true) ;

        info.addField("Slash Commands", "Most of my commands are slash commands. Click on the button to get that list", false);

        info.addField("Chat Commands", "Click on the button below to get the chat commands!",true);

        info.setThumbnail("https://cdn.discordapp.com/attachments/812010489248088088/879789517492600872/Comp_Cube.png");

        info.setImage("https://cdn.discordapp.com/attachments/812010489248088088/878341674269548554/cfop.png");

        event.replyEmbeds((MessageEmbed) info.build()).addActionRow(Button.primary("slash", "Slash Commands"), Button.primary("time", "Timing Commands"), Button.link("https://github.com/AkshathRaghav/cubord.io/tree/master/Docs", "All Commands")).setEphemeral(true).queue();
        info.clear() ;
    }

    public void onButtonClick(ButtonClickEvent event) {
        if ( event.getComponentId().equals("slash")) {
            EmbedBuilder info = new EmbedBuilder() ;
            info.setTitle("Slash Command List", null);

            info.setColor(Color.red);
            info.setColor(new Color(0xF40C0C));
            info.setColor(new Color(255, 0, 54));

            info.addField("/make", "I'll make a cube for you. [How?](https://github.com/AkshathRaghav/cubord.io/blob/master/Docs/makingcube.md)", false);
            info.addField("/makeSolved", "I'll make a solved cube for you\n cube_type - 3 for 3x3, 2 for 2x2", false);
            info.addField("/cube_string", "You will get the text format of the cube you are using right now. You can use it make another cube. \n (Click on the make button below to understand)", false);
            info.addField("/type", "I'll let you know if I'm holding a 2x2 or 3x3 for you", false);


            info.addField("/show", "I'll show your cube \n show_type - s for public view, empty for private view", false);
            info.addField("/indexes", "Check your DM for the indexed presentation of the cube. [What?](https://github.com/AkshathRaghav/cubord.io/blob/master/Docs/viewing.md) \n show_type - dm for a DM, empty for chat view", false);

            info.addField("/do", "Executes the moves. \n moves - moves to execute \n show_type - s for chat view, empty for no /show", false);
            info.addField("/rev", "Reverses the moves. \n moves - moves to execute \n show_type - s for getting reversed alg, empty for executing moves", false);
            info.addField("/getStore", "Returns all the moves done on the Cube at the start of the session", false);


            info.addField("/solve", "I will solve the cube for you \n If something goes wrong, please check your cube input or report a bug", false);
                info.addField("/solved", "I'll tell you if your cube is solved", false);

            info.addField("/scramble", "I'll scramble the cube for you \n number - Number of moves in scramble \n mode - s - only the scramble will be returned, empty - your cube will be scrambled", false);


            info.addField("/add_time", "I'll add your time to my database. \n mins - Number of mintues \n secs - Number of secs \n ms - Number of millseconds", false);
            info.addField("/get_best", "I'll return your best time!", false);
            info.addField("/get_avg", "I'll return your avg time!", false);

            info.setThumbnail("https://cdn.discordapp.com/attachments/812010489248088088/879789517492600872/Comp_Cube.png");
            info.setImage("https://cdn.discordapp.com/attachments/812010489248088088/878341674269548554/cfop.png") ;

            event.replyEmbeds((MessageEmbed) info.build()).addActionRow( Button.link("https://github.com/AkshathRaghav/cubord.io/tree/master/Docs", "All Commands"),  Button.primary("make", "Making a Cube"),
                    Button.primary("do", "Executing moves"), Button.primary("solve", "Solving the Cube")).setEphemeral(true).queue();
            info.clear() ;

        }

        else if ( event.getComponentId().equals("time")) {

            EmbedBuilder info = new EmbedBuilder() ;
            info.setTitle("Timing", null);

            info.setColor(Color.red);
            info.setColor(new Color(0xF40C0C));
            info.setColor(new Color(255, 0, 54));
            info.addField("-->", "Do not hope for these methods to be 100% accurate. \n Use your own timers or [Ruwix](https://ruwix.com/online-rubiks-stopwatch-timer/)", false) ;

            info.addField(":c s", "Starts stopwatch", false);
            info.addField(":c n", "Stops stopwatch. Approximates and returns your time.", false);
            info.addField(":c -t -(<time>)", "Enter <Time> and timer starts. Reminds you when the time's up. \n <time> should be of the format = -mins -secs -ms", false);
            info.setThumbnail("https://cdn.discordapp.com/attachments/812010489248088088/879789517492600872/Comp_Cube.png");

            event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
            info.clear() ;

        }

        else if ( event.getComponentId().equals("make")) {
            EmbedBuilder info = new EmbedBuilder() ;
            info.setTitle("Making a cube", null);

            info.setColor(Color.red);
            info.setColor(new Color(0xF40C0C));
            info.setColor(new Color(255, 0, 54));

            info.addField("/make", "I'll give you 2 options - 2/3", false);
            info.addField("-->" , "If you want to make a solved 2x2 cube, then enter '2' as the input. /n If you want to make solved 3x3 cube, then endter '3' as the input", false) ;

            info.addBlankField(false) ;

            info.addField("/make", "You will be given 6 fields. Enter the colors on the cube in the order shown below", false);
            info.addField("-->" , "Here the input for the Green face will be : \n Now do the same for the other faces. \n" +
                    " # Pro Tip : Keep the GREEN face towards you and follow the order - Left Face, Front face, Right face, Back face, " +
                    "Bottom face, Top face. This will help you keep track of your inputs." , false) ;

            info.setThumbnail("https://cdn.discordapp.com/attachments/812010489248088088/879789517492600872/Comp_Cube.png");

            info.setImage("https://camo.githubusercontent.com/f654b8ffdf1335fc93aba3cf72edb86cb5d808377a88fca979c6a04b29ab5292/68747470733a2f2f63646e2e646973636f72646170702e636f6d2f6174746163686d656e74732f3831323031303632343330313236393031322f3834363031383737313930373131373038362f556e7469746c65645f64657369676e2e706e67");
            event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
            info.clear() ;

        }

        else if ( event.getComponentId().equals("do")) {
            EmbedBuilder info = new EmbedBuilder() ;
            info.setTitle("Executing moves", null);

            info.setColor(Color.red);
            info.setColor(new Color(0xF40C0C));
            info.setColor(new Color(255, 0, 54));

            info.addField("/do", "Tell me the number of moves, and if you want me to show you the cube after executing", false);
            info.addField("-->" , "Moves should be in this format - 'R U R' U' y B D'. If you add no spaces then I'll ignore it, watch out!" , false) ;
            info.addField("-->" , "Input 's' if you want me to send on chat otherwise, leave it empty." , false) ;


            info.addBlankField(false) ;

            info.addField("/rev", "I'll reverse the alg you give me", false);
            info.addField("-->" , "e.g - If your input is 'R U' I will use 'U' R'" , false) ;
            info.addField("-->" , "Input 's' for just getting reversed alg otherwise, leave it empty and I'll execute it!" , false) ;
            info.setThumbnail("https://cdn.discordapp.com/attachments/812010489248088088/879789517492600872/Comp_Cube.png");

            info.setImage("https://cdn.discordapp.com/attachments/709067090769870942/874209373063372800/ezgif.com-gif-maker.gif") ;
            event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
            info.clear() ;

        }
        else if ( event.getComponentId().equals("solve")) {
            EmbedBuilder info = new EmbedBuilder() ;
            info.setTitle("Executing moves", null);

            info.setColor(Color.red);
            info.setColor(new Color(0xF40C0C));
            info.setColor(new Color(255, 0, 54));

            info.addField("/solve", "I'll solve the cube for you and return the solution", false);
            info.addField("-->" , "Please make sure that you have entered the cube correctly, otherwise I won't be able to solve" , false) ;
            info.addField("-->" , "If I'm unable to solve the cube even then, please report a bug through the /support command like this - '/support <scramble>'" , false) ;


            info.addBlankField(false) ;

            info.addField("/solved", "I'll tell you if your cube is solved", false);
            info.setThumbnail("https://cdn.discordapp.com/attachments/812010489248088088/879789517492600872/Comp_Cube.png");

            info.setImage("https://cdn.discordapp.com/attachments/812010489248088088/878893412680626196/AJIDNCP0zq_-_Copy.gif");
            event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
            info.clear() ;

        }
    }
}
