package me.mcblueparrot.mixintemplate.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraft {

	@Overwrite
	private String createTitle() {
		return "Mixin Client 1.0.0 | Minecraft 1.18.2";
	}

	@Inject(method = "getInstance", at = @At("HEAD"))
	private static void preGetInstance(CallbackInfoReturnable<Minecraft> cir) {
		System.out.println("Hello");
	}

}
