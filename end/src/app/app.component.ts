import { Component } from '@angular/core';
import { LoadingService } from './image/services/loading.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'images';

  constructor(public loadingService: LoadingService) {};
}
