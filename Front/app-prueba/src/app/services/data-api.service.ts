import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import { ClienteDto } from '../models/ClienteDto';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn:'root'
})
export class DataApiService {
  private apiUrl = 'http://192.168.0.18:8082/api/cliente';

  constructor(private _http:HttpClient) { }

  getListClientes(): Observable<ClienteDto[]> {
    return this._http.get<ClienteDto[]>(this.apiUrl +'/listar')
  }
}
