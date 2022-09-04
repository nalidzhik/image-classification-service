import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Image } from '../models/image.model';
import {Observable} from "rxjs";

@Injectable({ providedIn: 'root' })
export class BackEndService {
  api = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  getImages() {
    return this.httpClient.get<Image[]>(`${this.api}/images`);
  }

  addImage(url: any) : Observable <Image> {
    return this.httpClient.post<Image>(`${this.api}/images`, url);
  }
}
