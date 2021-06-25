package me.marius.listeners;

import me.marius.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDateTime;

public class JoinListener implements Listener {

    private Main plugin;
    JDA bot;
    TextChannel textChannel;

    public JoinListener(JDA bot, Main plugin) {
        this.plugin = plugin;
        this.bot = bot;
        this.textChannel = bot.getTextChannelById(plugin.SERVER_INFOS);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();

        plugin.onlinePlayers.add(event.getPlayer());

        URL url = null;
        try {
            url = new URL("https://crafatar.com/avatars/" + event.getPlayer().getUniqueId().toString());

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setThumbnail(String.valueOf(url))
                    .setColor(Color.GREEN)
                    .setTitle(">>> Serverjoin")
                    .setDescription(event.getPlayer().getName() + " hat den Server betreten!")
                    .addField("Online-Spieler", "Aktuell sind " + plugin.onlinePlayers.size() + " Spieler online.", false)
                    .setTimestamp(LocalDateTime.now(Clock.systemUTC()));

            textChannel.sendMessage(embedBuilder.build()).queue();
            embedBuilder.clear();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
