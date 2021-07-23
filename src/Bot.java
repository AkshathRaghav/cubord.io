import net.codejava.Update;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import javax.security.auth.login.LoginException;

import java.awt.*;
import java.util.Collection;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

public class Bot extends ListenerAdapter {
    public static JDA jda;
    public final static String prefix = ":c";
    private String[] temp3d = {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
    private String[] temp2d = {"RRRR", "GGGG", "OOOO", "BBBB", "WWWW", "YYYY"};
    public Cubot cube = new Cubot(temp3d);

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
        jda = JDABuilder.createDefault("ODQ3MTExNDEwNjE3Njc5ODk0.YK5T-g.X0Madg30Dr2_dOvolYI2-EKevoQ").addEventListeners(new Bot()).setActivity(Activity.playing("with Cubes for you")).setStatus(OnlineStatus.DO_NOT_DISTURB).build();
        jda.awaitReady();
        jda.addEventListener(new Commands());
        jda.addEventListener(new TextCubeCommands());
        jda.addEventListener(new Timing());
        Guild guild = jda.getGuildById("709067090769870938");
        CommandListUpdateAction commands = guild.updateCommands();

        commands.addCommands(
                new CommandData("help", "Help command"),
                new CommandData("commands", "List of commands"),
                new CommandData("make", "Create your custom cube. Check the docs on how to set input!")
                        .addOptions(new OptionData(STRING, "left_face", "Face1").setRequired(true),
                                new OptionData(STRING, "front_face", "Face2").setRequired(true),
                                new OptionData(STRING, "right_face", "Face3").setRequired(true),
                                new OptionData(STRING, "back_face", "Face4").setRequired(true),
                                new OptionData(STRING, "bottom_face", "Face5").setRequired(true),
                                new OptionData(STRING, "top_face", "Face6").setRequired(true)
                        ),
                new CommandData("make_solved", "Makes a solved cube for you").addOptions(
                        new OptionData(STRING, "cube_type", "3 - 3x3 or 2 - 2x2 or empty - Updates present cube")),
                new CommandData("show", "Visualize your cube!").addOptions(
                        new OptionData(STRING, "show_type", "s - Everyone can see, empty - Only you can see")),
                new CommandData("type", "Find out what type of cube you have right now!"),
                new CommandData("get_store", "Get a record of the moves you have performed on your cube"),
                new CommandData("solved", "Tells you if your cube is solved"),
                new CommandData("indexes", "Sends you the Cube indexes and colors").addOptions(
                        new OptionData(STRING, "show_type", "dm - Sends to DM, empty - Sends on chat")),
                new CommandData("cube_string", "Sends you the cube like an input ( Use it to make another cube )"),
                new CommandData("die", "But why?")

        );
        commands.queue();
    }


    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        if (event.getName().equals("make")) {
            make(event, event.getOption("left_face").getAsString(), event.getOption("front_face").getAsString(),
                    event.getOption("right_face").getAsString(),
                    event.getOption("back_face").getAsString(),
                    event.getOption("bottom_face").getAsString(),
                    event.getOption("top_face").getAsString()
            ); // content is required so no null-check here
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
        } else if (event.getName().equals("die")) {
            die(event);
        } else {
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
            tempCheck = false ; // to prevent a double reply from the bot
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
            } else if (check.equals("s")) {
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
                s = "Solved";
            } else {
                s = "Not Solved";
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

    public void die(SlashCommandEvent event) {
        event.reply("やめて ください \uD83E\uDD24").setEphemeral(true).queue();
    }
    public void help(SlashCommandEvent event) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("cubord.io", "https://github.com/AkshathRaghav/cubord.io");

        info.setColor(Color.red);
        info.setColor(new Color(0xF40C0C));
        info.setColor(new Color(255, 0, 54));

        info.addField("Cubord is a quick and easy way for you to cube through your keyboard", "Watch the official [demo](https://www.youtube.com/watch?v=xvFZjo5PgG0)! \n\n To get started, use :c commandlist \n Or click [here](https://www.youtube.com/watch?v=xvFZjo5PgG0) to visit the website \n", false);

        info.addField("Commands", "A full list of commands is available [here](https://www.youtube.com/watch?v=xvFZjo5PgG0)", true);
        info.addField("Support", "Click [here](https://www.youtube.com/watch?v=xvFZjo5PgG0) if you're having trouble or have any questions.\n", true);
        info.addField("Github", " \n Cubord is an implementation of [cubot.io](https://github.com/AkshathRaghav/cubot.io), an Open Source java library for the Rubiks Cube.\n Visit the Github [Repo](https://www.youtube.com/watch?v=xvFZjo5PgG0). \n", false);
        info.setImage("https://cdn.discordapp.com/attachments/812010489248088088/866530400582762506/finalcover.png");

        info.setThumbnail("https://cdn.discordapp.com/attachments/797420478574362634/866674337837875220/cubot.png");
        event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
        info.clear() ;

    }

    public void commands(SlashCommandEvent event) {
        EmbedBuilder info = new EmbedBuilder();

        info.setTitle("Cubord Command List", null);

        info.setColor(Color.red);
        info.setColor(new Color(0xF40C0C));
        info.setColor(new Color(255, 0, 54));

        info.addField("", "A full list of commands is available [here](https://www.youtube.com/watch?v=xvFZjo5PgG0)",true);


        info.addField(":c make", "Makes a cube for you, and returns if it was successful", false);
        info.addField(":c makeSolved", "Replaces your cube with a Solved cube", false);
        info.addField(":c makeSolved (-2x2/-3x3)", "Makes solved cube of given type", false);
        info.addField(":c type?", "Returns wether your cube is 2x2 or 3x3", false);


        info.addField(":c show", "Prints your cube", false);
        info.addField(":c indexes", "Sends you the indexed cube as a DM", false);

        info.addField(":c do -(<Insert moves>) -(s/d)", "Executes the moves. -s --> shows cube, -d --> doesn't show cube", false);
        info.addField(":c rev -(<Insert moves>) -(y/n)", "Reverses the moves. -y --> executes the reverse as well, -n --> just returns the reverse alg", false);
        info.addField(":c getStore", "Returns all the moves done on the Cube at the start of the session", false);


        info.addField(":c solve", "Solves the cube and returns the solution ( if something goes wrong, it returns why )", false);
        info.addField(":c solved?", "Returns state of Cube", false);

        info.addField(":c scramble -(<No. of Moves>) -(s/d)", "Gets you a scramble of requested length. -y --> executes the scramble, -d --> doesn't scramble your Cube \n Erases your store history", false);

        info.addField(":c s", "Starts stopwatch", false);
        info.addField(":c n", "Stops stopwatch. Approximates and returns your time.", false);
        info.addField(":c -t -(<time>)", "Enter <Time> and timer starts. Reminds you when the time's up.", false);

        info.addField(":c die", "Don't do this :/", false);

        info.addBlankField(false);
        info.addField("", "Watch the official [demo!](https://www.youtube.com/watch?v=xvFZjo5PgG0)", false);



        event.replyEmbeds((MessageEmbed) info.build()).setEphemeral(true).queue();
        info.clear() ;
    }
}
