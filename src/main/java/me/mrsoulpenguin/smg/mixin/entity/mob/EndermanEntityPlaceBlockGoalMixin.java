package me.mrsoulpenguin.smg.mixin.entity.mob;

import me.mrsoulpenguin.smg.Smg;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PlaceBlockGoal")
public class EndermanEntityPlaceBlockGoalMixin {

    @Redirect(method = "canStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean allowBlockInteraction(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule) {
        return instance.getBoolean(Smg.ALLOW_ENDERMAN_BLOCK_INTERACTION);
    }

}
