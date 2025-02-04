import { Channel } from "../channel/channel.entity";

export interface Video {
    id: number;
    title: string;
    content: string;
    channel: Channel;
    description: string;
    likeAmount: number;
    dislikeAmount: number;
    viewAmount: number;
    commentAmount: number;
    uploadedAt: Date;
    isPublic: boolean;
    isDeleted: boolean;
    deletedAt: Date;
}