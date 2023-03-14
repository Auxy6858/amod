package net.auxy.cwood.block;

import net.minecraft.block.DoorBlock;
import net.minecraft.sound.SoundEvent;

public class ModDoorBlock extends DoorBlock {
    public ModDoorBlock(Settings settings, SoundEvent closeSound, SoundEvent openSound) {
        super(settings, closeSound, openSound);
    }
}
