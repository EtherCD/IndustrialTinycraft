package cd.ethercd.it.utils.gui;

import cd.ethercd.it.IndustrialTinyCraft;
import ic2.core.GuiIC2;
import ic2.core.gui.VanillaButton;
import ic2.core.init.Localization;
import ic2.core.util.Util;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiElectricStorageBlock extends GuiIC2<ContainerElectricStorageBlock> {
    private static final ResourceLocation background = new ResourceLocation(IndustrialTinyCraft.MODID, "textures/gui/advanced_mfsu.png");

    public GuiElectricStorageBlock(final ContainerElectricStorageBlock container) {
        super(container, 196);
        this.addElement(((new VanillaButton(this, 152, 4, 20, 20, this.createEventSender(0))).withIcon(() -> new ItemStack(Items.REDSTONE))).withTooltip(container.base::getRedstoneMode));
//        this.addElement(((new VanillaButton(this, 152, 4, 20, 20, this.createEventSender(0))).withTooltip(() -> ((TileEntityElectricBlock)container.base).getRedstoneMode()));
    }

    protected void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        int e = (int)Math.min(this.container.base.energy.getEnergy(), this.container.base.energy.getCapacity());
        this.fontRenderer.drawString(Localization.translate("ic2.EUStorage.gui.info.armor"), 8, this.ySize - 126 + 3, 4210752);
        this.fontRenderer.drawString(Localization.translate("ic2.EUStorage.gui.info.level"), 79, 25, 4210752);
        this.fontRenderer.drawString(" " + Util.toSiString(e, 4), 110, 35, 4210752);
        this.fontRenderer.drawString("/" + Util.toSiString((int)this.container.base.energy.getCapacity(), 4), 110, 45, 4210752);
        String output = Localization.translate("ic2.EUStorage.gui.info.output", this.container.base.getOutput());
        this.fontRenderer.drawString(output, 79, 60, 4210752);
        String s = Localization.translate("industrialtinycraft.storages.lithium_mfsu");
        fontRenderer.drawString(s, xSize / 2 - fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        fontRenderer.drawString(Localization.translate("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override()
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(background);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        double energy = this.container.base.energy.getEnergy();
        double capacity = this.container.base.energy.getCapacity();
        int barWidth = 24;
        int filled = 0;

        if (capacity > 0) {
            filled = (int) Math.round((energy / capacity) * barWidth);
        }

        if (filled > 0) {
            drawTexturedModalRect(k + 78, l + 34, 176, 0, filled + 1, 17);
        }
    }

    @Override
    protected void renderHoveredToolTip(int mouseX, int mouseY) {
        super.renderHoveredToolTip(mouseX, mouseY);

        int e = (int)Math.min(this.container.base.energy.getEnergy(), this.container.base.energy.getCapacity());

        if (mouseX > this.guiLeft + 75 && mouseX < this.guiLeft + 106 && mouseY > this.guiTop + 34 && mouseY < this.guiTop + 50) {
            this.drawHoveringText(Util.toSiString(e, 4) + " / " + Util.toSiString(this.container.base.energy.getCapacity(), 4) + " EU", mouseX, mouseY);
        }
    }

    protected ResourceLocation getTexture() {
        return background;
    }
}
