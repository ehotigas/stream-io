export interface Channel {
    id: number;
    name: string;
    username: string;
    description: string;
    password: string;
    subscribersAmount: number;
    viewsAmount: number;
    videosAmount: number;
    createdAt: Date;
    isActive: boolean;
}