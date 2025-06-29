package me.mrsoulpenguin.smg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Smg implements ModInitializer {

    public static final String MOD_ID = "smg";
    private static Logger logger;

    public static final GameRules.Key<GameRules.BooleanRule> ALLOW_VILLAGER_ITEM_PICKUP;
    public static final GameRules.Key<GameRules.BooleanRule> ALLOW_PIGLIN_BARTERING;
    public static final GameRules.Key<GameRules.BooleanRule> ALLOW_ENDERMAN_BLOCK_INTERACTION;
    public static final GameRules.Key<GameRules.BooleanRule> ALLOW_CREEPER_EXPLOSION_DESTRUCTION;
    public static final GameRules.Key<GameRules.BooleanRule> ALLOW_GHAST_EXPLOSION_DESTRUCTION;

    static {
        ALLOW_VILLAGER_ITEM_PICKUP = GameRuleRegistry.register("allowVillagerItemPickup", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
        ALLOW_PIGLIN_BARTERING = GameRuleRegistry.register("allowPiglinBartering", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
        ALLOW_ENDERMAN_BLOCK_INTERACTION = GameRuleRegistry.register("allowEndermanBlockInteraction", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
        ALLOW_CREEPER_EXPLOSION_DESTRUCTION = GameRuleRegistry.register("allowCreeperExplosionDestruction",  GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
        ALLOW_GHAST_EXPLOSION_DESTRUCTION = GameRuleRegistry.register("allowGhastExplosionDestruction", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
    }

    public static Logger getLog() {
        return logger;
    }

    @Override
    public void onInitialize() {
        logger = LoggerFactory.getLogger(MOD_ID);
        logger.info("Initializing Specific Mob Griefing. Noot.");
    }
}
