package net.nerds.oysters.oysters;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.nerds.oysters.Oysters;

@Environment(EnvType.CLIENT)
public class OysterGui extends AbstractContainerScreen {

    public Identifier fishGui = new Identifier(Oysters.MODID, "textures/gui/oyster_gui.png");
    public OysterEntity tile;
    private String containerLabel;

    public OysterGui(OysterEntity oysterEntity, OysterContainer oysterContainer, String containerLabel, String textComponent) {
        super(oysterContainer, oysterContainer.playerInventory, new TranslatableText(textComponent));
        this.tile = oysterEntity;
        this.containerHeight = 146;
        this.containerLabel = containerLabel;
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
