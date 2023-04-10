import { AfterViewInit, ChangeDetectorRef, Component } from '@angular/core';
import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements AfterViewInit {

  constructor(private headerService: HeaderService, private cdr: ChangeDetectorRef) { }

  ngAfterViewInit(): void {
    // Força a atualização do componente após a inicialização
    this.cdr.detectChanges();
  }

  get title(): string {
    return this.headerService.headerData.title;
  }

  get icon(): string {
    return this.headerService.headerData.icon;
  }

  get routeUrl(): string {
    return this.headerService.headerData.routeUrl;
  }
}
