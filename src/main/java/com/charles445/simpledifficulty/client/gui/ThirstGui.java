package com.charles445.simpledifficulty.client.gui;

import java.util.Random;

import com.charles445.simpledifficulty.api.SDCapabilities;
import com.charles445.simpledifficulty.api.SDCompatibility;
import com.charles445.simpledifficulty.api.SDPotions;
import com.charles445.simpledifficulty.api.config.ClientConfig;
import com.charles445.simpledifficulty.api.config.ClientOptions;
import com.charles445.simpledifficulty.api.config.QuickConfig;
import com.charles445.simpledifficulty.api.thirst.IThirstCapability;
import com.charles445.simpledifficulty.config.ModConfig;
import com.charles445.simpledifficulty.util.RenderUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class ThirstGui 
{
	private final Minecraft minecraftInstance = Minecraft.getMinecraft();
	private int updateCounter = 0;
	private final Random rand = new Random();
	public static final ResourceLocation ICONS = new ResourceLocation("simpledifficulty:textures/gui/icons.png");
	public static final ResourceLocation THIRSTHUD = new ResourceLocation("simpledifficulty:textures/gui/thirsthud.png");
	
	//Position on the icons sheet
	private static final int texturepos_X = 0;
	private static final int texturepos_Y = 0;
	//Dimensions of the icon
	private static final int textureWidth = 9;
	private static final int textureHeight = 9;
	
	@SubscribeEvent
	public void onPreRenderGameOverlay(RenderGameOverlayEvent.Pre event)
	{
	}
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
	}
	
	//Similar behavior to net.minecraftforge.client.GuiIngameForge.renderFood
	private void renderThirst(int width, int height, int thirst, float thirstSaturation)
	{
	}
	
	private void bind(ResourceLocation resource)
	{
	}
}
