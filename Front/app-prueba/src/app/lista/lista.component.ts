import { Component, OnInit, ViewChild } from '@angular/core';
import { ClienteDto } from '../models/ClienteDto';
import { DataApiService } from '../services/data-api.service';

import {MatTableDataSource,MatPaginator} from '@angular/material';
import { element } from 'protractor';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  clientes:ClienteDto[];
  cliente:any;
  constructor( private dataService: DataApiService) { }
  displayedColumns = [];
  dataSource = new MatTableDataSource(this.clientes);
  ngOnInit() {
    this.llenarLista();
    this.cliente.paginator = this.paginator;
  }

  

  llenarLista():void{
    this.dataService.getListClientes()
    .subscribe(
      (resp) => {
        let listado = resp ? resp : [];
        this.displayedColumns = [
          'id',
          'nombre'
        ];
        this.cliente = new MatTableDataSource(listado);
      }

    )
     
  }
}
