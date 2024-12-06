/*
 * Singleton Services class
 *
 * Centralized service layer through which all REST API calls will be sent.
 * Eliminates duplicate request
 *
 * Usage example:
 *
 *    import services from "Services";
 *
 *    try {
 *      const result = await services.request("GET", `https://api.github.com/users/test`, {});
 *      console.log(result);
 *      const response = await result.json();
 *     console.log(response);
 *   } catch (ex) {
 *     console.error(ex);
 *  }
 *
 */

class Services {
  private promiseCache: { [key: string]: Promise<any> };
  private interval: number;

  constructor() {
    this.promiseCache = {};
    // Time interval for caching one API call
    this.interval = 2000;
  }

  // private stringCompression(str: string): string {
  //   if (str.length === 0) {
  //     console.error('Please enter a valid string.');
  //     return '';
  //   }
  //   let output = '';
  //   let count = 0;
  //   for (let i = 0; i < str.length; i++) {
  //     count++;
  //     if (str[i] !== str[i + 1]) {
  //       output += str[i] + count;
  //       count = 0;
  //     }
  //   }
  //   return output;
  // }

  private static getHeaders(): { [key: string]: string } {
    const token = '';
    const headers: { [key: string]: string } = {
      'Content-Type': 'application/json',
    };
  
    if (token) {
      headers.Authorization = `Bearer ${token}`;
    }
    return headers;
  }

  public async post(url: string, payload = true): Promise<any> {
    return this.request('POST', url, payload, Services.getHeaders());
  }

  public async get(url: string): Promise<any> {
    return this.request('GET', url, null, Services.getHeaders());
  }

  public async put(url: string, payload = true): Promise<any> {
    return this.request('PUT', url, payload, Services.getHeaders());
  }

  public async delete(url: string): Promise<any> {
    return this.request('DELETE', url, null, Services.getHeaders());
  }

  // eslint-disable-next-line class-methods-use-this
  private async request(
    type: string,
    url: string,
    props: any = null,
    headers: { [key: string]: string } | null = null,
  ): Promise<any> {
    const requestPayload = JSON.stringify(props);
    // const key = this.stringCompression(
    //   `${type}${url}${props ? requestPayload : ""}`
    // );
    const requestBody = props
      ? { method: type, body: requestPayload, headers }
      : { method: type, headers };
    try {
      try {
        const response = await fetch(url, requestBody);
        try {
          const res = await response.json();
      
          if (response.ok) {
            return res;
          }
          throw res;
        } catch (error) {
          console.log('json parese error:', error.message);
          return error;
        }
      } catch (e) {
        console.error(e);
        throw new Error(e);
      }
    } catch (e) {
      console.error(e);
      throw new Error(e);
    }
  }
  // private handleAccessDenied(): void {
  //   console.info('Access denied');
  // }

  // private handleUnauthorised(): void {
  //   console.info('Unauthorized');
  // }
}

// create instance and freeze to make it singleton
const services = new Services();
Object.freeze(services);

export default services;
