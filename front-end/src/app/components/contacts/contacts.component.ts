import { Component, OnInit } from '@angular/core';
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
  pageSize: number = 0;
  totalElements: number = 0;
  totalPages: number = 0;

  displayColumns: string[] = ["Name","Phone"];

  constructor(private contactsService: ContactsService) { }

  ngOnInit(): void {
    this.contactsService.getClients()
      .subscribe((result)=> {
        this.pagedResult = result;
        this.contacts = this.pagedResult.content;
        this.pageSize = this.pagedResult.pageable.pageSize;
        this.totalElements = this.pagedResult.totalElements;
        this.totalPages = this.pagedResult.totalPages;
      });
  }

}
