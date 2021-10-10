import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;
import java.util.Collection;

public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] arg = event.getMessage().getContentRaw().split(" ");

        if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "commands")) {
            EmbedBuilder info = new EmbedBuilder() ;
            info.setTitle("Slash Command List", null);

            info.setColor(Color.red);
            info.setColor(new Color(0xF40C0C));
            info.setColor(new Color(255, 0, 54));

            info.addField("/make", "I'll make a cube for you. [How?](\"https://github.com/AkshathRaghav/cubot.io)", false);
            info.addField("/makeSolved", "I'll make a solved cube for you\n cube_type - 3 for 3x3, 2 for 2x2", false);
            info.addField("/cube_string", "You will get the text format of the cube you are using right now. You can use it make another cube. \n (Click on the make button below to understand)", false);
            info.addField("/type", "I'll let you know if I'm holding a 2x2 or 3x3 for you", false);


            info.addField("/show", "I'll show your cube \n show_type - s for public view, empty for private view", false);
            info.addField("/indexes", "Check your DM for the indexed presentation of the cube. [What?](\"https://github.com/AkshathRaghav/cubot.io) \n show_type - dm for a DM, empty for chat view", false);

            info.addField("/do", "Executes the moves. \n moves - moves to execute \n show_type - s for chat view, empty for no /show", false);
            info.addField("/rev", "Reverses the moves. \n moves - moves to execute \n show_type - s for getting reversed alg, empty for executing moves", false);
            info.addField("/getStore", "Returns all the moves done on the Cube at the start of the session", false);


            info.addField("/solve", "I will solve the cube for you \n ( If something goes wrong, I'll tell you why )", false);
            info.addField("/solved", "I'll tell you if your cube is solved", false);

            info.addField("/scramble", "I'll scramble the cube for you \n number - Number of moves in scramble \n mode - s - only the scramble will be returned, empty - your cube will be scrambled", false);


            info.addField("/add_time", "I'll add your time to my database. \n mins - Number of mintues \n secs - Number of secs \n ms - Number of millseconds", false);
            info.addField("/get_best", "I'll return your best time!", false);
            info.addField("/get_avg", "I'll return your avg time!", false);
            event.getChannel().sendMessage(info.build()).queue();
            info.clear() ;

        }

        else if (arg.length > 1 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "help")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("cubord.io", "https://github.com/AkshathRaghav/cubord.io");

            info.setColor(Color.red);
            info.setColor(new Color(0xF40C0C));
            info.setColor(new Color(255, 0, 54));

            info.addField("Cubord is a quick and easy way for you to cube through your keyboard", "Watch the official [demo](https://www.youtube.com/watch?v=xvFZjo5PgG0)! \n\n To get started, use /help or :c help \n", false);

            info.addField("Commands", "A full list of commands is available [here](https://github.com/AkshathRaghav/cubord.io/tree/master/Docs)", true);
            info.addField("Support", "Click [here](https://www.youtube.com/watch?v=xvFZjo5PgG0) if you're having trouble or have any questions.\n", true);
            info.addField("Github", " \n Cubord is an implementation of [cubot.io](https://github.com/AkshathRaghav/cubot.io), an Open Source java library for the Rubiks Cube.\n Visit the Github [Repo](https://github.com/AkshathRaghav/cubord.io). \n", false);
            info.setImage("https://cdn.discordapp.com/attachments/812010489248088088/866530400582762506/finalcover.png");

            info.setThumbnail("https://cdn.discordapp.com/attachments/797420478574362634/866674337837875220/cubot.png");
            event.getChannel().sendMessage(info.build()).queue();
            info.clear() ;
        }
    }
}
