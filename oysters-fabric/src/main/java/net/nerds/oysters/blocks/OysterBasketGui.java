package net.nerds.oysters.blocks;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.Generic3x3ContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.nerds.oysters.Oysters;

@Environment(EnvType.CLIENT)
public class OysterBasketGui extends HandledScreen<OysterBasketContainer> {

    public Identifier fishGui = new Identifier(Oysters.MODID, "textures/gui/oyster_basket_gui.png");
    public OysterBasketEntity tile;
    private String containerLabel = "";
    private final int rows = 5;

    public OysterBasketGui(OysterBasketEntity fishTrapBlockEntity, OysterBasketContainer fishTrapContainer, String containerLabel, String textComponent) {
        super(fishTrapContainer, fishTrapContainer.playerInventory, new TranslatableText(textComponent));
        this.tile = fishTrapBlockEntity;
        this.backgroundHeight = 133 + this.rows * 18;
        this.containerLabel = new TranslatableText(containerLabel).asString();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    public void drawForeground(MatrixStack matrices, int int_1, int int_2) {
        this.textRenderer.draw(matrices, this.containerLabel, 8.0F, 6.0F, 4210752);

        //this.textRenderer.draw(matrices, this.playerInventory.getDisplayName(), (float)this.playerInventoryTitleX, (float)this.playerInventoryTitleY, 4210752);
    }

    @Override
    public void drawBackground(MatrixStack matrices, float v, int i, int i1) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(fishGui);
        int w = (this.width - this.backgroundWidth) / 2;
        int h = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, w, h, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }
}