import { IRequestParamResolver, RequestParamResolver } from "../@api-adapter/request-param.resolver";
import { IApiAdapter } from "../@api-adapter/api-adapter";
import { GetChannelDto } from "./dto/get-channel.dto";
import { Channel } from "./channel.entity";
import { CreateChannelDto } from "./dto/create-channel.dto";


export class ChannelAdapter {
    private readonly adapter: IApiAdapter;
    private readonly requestParamResolver: IRequestParamResolver;
    public constructor(adapter: IApiAdapter) {
        this.adapter = adapter;
        this.requestParamResolver = new RequestParamResolver();
    }

    public async findAll(filters: Partial<Channel> = {}): Promise<GetChannelDto> {
        const request = await this.adapter.fetch(`/channel${this.requestParamResolver.resolve(filters)}`);
        return request.data as GetChannelDto;
    }

    public async findById(id: number): Promise<Channel> {
        const request = await this.adapter.fetch(`/channel/${id}`);
        return request.data as Channel;
    }

    public async findByUsername(username: string): Promise<Channel> {
        const request = await this.adapter.fetch(`/channel/username/${username}`);
        return request.data as Channel;
    }

    public async save(dto: CreateChannelDto): Promise<Channel> {
        const request = await this.adapter.fetch(`/channel`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            data: JSON.stringify(dto)
        });
        return request.data as Channel;
    }

    public async update(dto: Partial<CreateChannelDto>): Promise<Channel> {
        const request = await this.adapter.fetch(`/channel`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            data: JSON.stringify(dto)
        });
        return request.data as Channel;
    }

    public async remove(id: number): Promise<Channel> {
        const request = await this.adapter.fetch(`/channel/${id}`, {
            method: "DELETE"
        });
        return request.data as Channel;
    }
}