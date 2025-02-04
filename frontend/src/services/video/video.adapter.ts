import { IRequestParamResolver, RequestParamResolver } from "../@api-adapter/request-param.resolver";
import { IApiAdapter } from "../@api-adapter/api-adapter";
import { Video } from "./video.entity";
import { GetVideoDto } from "./dto/get-video.dto";
import { CreateVideoDto } from "./dto/create-video.dto";
import { UpdateVideoDto } from "./dto/update-video.dto";


export class VideoAdapter {
    private readonly adapter: IApiAdapter;
    private readonly requestParamResolver: IRequestParamResolver;
    public constructor(adapter: IApiAdapter) {
        this.adapter = adapter;
        this.requestParamResolver = new RequestParamResolver();
    }

    public async findAll(filters: Partial<Video> = {}): Promise<GetVideoDto> {
        const request = await this.adapter.fetch(`/video${this.requestParamResolver.resolve(filters)}`);
        return request.data as GetVideoDto;
    }

    public async findById(id: number): Promise<Video> {
        const request = await this.adapter.fetch(`/video/${id}`);
        return request.data as Video;
    }

    public async save(dto: CreateVideoDto): Promise<Video> {
        const request = await this.adapter.fetch(`/video`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            data: JSON.stringify(dto)
        });
        return request.data as Video;
    }

    public async update(dto: UpdateVideoDto): Promise<Video> {
        const request = await this.adapter.fetch(`/video`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            data: JSON.stringify(dto)
        });
        return request.data as Video;
    }

    public async remove(id: number): Promise<Video> {
        const request = await this.adapter.fetch(`/video/${id}`, {
            method: "DELETE"
        });
        return request.data as Video;
    }
}