package me.mrsoulpenguin.smg.mixin.entity.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileEntity;deflect(Lnet/minecraft/entity/ProjectileDeflection;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Z)Z"))
    private boolean maintainGhastOwnership(ProjectileEntity projectile, ProjectileDeflection deflection, Entity deflector, Entity owner, boolean fromAttack) {
        if (projectile instanceof FireballEntity fireball) {
            Entity fireballOwner = fireball.getOwner();

            // Gotta reassign ownership back to the Ghast so that FireballEntityMixin properly cancels an explosion on
            // deflection if it's a Ghast fireball being deflected.
            if (fireballOwner instanceof GhastEntity) {
                return projectile.deflect(deflection, deflector, fireballOwner, fromAttack);
            }
        }

        return projectile.deflect(deflection, deflector, owner, fromAttack);
    }

}
