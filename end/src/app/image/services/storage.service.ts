import {Injectable} from '@angular/core';
import {
  BehaviorSubject,
  interval,
  Observable,
  Subject,
  Subscription,
} from 'rxjs';
import {concatMap, tap} from 'rxjs/operators';
import {Image} from '../models/image.model';
import {BackEndService} from './back-end.service';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  private _items$: BehaviorSubject<Image[]> = new BehaviorSubject<Image[]>([]);

  constructor(private backend: BackEndService) {
    const schedule = interval(2000);
    schedule.subscribe(
      () => this.getTasksHelper().subscribe() // This should be destroy at some point but for the example its fine.
    );
  }

  get items(): BehaviorSubject<Image[]> {
    return this._items$;
  }

  public addItem(item: Image) {
    this.backend
      .addImage(item)
      .pipe(concatMap(() => this.getTasksHelper())) // Executes getImages after adding the item
      .subscribe();
  }

  private getTasksHelper() {
    return this.backend.getImages().pipe(
      // When the request succeeds, update the items observable
      tap((images) => {
        this._items$.next(images);
      })
    );
  }
}

