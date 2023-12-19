package com.charles445.simpledifficulty.client.gui;

import java.util.Random;

import com.charles445.simpledifficulty.api.SDCapabilities;
import com.charles445.simpledifficulty.api.SDItems;
import com.charles445.simpledifficulty.api.config.ClientConfig;
import com.charles445.simpledifficulty.api.config.ClientOptions;
import com.charles445.simpledifficulty.api.config.QuickConfig;
import com.charles445.simpledifficulty.api.temperature.ITemperatureCapability;
import com.charles445.simpledifficulty.api.temperature.TemperatureEnum;
import com.charles445.simpledifficulty.api.temperature.TemperatureUtil;
import com.charles445.simpledifficulty.config.ModConfig;
import com.charles445.simpledifficulty.util.RenderUtil;
import com.charles445.simpledifficulty.util.WorldUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TemperatureGui
{
	private final Minecraft mc = Minecraft.getMinecraft();
	
	private int updateCounter = 0;
	private final Random rand = new Random();
	public static final ResourceLocation ICONS = new ResourceLocation("simpledifficulty:textures/gui/icons.png");
	public static final ResourceLocation TEMPHUD = new ResourceLocation("simpledifficulty:textures/gui/temphud.png");
	//Position on the icons sheet
	private static final int classicTexturePos_X = 0;
	private static final int classicTexturePos_Y = 32;
	//Dimensions of the icon
	private static final int classicTextureWidth = 16;
	private static final int classicTextureHeight = 16;
	
	private static final int modernTexturePos_X = 0;
	private static final int modernTexturePos_Y = 0;
	private static final int modernTextureWidth = 16;
	private static final int modernTextureHeight = 16;
	private static final int modernFeelPos_X = 0;
	private static final int modernFeelPos_Y = 16;
	private static final int modernFeelWidth = 32;
	private static final int modernFeelHeight = 32;
	private static final int modernArrowPos_X = 0;
	private static final int modernArrowPos_Y = 144;
	private static final int modernArrowFrames = 14; //0 - 14 = 15 frames
	
	private int oldTemperature = -1;
	private int frameCounterClassic = -1;
	private int frameCounterModern = -1;
	private boolean risingTemperature = false;
	private boolean startAnimation = false;
	private boolean shakeSide = false;
	
	private static final int texturepos_Y_alt_OVR = 80;
	private static final int texturepos_Y_alt_BG = 96;
	private int alternateTemperature = 0;
	
	private int worldThermometerTemperature = 0;
	private boolean hasThermometer = false;
	private static final int texturepos_X_therm = 0;
	private static final int texturepos_Y_therm = 192;
	private static final int thermometer_per_row = 8;
	private static final int textureWidthTherm = 16;
	private static final int textureHeightTherm = 16;
	
	@SubscribeEvent
	public void onPreRenderGameOverlay(RenderGameOverlayEvent.Pre event)
	{	
	}
	
	private int getTempHudFeelBGX(int temperature)
	{
	}
	
	private int getTempHudFeelBGY(int temperature)
	{
	}
	
	private int getTempHudCoreBGX(int temperature)
	{
	}
	
	private void renderTemperatureIcon(int width, int height, int temperature)
	{
	}
	
	private void renderClassicTemperatureIcon(int width, int height, int temperature)
	{
	}
	
	private void renderTemperatureChangeAnimation(boolean classic, int x, int y, int temperature)
	{
	}
	
	private void renderThermometer(int x, int y)
	{
	}
	
	private int getShakeFrequency(TemperatureEnum tempEnum, int temperature)
	{
	}
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
	}
	
	private void bind(ResourceLocation resource)
	{
	}
}
