import { Channel } from "../channel.entity"

export interface Subscriber {
    id: number;
    subscriber: Channel;
    targetChannel: Channel;
    subscribedAt: Date;
}