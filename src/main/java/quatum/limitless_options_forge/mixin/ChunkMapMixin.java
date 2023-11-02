package quatum.limitless_options_forge.mixin;

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import net.minecraft.core.SectionPos;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.DistanceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import org.apache.commons.lang3.mutable.MutableObject;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.function.Consumer;

@Mixin(ChunkMap.class)
public class ChunkMapMixin{

    @Shadow
    int viewDistance;
    int anInt = 0;
    int anInt_ = 0;

    @Redirect(method = "setViewDistance", at = @At(value = "INVOKE", target = "net/minecraft/util/Mth.clamp(III)I"))
    private int redirectClamp(int p_140168_, int min, int max) {
        // Hier leitest du den Aufruf von Mth.clamp um und gibst einfach 'value' zurück
        return Mth.clamp(p_140168_, 1, Byte.MAX_VALUE -2);
    }
    @ModifyArg(method = "setViewDistance",at = @At(value = "INVOKE",target = "Lnet/minecraft/server/level/ChunkMap$DistanceManager;updatePlayerTickets(I)V"),index = 0)
    private int redirectdistanceManager(int par1) {
        // Hier leitest du den Aufruf von Mth.clamp um und gibst einfach 'value' zurück

        return Mth.clamp(par1, 1, Byte.MAX_VALUE -2);
    }
}
