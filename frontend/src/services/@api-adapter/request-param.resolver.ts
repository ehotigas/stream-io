export interface IRequestParamResolver {
    resolve<T extends object>(filters: T): string;
}

export class RequestParamResolver {
    /**
     * @param {T extends object} filters 
     * @returns {string}
     */
    public resolve<T extends object>(filters: T): string {
        let path = `?`;
        const keyList = Object.keys(filters);
        for (let filterIdx = 0; filterIdx < keyList.length; filterIdx++) {
            const key = keyList[filterIdx];
            path += `${key}=${filters[key as keyof T]}`;
            if (filterIdx != keyList.length - 1) {
                path += "&";
            }
        }
        return path;
    }
}