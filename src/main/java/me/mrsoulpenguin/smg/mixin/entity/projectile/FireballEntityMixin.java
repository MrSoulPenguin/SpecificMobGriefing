package me.mrsoulpenguin.smg.mixin.entity.projectile;

import me.mrsoulpenguin.smg.RemembersOwner;
import me.mrsoulpenguin.smg.Smg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FireballEntity.class)
public abstract class FireballEntityMixin implements RemembersOwner {

    @Unique
    private Entity originalOwner;

    @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)V"), index = 6)
    private World.ExplosionSourceType allowGhastExplosions(World.ExplosionSourceType explosionSourceType) {
        FireballEntity fireball = (FireballEntity) (Object) this;
        World world = fireball.getWorld();

        if (world instanceof ServerWorld serverWorld) {
            if (fireball instanceof RemembersOwner remembers) {
                Entity owner = remembers.specificMobGriefing$recallOwner();
                if (owner instanceof GhastEntity && !serverWorld.getGameRules().getBoolean(Smg.ALLOW_GHAST_EXPLOSION_DESTRUCTION)) {
                    // Setting the explosion source to ExplosionSourceType.NONE means that when the explosion is created in
                    // the ServerWorld, it's destruction type will be set to DestructionType.KEEP, not breaking any blocks.
                    return World.ExplosionSourceType.NONE;
                }
            }
        }

        return explosionSourceType;
    }

    @Override
    public void specificMobGriefing$rememberOwner(Entity entity) {
        this.originalOwner = entity;
    }

    @Override
    public Entity specificMobGriefing$recallOwner() {
        return this.originalOwner != null ? this.originalOwner : ((FireballEntity) (Object) this).getOwner();
    }
}
