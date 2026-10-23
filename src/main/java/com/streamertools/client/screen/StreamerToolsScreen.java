package com.streamertools.client.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class StreamerToolsScreen extends Screen {
    private static final int TAB_WIDTH = 140;
    private static final int TAB_HEIGHT = 26;

    private int selectedTab = 0;
    private final TextFieldWidget itemField;
    private final TextFieldWidget valueField;
    private final TextFieldWidget minBidField;
    private final TextFieldWidget durationField;
    private final ButtonWidget startAuctionButton;
    private final ButtonWidget drawCardButton;

    public StreamerToolsScreen() {
        super(Text.literal("Streamer Tools"));

        this.itemField = new TextFieldWidget(
                null,
                0,
                0,
                220,
                20,
                Text.literal("Item")
        );
        this.itemField.setText("Diamond Sword");

        this.valueField = new TextFieldWidget(
                null,
                0,
                0,
                220,
                20,
                Text.literal("Value")
        );
        this.valueField.setText("250");

        this.minBidField = new TextFieldWidget(
                null,
                0,
                0,
                220,
                20,
                Text.literal("Min Bid")
        );
        this.minBidField.setText("50");

        this.durationField = new TextFieldWidget(
                null,
                0,
                0,
                220,
                20,
                Text.literal("Duration")
        );
        this.durationField.setText("180");

        this.startAuctionButton = ButtonWidget.builder(
                Text.literal("Start Auction"),
                button -> {
                    // Auction implementation hook.
                }
        ).dimensions(0, 0, 180, 20).build();

        this.drawCardButton = ButtonWidget.builder(
                Text.literal("Draw Card"),
                button -> {
                    // Paper game hook.
                }
        ).dimensions(0, 0, 180, 20).build();
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        int tabStartX = centerX - 170;
        int tabY = centerY - 140;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Auction"), button -> selectedTab = 0)
                .dimensions(tabStartX, tabY, TAB_WIDTH, TAB_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Paper Game"), button -> selectedTab = 1)
                .dimensions(tabStartX + TAB_WIDTH, tabY, TAB_WIDTH, TAB_HEIGHT)
                .build());

        if (selectedTab == 0) {
            itemField.setPosition(centerX - 110, centerY - 80);
            valueField.setPosition(centerX - 110, centerY - 40);
            minBidField.setPosition(centerX - 110, centerY);
            durationField.setPosition(centerX - 110, centerY + 40);
            startAuctionButton.setPosition(centerX - 90, centerY + 90);

            this.addDrawableChild(itemField);
            this.addDrawableChild(valueField);
            this.addDrawableChild(minBidField);
            this.addDrawableChild(durationField);
            this.addDrawableChild(startAuctionButton);
        } else {
            drawCardButton.setPosition(centerX - 90, centerY - 10);
            this.addDrawableChild(drawCardButton);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        context.drawCenteredTextWithShadow(this.textRenderer, "Auction / Paper Game", centerX, centerY - 150, 0xFFFFFF);

        if (selectedTab == 0) {
            context.drawTextWithShadow(this.textRenderer, "Item", centerX - 110, centerY - 100, 0xFFFFFF);
            context.drawTextWithShadow(this.textRenderer, "Value", centerX - 110, centerY - 60, 0xFFFFFF);
            context.drawTextWithShadow(this.textRenderer, "Min Bid", centerX - 110, centerY - 20, 0xFFFFFF);
            context.drawTextWithShadow(this.textRenderer, "Duration (seconds)", centerX - 110, centerY + 20, 0xFFFFFF);
        } else {
            context.drawTextWithShadow(this.textRenderer, "Paper Game", centerX - 60, centerY - 60, 0xFFFFFF);
            context.drawTextWithShadow(this.textRenderer, "Draw a card and gamble your paper stake.", centerX - 120, centerY - 30, 0xFFFFFF);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
