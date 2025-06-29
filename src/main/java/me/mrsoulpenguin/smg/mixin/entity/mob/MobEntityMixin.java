package me.mrsoulpenguin.smg.mixin.entity.mob;

import me.mrsoulpenguin.smg.Smg;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {

    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean allowVillagerItemPickup(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule) {
        if ((Object) this instanceof VillagerEntity) {
            return instance.getBoolean(Smg.ALLOW_VILLAGER_ITEM_PICKUP);
        }

        return instance.getBoolean(rule);
    }

}
