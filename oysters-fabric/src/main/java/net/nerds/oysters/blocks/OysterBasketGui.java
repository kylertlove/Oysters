package net.nerds.oysters.blocks;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.nerds.oysters.Oysters;

@Environment(EnvType.CLIENT)
public class OysterBasketGui extends ContainerScreen<OysterBasketContainer> {

    public Identifier fishGui = new Identifier(Oysters.MODID, "textures/gui/oyster_basket_gui.png");
    public OysterBasketEntity tile;
    private String containerLabel = "";
    private final int rows = 5;

    public OysterBasketGui(OysterBasketEntity fishTrapBlockEntity, OysterBasketContainer fishTrapContainer, String containerLabel, String textComponent) {
        super(fishTrapContainer, fishTrapContainer.playerInventory, new TranslatableText(textComponent));
        this.tile = fishTrapBlockEntity;
        this.containerHeight = 133 + this.rows * 18;
        this.containerLabel = new TranslatableText(containerLabel).asString();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(int var1, int var2, float var3) {
        this.renderBackground();
        super.render(var1, var2, var3);
        this.drawMouseoverTooltip(var1, var2);
    }

    @Override
    public void drawForeground(int int_1, int int_2) {
        this.font.draw(this.containerLabel, 8.0F, 6.0F, 4210752);
    }

    @Override
    public void drawBackground(float v, int i, int i1) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(fishGui);
        int w = (this.width - this.containerWidth) / 2;
        int h = (this.height - this.containerHeight) / 2;
        this.blit(w, h, 0, 0, this.containerWidth, this.containerHeight);
    }
}