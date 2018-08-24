package io.github.aquerr.pandobot;

import io.github.aquerr.pandobot.commands.Commands;
import io.github.aquerr.pandobot.events.MessageListener;
import io.github.aquerr.pandobot.secret.SecretProperties;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;

import java.util.ArrayList;
import java.util.List;

public class PandoBot
{
    public static Commands commands = new Commands();

    public static void main(String[] args)
    {
        try
        {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(SecretProperties.BOT_TOKEN)
                    .setGame(Game.of(Game.GameType.DEFAULT, "Pandaria", "https://github.com/Aquerr/PandoBot"))
                    .buildBlocking();

            System.out.println("Setting up commands...");


            System.out.println("Connectd!");
            jda.addEventListener(new MessageListener());
            jda.setAutoReconnect(true);

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void processCommand(User author, MessageChannel channel, Message message)
    {
//        String text = message.getContentDisplay().substring(1);
//        String command = text.split(" ")[0];
//
//        if (!commands.containsCommand(command))
//            return;
//
//        String argsText = text.substring(command.length());
//        String[] args = argsText.split("\" \"");
//
//        commands.executeCommand(command, author, args);


        String text = message.getContentDisplay().substring(1);
        String commandAlias = text.split(" ")[0];

        if (!commands.containsCommand(commandAlias))
            return;

        boolean isArgument = false;
        List<String> argsList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : text.substring(commandAlias.length()).toCharArray())
        {
            if (character == '\"' && !isArgument)
            {
                isArgument = true;
            }
            else if(character == '\"' && isArgument)
            {
                isArgument = false;
                argsList.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
            else if(isArgument)
            {
                stringBuilder.append(character);
            }
        }

        commands.executeCommand(commandAlias, author, channel, argsList);
    }
}
