package com.charles445.simpledifficulty.client.gui;

import java.util.Map;
import java.util.TreeMap;

import com.charles445.simpledifficulty.api.config.ClientConfig;
import com.charles445.simpledifficulty.api.config.ClientOptions;
import com.charles445.simpledifficulty.api.config.QuickConfig;
import com.charles445.simpledifficulty.api.temperature.ITemperatureDynamicModifier;
import com.charles445.simpledifficulty.api.temperature.ITemperatureModifier;
import com.charles445.simpledifficulty.api.temperature.TemperatureEnum;
import com.charles445.simpledifficulty.api.temperature.TemperatureRegistry;
import com.charles445.simpledifficulty.api.temperature.TemperatureUtil;
import com.charles445.simpledifficulty.config.ModConfig;
import com.charles445.simpledifficulty.util.WorldUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TemperatureInfoGui
{
	private final Minecraft mc = Minecraft.getMinecraft();
	
	private int updateCounter = 0;

	public int xPadding = 2;
	public int yPadding = 2;

	//public int transparency = 0xBB000000;
	public int transparency = 0xDD000000;
	public int defaultColor = 0xFFFFFF | transparency;
	public int coldColor = 0x7777FF | transparency;
	public int hotColor = 0xFF7777 | transparency;
	
	public Map<String, Float> resultMap = new TreeMap<String, Float>();
	public int resultCumulative = 0;
	
	@SubscribeEvent
	public void onPostRenderGameOverlay(RenderGameOverlayEvent.Post event)
	{	
	}
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{	
	}
	
	public void updateTemperature()
	{	
	}
	
	public void displayTemperature(int width, int height)
	{
	}
}
