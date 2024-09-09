package me.chaunmt.gmapebot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ValheimCommand {
    public static void run(SlashCommandInteractionEvent event) {
        final String replyString = """
                Server info:
                > Valheim IP: apegm.datho.st:21831
                > Password: Woow00kek
                > (Steam IP: apegm.datho.st:21832)
                
                Required mods:
                1. [PlantEverything - Advize (tested)](<https://valheim.thunderstore.io/package/Advize/PlantEverything/>)
                2. [InfiniteTorches - Frogger (tested)](<https://valheim.thunderstore.io/package/Frogger/InfiniteTorches/>)                    
                3. [Leash - CookieMilk (tested)](<https://valheim.thunderstore.io/package/CookieMilk/Leash/>)
                
                Suggested mods:
                1. [PlantEasily - Advize (tested)](<https://valheim.thunderstore.io/package/Advize/PlantEasily/>)
                2. [EquipmentAndQuickSlots - RandyKnapp (tested)](<https://valheim.thunderstore.io/package/RandyKnapp/EquipmentAndQuickSlots/>)
                3. [CraftFromContainers - aedenthorn_mods (tested)](<https://valheim.thunderstore.io/package/aedenthorn_mods/CraftFromContainers/>)
                4. [AAA_Crafting - Azumatt (tested)](<https://valheim.thunderstore.io/package/Azumatt/AAA_Crafting/>)
                
                Stopped working mods:
                1. [AutoFeed - ALY (tested)](<https://valheim.thunderstore.io/package/ALY/AutoFeed/>)
                """;

        event.reply(replyString).queue();
    }
}
