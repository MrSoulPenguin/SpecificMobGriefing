package me.mrsoulpenguin.smg.mixin.server.world;

import me.mrsoulpenguin.smg.Smg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @ModifyArgs(method = "createExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/explosion/ExplosionImpl;<init>(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;Lnet/minecraft/util/math/Vec3d;FZLnet/minecraft/world/explosion/Explosion$DestructionType;)V"))
    private void allowCreeperExplosions(Args args) {
        ServerWorld world = args.get(0);
        Entity entity = args.get(1);

        if (entity instanceof CreeperEntity && !world.getGameRules().getBoolean(Smg.ALLOW_CREEPER_EXPLOSION_DESTRUCTION)) {
            args.set(7, Explosion.DestructionType.KEEP);
        }
    }
}
