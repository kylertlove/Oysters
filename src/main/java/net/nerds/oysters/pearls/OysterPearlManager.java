package net.nerds.oysters.pearls;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.oysters.OysterBreed;

import java.util.Arrays;

public class OysterPearlManager {

    public static void init() {
        Arrays.stream(OysterBreed.values())
                .forEach(oysterBreed -> {
                    Registry.register(Registry.ITEM,
                            new Identifier(Oysters.MODID, oysterBreed.getOysterPearl().getIdentifier()),
                            oysterBreed.getOysterPearl());
                });
    }
}
