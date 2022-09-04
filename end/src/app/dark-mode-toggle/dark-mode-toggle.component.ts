import { Component } from "@angular/core";
import { DarkModeService } from "angular-dark-mode";

@Component({
  selector: "app-dark-mode-toggle",
  templateUrl: "./dark-mode-toggle.component.html",
  styleUrls: ["./dark-mode-toggle.component.css"]
})
export class DarkModeToggleComponent {
  darkMode$ = this.darkModeService.darkMode$;

  constructor(private darkModeService: DarkModeService) {}

  onToggle(): void {
    this.darkModeService.toggle();
  }
}
