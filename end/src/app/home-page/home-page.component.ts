import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Image} from "../image/models/image.model";
import {BackEndService} from "../image/services/back-end.service";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
})
export class HomePageComponent {

  image: Image | undefined;
  urlForm: FormGroup;
  load = false;
  err = false;
  status = 200;

  constructor(private backEndService: BackEndService, private formBuilder: FormBuilder, private router: Router) {
    this.urlForm = formBuilder.group({
      url: ['', [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')]]
    })
  }

  get validateUrl() {
    return this.urlForm.controls;
  }

  onSubmit() {
    this.load = true;
    console.log(this.urlForm.value);
    this.addTags();
  }

  ngOnInit(): void {
  }

  public deleteErr() {
    this.err = false;
  }

  public addTags(): void {
    this.backEndService.addImage(this.urlForm.value.url.trim()).subscribe(
      (response: Image) => {
        this.err = false;
        this.image = response,
          this.router.navigate(['/gallery/' + this.image?.id]);
      },
      (error: HttpErrorResponse) => {
        this.load = false;
        this.err = true;
        this.status = error.status;
      }
    )


  }
}
