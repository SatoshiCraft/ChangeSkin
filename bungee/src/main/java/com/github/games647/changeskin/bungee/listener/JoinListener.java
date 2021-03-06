package com.github.games647.changeskin.bungee.listener;

import com.github.games647.changeskin.bungee.ChangeSkinBungee;
import com.github.games647.changeskin.core.model.SkinData;
import com.github.games647.changeskin.core.model.UserPreference;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class JoinListener extends AbstractSkinListener {

    public JoinListener(ChangeSkinBungee plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLogin(PostLoginEvent postLoginEvent) {
        ProxiedPlayer player = postLoginEvent.getPlayer();

        //updates to the chosen one
        UserPreference preferences = plugin.getLoginSession(player.getPendingConnection());
        if (preferences == null) {
            return;
        }

        SkinData targetSkin = preferences.getTargetSkin();
        if (targetSkin == null) {
            setRandomSkin(preferences, player);
        } else {
            plugin.applySkin(player, targetSkin);
        }
    }
}
