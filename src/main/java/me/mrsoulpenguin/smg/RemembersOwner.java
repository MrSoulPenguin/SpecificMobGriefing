package me.mrsoulpenguin.smg;

import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;


public interface RemembersOwner {

    void specificMobGriefing$rememberOwner(Entity entity);

    @Nullable
    Entity specificMobGriefing$recallOwner();

}
