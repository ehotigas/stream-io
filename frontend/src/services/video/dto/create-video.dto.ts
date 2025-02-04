export interface CreateVideoDto {
    title: string;
    content: string;
    channelId: number;
    description: string;
    isPublic: boolean;
}