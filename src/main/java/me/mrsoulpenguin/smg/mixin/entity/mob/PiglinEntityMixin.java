package me.mrsoulpenguin.smg.mixin.entity.mob;

import me.mrsoulpenguin.smg.Smg;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PiglinEntity.class)
public class PiglinEntityMixin {

    @Redirect(method = "canGather", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean allowPiglinBartering(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule) {
        return instance.getBoolean(Smg.ALLOW_PIGLIN_BARTERING);
    }

}
