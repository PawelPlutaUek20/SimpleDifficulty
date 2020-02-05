package com.charles445.simpledifficulty.item;

import java.util.List;

import javax.annotation.Nullable;

import com.charles445.simpledifficulty.SimpleDifficulty;
import com.charles445.simpledifficulty.api.SDCapabilities;
import com.charles445.simpledifficulty.api.config.QuickConfig;
import com.charles445.simpledifficulty.api.thirst.IThirstCapability;
import com.charles445.simpledifficulty.api.thirst.ThirstEnum;
import com.charles445.simpledifficulty.api.thirst.ThirstEnumBlockPos;
import com.charles445.simpledifficulty.api.thirst.ThirstUtil;
import com.charles445.simpledifficulty.capability.ThirstCapability;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCanteen extends ItemDrinkBase
{
	//TODO This code is terrible and can't be interfaced with
	//TODO cauldron implementation? lol
	
	public static final String CANTEENTYPE = "CanteenType";
	
	public ItemCanteen()
	{
		//Doesn't super the constructor
		setMaxDamage(3);
		setNoRepair();
		setMaxStackSize(1);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (this.isInCreativeTab(tab))
		{
			ItemStack emptyCanteen = new ItemStack(this, 1, 0);
			createTag(emptyCanteen);
			setCanteenEmpty(emptyCanteen);
			
			ItemStack fullCanteen = emptyCanteen.copy();
			setCanteenFull(fullCanteen);
			
			ItemStack purifiedCanteen = fullCanteen.copy();
			setTypeTag(purifiedCanteen, ThirstEnum.PURIFIED.ordinal());
			
			items.add(emptyCanteen);
			items.add(fullCanteen);
			items.add(purifiedCanteen);
			
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if(isCanteenEmpty(stack))
			return "item."+SimpleDifficulty.MODID+":"+"canteen_empty";
		
		int type = getTypeTag(stack).getInt();
		if(type>=ThirstEnum.values().length)
			return "item."+SimpleDifficulty.MODID+":"+"canteen_broken";
		
		return "item."+SimpleDifficulty.MODID+":"+"canteen_"+ThirstEnum.values()[type].toString();
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		//Initializes if it hasn't been initialized already
		int typetag = getTypeTag(stack).getInt();
		
		//Only attempt refill if item isn't full or if it isn't normal water
		//This prevents full purified canteens from getting overridden and removing purified water from the ground mistakenly
		if(!isCanteenFull(stack) || typetag==ThirstEnum.NORMAL.ordinal())
		{
			ThirstEnumBlockPos traceBlockPos = ThirstUtil.traceWater(player);
			if(traceBlockPos != null)
			{
				ThirstEnum trace = traceBlockPos.thirstEnum;
				//Clear out any purified block that got found
				if(trace==ThirstEnum.PURIFIED)
					player.world.setBlockToAir(traceBlockPos.pos);
				
				//Convert Rain to Normal
				if(trace==ThirstEnum.RAIN)
					trace = ThirstEnum.NORMAL;
				
				if(trace.ordinal()==typetag)
				{
					addDose(stack);
				}
				else
				{
					setTypeTag(stack,trace.ordinal());
					setDoses(stack,1);
				}
				player.setActiveHand(hand);
				player.swingArm(hand);
				player.playSound(SoundEvents.ITEM_BUCKET_FILL, 0.5f, 1.0f);
				player.stopActiveHand();
				return new ActionResult(EnumActionResult.SUCCESS, stack);
			}
		}
		if(!isCanteenEmpty(stack))
		{
			IThirstCapability capability = SDCapabilities.getThirstData(player);
			if(capability.isThirsty() || !QuickConfig.isThirstEnabled())
			{
				player.setActiveHand(hand);
				//DebugUtil.messageAll("itemdamage is not maxdamage ActionResult SUCCESS");
				return new ActionResult(EnumActionResult.SUCCESS, stack);
			}
		}
		//DebugUtil.messageAll("ActionResult FAIL");
		return new ActionResult(EnumActionResult.FAIL, stack);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving)
	{
		if(world.isRemote || !(entityLiving instanceof EntityPlayer))
			return stack;
		
		if(isCanteenEmpty(stack))
			return stack;
		
		EntityPlayer player = (EntityPlayer)entityLiving;
		ThirstUtil.takeDrink(player, this.getThirstLevel(stack), this.getSaturationLevel(stack), this.getDirtyChance(stack));
		removeDose(stack);
		return stack;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
	{
		//Add durability information
		
		boolean drawDurability = true;
		
		if(flag.isAdvanced())
		{
			
			if(!isCanteenFull(stack))
			{
				//Advanced tooltips, durability is already shown if the item has damage
				drawDurability = false;
			}
		}
		
		if(drawDurability)
		{
			tooltip.add(I18n.format("item.durability", stack.getMaxDamage()-stack.getItemDamage(), stack.getMaxDamage()));
		}
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return !isCanteenEmpty(stack);
	}

	@Override
	public int getThirstLevel(ItemStack stack)
	{
		int type = getTypeTag(stack).getInt();
		if(type>=ThirstEnum.values().length)
			return 0;
		
		return ThirstEnum.values()[type].getThirst();
	}

	@Override
	public float getSaturationLevel(ItemStack stack)
	{
		int type = getTypeTag(stack).getInt();
		if(type>=ThirstEnum.values().length)
			return 0.0f;
		
		return ThirstEnum.values()[type].getSaturation();
	}

	@Override
	public float getDirtyChance(ItemStack stack)
	{
		int type = getTypeTag(stack).getInt();
		if(type>=ThirstEnum.values().length)
			return 0.0f;
		
		return ThirstEnum.values()[type].getThirstyChance();
	}
	
	private void createTag(ItemStack stack)
	{
		setTypeTag(stack,ThirstEnum.NORMAL.ordinal());
	}
	
	private void setTypeTag(ItemStack stack, int tag)
	{
		stack.setTagInfo(CANTEENTYPE, new NBTTagInt(tag));
	}
	
	private boolean isCanteenFull(ItemStack stack)
	{
		return stack.getItemDamage()==0;
	}
	
	private boolean isCanteenEmpty(ItemStack stack)
	{
		return stack.getItemDamage()==stack.getMaxDamage();
	}
	
	private void setCanteenFull(ItemStack stack)
	{
		stack.setItemDamage(0);
	}
	
	private void setCanteenEmpty(ItemStack stack)
	{
		stack.setItemDamage(stack.getMaxDamage());
	}
	
	private void removeDose(ItemStack stack)
	{
		if(!isCanteenEmpty(stack))
		{
			stack.setItemDamage(stack.getItemDamage()+1);
		}
	}
	
	private void setDoses(ItemStack stack, int amount)
	{
		if(amount<=0)
		{
			setCanteenEmpty(stack);
		}
		else
		{
			//setItemDamage takes care of negative results
			stack.setItemDamage(stack.getMaxDamage()-amount);
		}
	}
	
	private void addDose(ItemStack stack)
	{
		//setItemDamage takes care of negative results
		stack.setItemDamage(stack.getItemDamage()-1);
	}
	
	private NBTTagInt getTypeTag(ItemStack stack)
	{
		if(stack.getTagCompound()==null)
		{
			createTag(stack);
			setCanteenEmpty(stack);
		}
		
		NBTBase tag = stack.getTagCompound().getTag(CANTEENTYPE);
		if(tag instanceof NBTTagInt)
		{
			return (NBTTagInt)tag;
		}
		else
		{
			stack.getTagCompound().removeTag(CANTEENTYPE);
			createTag(stack);
			return new NBTTagInt(ThirstEnum.NORMAL.ordinal());
		}
	}
}