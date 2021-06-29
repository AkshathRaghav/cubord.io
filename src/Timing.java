import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.* ;
import java.awt.event.*;
import javax.swing.Timer;
import static java.lang.Math.abs;

public class Timing extends ListenerAdapter  {
    Dictionary<String, Long> times = new Hashtable<String, Long>();
    Dictionary<String, Boolean> timestart = new Hashtable<>();
    ArrayList<String> timerlist = new ArrayList<>() ;
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] arg = event.getMessage().getContentRaw().split(" ");
        if  ( arg.length == 2 && (arg[0] + arg[1]).equalsIgnoreCase(Bot.prefix + "s") ) {
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
                int mins = (int) (time/60) ;
                String timesend   ;
                if ( mins != 0 ) {
                    time = time%60 ;
                    timesend = String.valueOf(mins)+ " minutes " + String.valueOf(abs(time)).substring(0,5) ;
                }
                else { timesend = String.valueOf(abs(time)) ; }
                timestart.put(n, false);
                EmbedBuilder info = new EmbedBuilder();
                info.setColor(0Xa80d2c);
                info.addField(n + "'s time : ", timesend + " seconds", true);

                timestart.remove(n);
                times.remove(n) ;
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
    }

}
