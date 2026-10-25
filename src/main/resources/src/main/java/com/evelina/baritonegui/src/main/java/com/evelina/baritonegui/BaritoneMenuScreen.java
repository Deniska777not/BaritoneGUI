package com.evelina.baritonegui;

import baritone.api.BaritoneAPI;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class BaritoneMenuScreen extends Screen {

    public BaritoneMenuScreen() {
        super(Text.literal("Baritone Control Panel"));
    }

    @Override
    protected void init() {
        int buttonWidth = 220;
        int buttonHeight = 20;
        int startX = (this.width - buttonWidth) / 2;
        int startY = this.height / 4;

        // Кнопка: Копка алмазов
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("💎 Добывать алмазы"),
                button -> runCommand("mine diamond_ore deepslate_diamond_ore")
        ).dimensions(startX, startY, buttonWidth, buttonHeight).build());

        // Кнопка: Копка железа
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("⚔️ Добывать железо"),
                button -> runCommand("mine iron_ore deepslate_iron_ore")
        ).dimensions(startX, startY + 28, buttonWidth, buttonHeight).build());

        // Кнопка: Сруб деревьев
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("🪓 Рубить деревья"),
                button -> runCommand("mine oak_log birch_log spruce_log dark_oak_log")
        ).dimensions(startX, startY + 56, buttonWidth, buttonHeight).build());

        // Кнопка: Сбор выпавших предметов
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("🎒 Собирать лут вокруг"),
                button -> runCommand("follow entity item")
        ).dimensions(startX, startY + 84, buttonWidth, buttonHeight).build());

        // Кнопка: Полная остановка бота
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("⛔ СТОП (Отмена задач)"),
                button -> runCommand("stop")
        ).dimensions(startX, startY + 120, buttonWidth, buttonHeight).build());
    }

    private void runCommand(String command) {
        try {
            if (BaritoneAPI.getProvider() != null && BaritoneAPI.getProvider().getPrimaryClientBaritone() != null) {
                BaritoneAPI.getProvider().getPrimaryClientBaritone().getCommandManager().execute(command);
            }
        } catch (Exception ignored) {}
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
