package me.mrsoulpenguin.smg.mixin.entity.player;

import me.mrsoulpenguin.smg.RemembersOwner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileEntity;deflect(Lnet/minecraft/entity/ProjectileDeflection;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Z)Z"))
    private void saveOriginalOwner(Entity target, CallbackInfo ci) {
        if (target instanceof FireballEntity fireball) {
            if (fireball instanceof RemembersOwner remembers) {
                remembers.specificMobGriefing$rememberOwner(fireball.getOwner());
            }
        }
    }

}
