import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs';
import { Contact } from 'src/app/models/contact';
import { ContactsService } from 'src/app/services/contacts.service';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {

  pagedResult: any;
  contacts: Contact[] = [];
  pageSize: number = 10;
  totalElements: number = 0;
  totalPages: number = 0;

  displayColumns: string[] = ["Name","Phone"];

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private contactsService: ContactsService) { }

  ngOnInit(): void {
    this.loadContacts();
  }

  loadContacts(){
    this.contactsService.getClients(
      this.paginator?.pageIndex ?? 0,
      this.paginator?.pageSize ?? 10)
      .subscribe((result)=> {
        this.pagedResult = result;
        this.contacts = this.pagedResult.content;
        this.pageSize = this.pagedResult.pageable.pageSize;
        this.totalElements = this.pagedResult.totalElements;
        this.totalPages = this.pagedResult.totalPages;
      });
  }

  ngAfterViewInit(){
    this.paginator.page
      .pipe(
        tap(() => this.loadContacts())
      )
      .subscribe()
  }

}
