package io.github.aquerr.pandobot.commands;

import io.github.aquerr.pandobot.PandoBot;
import io.github.aquerr.pandobot.annotations.BotCommand;
import io.github.aquerr.pandobot.entities.VTEAMRoles;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

@BotCommand(minRole = VTEAMRoles.EVERYONE, argsCount = 1)
public class ClearCommand implements ICommand
{
    @Override
    public boolean execute(User user, MessageChannel channel, List<String> args)
    {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(PandoBot.PANDA_EMBED_COLOR);
        embedBuilder.addField(":warning: Czy na pewno chcesz usunąć (" + args.get(0) + ") wiadomości?", "", false);
        MessageEmbed messageEmbed = embedBuilder.build();
        channel.sendMessage(messageEmbed).queue();

//        List<Message> messagesList = channel.getHistory().retrievePast(Integer.parseInt(args.get(0)) + 1).complete();
//
//        messagesList.parallelStream().forEach(x->x.delete().queue());
        return true;
    }

    public String getUsage()
    {
        return "!clear \"ilość\"";
    }
}
