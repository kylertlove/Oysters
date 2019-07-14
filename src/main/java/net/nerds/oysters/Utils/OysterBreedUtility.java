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

    public static OysterBreed getBreedByResourceItem(Item item) {
        return Arrays.stream(OysterBreed.values())
                .filter(oysterBreed -> oysterBreed.getResourceItem() == item)
                .findFirst().get();
    }

    public static OysterBreed.OysterTier getTierOfBreedForMutation(OysterBreed breed) {
        switch (breed) {
            case BLEMISHED: return OysterBreed.OysterTier.BLEMISHED;
            case CLEAN: return OysterBreed.OysterTier.CLEAN;
            case FLAWLESS: return OysterBreed.OysterTier.FLAWLESS;
            default: return breed.getOysterTier();
        }
    }

    public static boolean isRightTierForBreeding(OysterBreed.OysterTier oysterTier, Item itemAttemptingToMutate) {
        OysterBreed.OysterTier tierOfResource = getBreedByResourceItem(itemAttemptingToMutate).getOysterTier();
        return oysterTier == tierOfResource;
    }
}
