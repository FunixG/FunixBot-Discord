package fr.funixgaming.bot.Events;

import fr.funixgaming.bot.Utils.Log;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserMessage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;
        User user = e.getAuthor();
        TextChannel channel = e.getChannel();
        Message message = e.getMessage();

        Log.logMessage(user, channel, message);
    }
}
