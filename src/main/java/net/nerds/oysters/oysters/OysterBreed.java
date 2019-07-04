package net.nerds.oysters.oysters;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.nerds.oysters.Oysters;

public enum OysterBreed {
    PLAIN("plain_oyster", "plain_oyster_container"),
    BONE_MEAL("bone_meal_oyster", "bone_meal_oyster_container"),
    COAL("coal_oyster", "coal_oyster_container"),
    IRON("iron_oyster", "iron_oyster_container"),
    GOLD("gold_oyster", "gold_oyster_container"),
    DIAMOND("diamond_oyster", "diamond_oyster_container"),
    LAPIS("lapis_oyster", "lapis_oyster_container"),
    EMERALD("emerald_oyster", "emerald_oyster_container");

    private Identifier identifier;
    private Identifier containerIdentifier;
    private OysterBlock oysterBlock;
    public BlockEntityType<BlockEntity> blockEntityType;

    private OysterBreed(String name, String containerName) {
        identifier = new Identifier(Oysters.MODID, name);
        containerIdentifier = new Identifier(Oysters.MODID, containerName);
        oysterBlock = new OysterBlock();
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public OysterBlock getOysterBlock() {
        return oysterBlock;
    }

    public Identifier getContainerIdentifier() {
        return containerIdentifier;
    }

    public BlockEntityType<BlockEntity> getBlockEntityType() {
        return blockEntityType;
    }
}
