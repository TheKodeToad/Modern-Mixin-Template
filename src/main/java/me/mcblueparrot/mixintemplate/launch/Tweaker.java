package me.mcblueparrot.mixintemplate.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.MixinEnvironment.Side;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

/**
 * Boilerplate tweaker for mixin client, modelled around Hyperium.
 * @author TheKodeToad
 */
public class Tweaker implements ITweaker {

	private static final String MAIN_CLASS = "net.minecraft.client.main.Main";

	private List<String> args = new ArrayList<>();

	@Override
	public void acceptOptions(List<String> args, File gameDir, File assetsDir, String version) {
		this.args.addAll(args);

		// these arguments are stolen by LaunchWrapper
		// pass them back to Minecraft

		if (gameDir != null) {
			this.args.add("--gameDir");
			this.args.add(gameDir.getPath());
		}

		if (assetsDir != null) {
			this.args.add("--assetsDir");
			this.args.add(assetsDir.toString());
		}

		// add supplied version to arguments...
		if (version != null) {
			this.args.add("--version");
			this.args.add(version);
		}
		// ...or ignore and override it
		// this.args.add("--version");
		// this.args.add("MixinTemplate");
	}

	@Override
	public void injectIntoClassLoader(LaunchClassLoader classLoader) {
		classLoader.addClassLoaderExclusion("org.slf4j");

		MixinBootstrap.init();

		// add your mixin configurations here
		// to ensure that they can modify classes
		Mixins.addConfiguration("mixintemplate.mixins.json");

		MixinEnvironment environment = MixinEnvironment.getDefaultEnvironment();

		// default to the vanilla obfuscated classes
		if (environment.getObfuscationContext() == null) {
			environment.setObfuscationContext("named:official");
		}

		environment.setSide(Side.CLIENT);
	}

	@Override
	public String getLaunchTarget() {
		return MAIN_CLASS;
	}

	@Override
	public String[] getLaunchArguments() {
		return args.toArray(String[]::new);
	}

}
