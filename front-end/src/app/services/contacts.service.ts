import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactsService {

  private getContactsUrl = "http://localhost:9000/contacts"
  private requestUrl: string = ""

  constructor(private http: HttpClient) { }

  getClients(pageIndex:number,pageSize:number): Observable<any>{
    this.requestUrl= this.getContactsUrl + "?page=" + pageIndex
     + "&size=" + pageSize;
    return this.http.get<any>(this.requestUrl);
  }
}
