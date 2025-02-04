import { IRequestParamResolver, RequestParamResolver } from "@/services/@api-adapter/request-param.resolver";
import { CreateSubscriberDto } from "./dto/create-subscriber.dto";
import { IApiAdapter } from "@/services/@api-adapter/api-adapter";
import { GetSubscriberDto } from "./dto/get-subscriber.dto";
import { Subscriber } from "./subscriber.entity";


export class SubscriberAdapter {
    private readonly adapter: IApiAdapter;
    private readonly requestParamResolver: IRequestParamResolver;
    public constructor(adapter: IApiAdapter) {
        this.adapter = adapter;
        this.requestParamResolver = new RequestParamResolver();
    }

    public async findAll(filters: Partial<Subscriber> = {}): Promise<GetSubscriberDto> {
        const request = await this.adapter.fetch(`/channel-subscriber${this.requestParamResolver.resolve(filters)}`);
        return request.data as GetSubscriberDto;
    }

    public async findById(id: number): Promise<Subscriber> {
        const request = await this.adapter.fetch(`/channel-subscriber/${id}`);
        return request.data as Subscriber;
    }

    public async save(dto: CreateSubscriberDto): Promise<Subscriber> {
        const request = await this.adapter.fetch(`/channel-subscriber`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            data: JSON.stringify(dto)
        });
        return request.data as Subscriber;
    }

    public async remove(id: number): Promise<Subscriber> {
        const request = await this.adapter.fetch(`/channel-subscriber/${id}`, {
            method: "DELETE"
        });
        return request.data as Subscriber;
    }
}