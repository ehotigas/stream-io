import axios, { AxiosRequestConfig, AxiosResponse } from "axios";

export interface IApiAdapter {
    /**
     * @async
     * @param {string} uri 
     * @returns {Promise<AxiosResponse<T, D>>}
     */
    fetch<T, D>(uri: string): Promise<AxiosResponse<T, D>>;
    
    /**
     * @async
     * @param {string} uri 
     * @param {AxiosRequestConfig} config 
     * @returns {Promise<AxiosResponse<T, D>>}
     */
    fetch<T, D>(uri: string, config: AxiosRequestConfig): Promise<AxiosResponse<T, D>>;
}


export class ApiAdapter implements IApiAdapter {
    private url: string;
    public constructor() {
        this.url = "http://localhost:4043";
    }

    public async fetch<T, D>(uri: string, config: AxiosRequestConfig = {}): Promise<AxiosResponse<T, D>> {
        return await axios({ ...config, url: `${this.url}${uri}` });
    }
}