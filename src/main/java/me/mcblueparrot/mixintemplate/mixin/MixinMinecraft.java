package me.mcblueparrot.mixintemplate.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public class MixinMinecraft {

	@Overwrite
	private String createTitle() {
		return "Mixin Client 1.0.0 | Minecraft 1.18.2";
	}

}
