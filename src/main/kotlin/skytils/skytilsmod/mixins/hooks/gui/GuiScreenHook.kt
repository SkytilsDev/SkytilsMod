/*
 * Skytils - Hypixel Skyblock Quality of Life Mod
 * Copyright (C) 2021 Skytils
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package skytils.skytilsmod.mixins.hooks.gui

import net.minecraft.client.gui.GuiScreen
import net.minecraft.util.IChatComponent
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import skytils.skytilsmod.Skytils
import skytils.skytilsmod.events.impl.SendChatMessageEvent
import skytils.skytilsmod.utils.Utils

fun onSendChatMessage(message: String, addToChat: Boolean, ci: CallbackInfo) {
    if (SendChatMessageEvent(message, addToChat).postAndCatch()) ci.cancel()
}

fun onComponentClick(component: IChatComponent, cir: CallbackInfoReturnable<Boolean>) {
    if (Utils.isOnHypixel && Skytils.config.chatTabs && Skytils.config.copyChat && GuiScreen.isCtrlKeyDown()) cir.returnValue =
        false
}