import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {ImageContainerComponent} from "./components/image-container/image-container.component";

@NgModule({
  declarations: [/*ImageComponent,*/ ImageContainerComponent],
  imports: [CommonModule, HttpClientModule, ReactiveFormsModule],
  exports: [ImageContainerComponent],
})
export class ImageModule {}
