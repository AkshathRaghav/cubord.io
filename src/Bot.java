import net.codejava.Update;
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

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

public class Bot extends ListenerAdapter {
    public static JDA jda ;
    public final static String prefix = ":c" ;
    private String[] temp3d = {"RRRRRRRRR", "GGGGGGGGG", "OOOOOOOOO", "BBBBBBBBB", "WWWWWWWWW", "YYYYYYYYY"};
    private String[] temp2d = {"RRRR", "GGGG", "OOOO", "BBBB", "WWWW", "YYYY"};
    public Cubot cube   = new Cubot(temp3d);

        public static void main(String[] args) throws LoginException, InterruptedException {
            jda = JDABuilder.createDefault("ODQ3MTExNDEwNjE3Njc5ODk0.YK5T-g.X0Madg30Dr2_dOvolYI2-EKevoQ").addEventListeners(new Bot()).setActivity(Activity.playing("with Cubes for you")).setStatus(OnlineStatus.DO_NOT_DISTURB).build();
            jda.awaitReady();
            jda.addEventListener(new Commands());
            jda.addEventListener(new TextCubeCommands());
            jda.addEventListener(new Timing());
            Guild guild = jda.getGuildById("709067090769870938") ;
            CommandListUpdateAction commands = guild.updateCommands();

            commands.addCommands(
                    new CommandData("make", "Create your custom cube. Check the docs on how to set input!")
                            .addOptions(new OptionData(STRING, "left_face", "Face1").setRequired(true),
                                    new OptionData(STRING, "front_face" , "Face2").setRequired(true),
                                    new OptionData(STRING, "right_face" , "Face3").setRequired(true),
                                    new OptionData(STRING, "back_face" , "Face4").setRequired(true),
                                    new OptionData(STRING, "bottom_face" , "Face5").setRequired(true),
                                    new OptionData(STRING, "top_face" , "Face6").setRequired(true)
                                    )

            );

            commands.queue();
        }


        @Override
        public void onSlashCommand(SlashCommandEvent event)
        {
            // Only accept commands from guilds
            if (event.getGuild() == null)
                return;
            if  (event.getName().equals("make")) {
                make(event, event.getOption("left_face").getAsString(), event.getOption("front_face").getAsString(),
                        event.getOption("right_face").getAsString(),
                        event.getOption("back_face").getAsString(),
                        event.getOption("bottom_face").getAsString(),
                        event.getOption("top_face").getAsString()
                        ); // content is required so no null-check here

            }
            else {
                event.reply("I can't handle that command right now :(").setEphemeral(true).queue();
            }
        }

        public void make(SlashCommandEvent event, String f1, String f2, String f3, String f4, String f5, String f6)
        {
            boolean solvedatfirst = false;
            String[] arg2 = new String[]{f1, f2, f3, f4, f5, f6};
            for (String i : arg2 ) {
                System.out.println(i);
            }
            try {
                cube = new Cubot(arg2);
                if (!(cube.isValid())) {
                    cube = null;
                    System.out.println("ok");
                    event.reply("CubeError : Incorrect Input Format. Check the Docs on how to make a cube").setEphemeral(true).queue();
                } else {
                    if (cube.isSolved()) {
                        solvedatfirst = true;
                    }
                    try {
                        String s = event.getMember().getId() ;
                        Update.setCubeSQL(s, " ", f1+ " " + f2 + " " + f3 + " " + f4 + " " + f5 + " " +  f6 );
                        Update.setSolvedSQL(s, String.valueOf(solvedatfirst));
                        Update.setTimeSQL(s, "0" , true);
                        Update.setTimeSQL(s, "0" , false);
                        event.reply("Cube Created!").setEphemeral(true).queue();
                    }
                    catch (Error e) {
                        event.reply("Sorry, try again later!").setEphemeral(true).queue();
                    }
                }
            } catch (IllegalArgumentException e) {
                event.reply("CubeError : Incorrect Input Format. Check the Docs on how to make a cube").setEphemeral(true).queue();
            }
        }
}
