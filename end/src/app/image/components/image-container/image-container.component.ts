import { Component, OnDestroy } from '@angular/core';
import { StorageService } from '../../services/storage.service';
import { Image } from '../../models/image.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { LoadingService } from '../../services/loading.service';

@Component({
  selector: 'app-image-container',
  templateUrl: './image-container.component.html',
  styleUrls: ['./image-container.component.scss'],
})
export class ImageContainerComponent implements OnDestroy {
  imageFormGroup: FormGroup;

  images$!: Observable<Image[]>;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private activatedRoute: ActivatedRoute,
    public storageService: StorageService,
    private formBuilder: FormBuilder
  ) {
    this.imageFormGroup = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(4)]],
      details: ['', Validators.required],
    });

    this.activatedRoute.data.subscribe((data) => {
      this.images$ = new BehaviorSubject<Image[]>(data['images']).asObservable();
    });
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  public addImageItem() {
    if (!this.imageFormGroup.valid) {
      alert('The text box cannot be empty!');
      return;
    }

    // const imageToAdd: Image = {
    //   analysed_at: this.imageFormGroup.get('analysed_at')?.value,
    //   url: this.imageFormGroup.get('url')?.value,
    // };

    // this.storageService.addItem(imageToAdd);

    // clean the form
    this.imageFormGroup.setValue({
      url: '',
      analysed_at: '',
    });
    this.imageFormGroup.markAsPristine();
  }

  public onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    const file = target.files?.[0];
    target.value = '';
    if (!file) {
      return;
    }

    const fileReader = new FileReader();
    fileReader.onloadend = () => {
      const parsedFile = fileReader.result;
      const lines = (parsedFile as string).split('\n');
      lines.forEach((line: string) => {
        const parts = line.split('|');

        if (parts.length !== 2) {
          return;
        }

        // const imageToAdd: Image = {
        //   url: parts[0],
        //   analysed_at: parts[1],
        // };
        //
        // this.storageService.addItem(imageToAdd);
      });
    };

    fileReader.readAsText(file);
  }
}
