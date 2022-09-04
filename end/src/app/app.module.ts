import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from "@angular/router";
import { AppComponent } from './app.component';
import {ClarityModule, ClrIconModule} from "@clr/angular";
import {HttpClientModule} from "@angular/common/http";
import {ImageContainerComponent} from "./image/components/image-container/image-container.component";
import {ImageResolver} from "./image/image.resolver";
import {PageNotFoundModule} from "./page-not-found/page-not-found.module";
import {CommonModule} from "@angular/common";
import {ImageModule} from "./image/image.module";
import {DarkModeToggleComponent} from "./dark-mode-toggle/dark-mode-toggle.component";
import {HomePageComponent} from "./home-page/home-page.component";

@NgModule({
  declarations: [AppComponent, DarkModeToggleComponent],
  imports: [
    RouterModule.forRoot([
      { path: '', component: HomePageComponent },
      { path: 'images',
        component: ImageContainerComponent,
        resolve: {
          images: ImageResolver,
        },
      }
    ]),
    BrowserModule,
    ClrIconModule,
    HttpClientModule,
    PageNotFoundModule,
    ClarityModule,
    CommonModule,
    ImageModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
