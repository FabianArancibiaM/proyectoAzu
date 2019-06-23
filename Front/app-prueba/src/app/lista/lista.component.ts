import { Component, OnInit } from '@angular/core';
import { ClienteDto } from '../models/ClienteDto';
import { DataApiService } from '../services/data-api.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {
  clientes:ClienteDto[];
  
  constructor( private dataService: DataApiService) { }

  ngOnInit() {
    return this.dataService.getListClientes()
      .subscribe(data => this.clientes=data);
  }

}
