import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactsService {

  private getContactsUrl = "http://localhost:9000/contacts"

  constructor(private http: HttpClient) { }

  getClients(): Observable<any>{
    return this.http.get<any>(this.getContactsUrl);
  }
}
