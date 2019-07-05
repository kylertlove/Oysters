package net.nerds.oysters.Utils;

import net.minecraft.item.Item;
import net.nerds.oysters.oysters.OysterBreed;

import java.util.Arrays;

public class OysterBreedUtility {


    public static Item[] getAllOysterBlockItem() {
        return Arrays.stream(OysterBreed.values())
                .map(OysterBreed::getOysterBlockItem)
                .toArray(Item[]::new);
    }

    public static Item[] getAllResouceItems() {
        return Arrays.stream(OysterBreed.values())
                .map(OysterBreed::getResourceItem)
                .toArray(Item[]::new);
    }

    public static boolean isAResource(Item item) {
        return Arrays.stream(OysterBreed.values())
                .anyMatch(oysterBreed -> item == oysterBreed.getResourceItem());

    }

    public static OysterBreed getBreedByBlockItem(Item item) {
        return Arrays.stream(OysterBreed.values())
                .filter(oysterBreed -> oysterBreed.getOysterBlockItem() == item)
                .findFirst().get();
    }
}
