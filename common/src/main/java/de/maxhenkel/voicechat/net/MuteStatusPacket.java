package de.maxhenkel.voicechat.net;

import de.maxhenkel.voicechat.Voicechat;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

/**
 * Пакет для получения статуса мута своего игрока с сервера.
 *
 * Канал: "svcmute:mute_status"
 *
 * Формат пакета:
 * - 1 байт: 0x00 = не в муте, 0x01 = в муте
 */
public class MuteStatusPacket implements Packet<MuteStatusPacket> {

    public static final ResourceLocation MUTE_STATUS = new ResourceLocation("svcmute", "mute_status");

    private boolean muted;

    public MuteStatusPacket() {
    }

    public MuteStatusPacket(boolean muted) {
        this.muted = muted;
    }

    public boolean isMuted() {
        return muted;
    }

    @Override
    public ResourceLocation getIdentifier() {
        return MUTE_STATUS;
    }

    @Override
    public MuteStatusPacket fromBytes(FriendlyByteBuf buf) {
        muted = buf.readByte() == 0x01;
        Voicechat.LOGGER.info("Received MuteStatusPacket: muted={}", muted);
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeByte(muted ? 0x01 : 0x00);
    }

}
