import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Country } from '../models/country';

@Injectable({
  providedIn: 'root'
})
export class ContactsService {

  private baseUrl = "http://localhost:9000";
  private getContactsUrl = this.baseUrl + "/contacts";
  private getCountriesUrl = this.baseUrl + "/countries";
  private filterContactsByCountriesUrl = this.baseUrl + "/contacts/filter/"
  private requestUrl: string = ""

  constructor(private http: HttpClient) { }

  getContacts(pageIndex:number,pageSize:number): Observable<any>{
    this.requestUrl= this.getContactsUrl + "?page=" + pageIndex
     + "&size=" + pageSize;
    return this.http.get<any>(this.requestUrl);
  }

  getCountries():Observable<Country[]>{
    return this.http.get<Country[]>(this.getCountriesUrl);
  }

  filterContactsByCountry(countryId: number):Observable<any>{
    return this.http.get<any>(this.filterContactsByCountriesUrl+countryId);
  }
}
