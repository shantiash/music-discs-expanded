package com.shantiashams.musicdiscsexpanded.item;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import com.shantiashams.musicdiscsexpanded.util.ModIds;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/** Creates one self-populating creative tab for every registered disc. */
public final class ModCreativeTabs {
    private static final ResourceKey<CreativeModeTab> TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            ModIds.id("music_discs")
    );

    private static final String TITLE_TRANSLATION_KEY = "creativeTab." + ModIds.MOD_ID;
    private static boolean initialized;

    private ModCreativeTabs() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }

        ModItems.initialize();

        CreativeModeTab tab = FabricCreativeModeTab.builder()
                .icon(() -> new ItemStack(ModItems.get(DiscRegistry.first())))
                .title(Component.translatable(TITLE_TRANSLATION_KEY))
                .displayItems((parameters, output) -> {
                    for (DiscDefinition disc : DiscRegistry.all()) {
                        output.accept(ModItems.get(disc));
                    }
                })
                .build();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_KEY, tab);
        initialized = true;
    }

    public static String titleTranslationKey() {
        return TITLE_TRANSLATION_KEY;
    }
}
