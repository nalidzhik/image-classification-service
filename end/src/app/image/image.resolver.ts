import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { delay, tap } from 'rxjs/operators';
import { Image } from './models/image.model';
import { BackEndService } from './services/back-end.service';
import { LoadingService } from './services/loading.service';

@Injectable({ providedIn: 'root' })
export class ImageResolver implements Resolve<Image[]> {
  constructor(
    private backEndService: BackEndService,
    private loadingService: LoadingService
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Image[]> {
    this.loadingService.start();
    return this.backEndService.getImages().pipe(
      delay(1000),
      tap(() => this.loadingService.stop())
    );
  }
}
