import { NgModule } from '@angular/core';

import {MatTableModule} from '@angular/material/table'; 
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';


const MaterialComponents = [
  MatTableModule,MatPaginatorModule,MatSelectModule,
  MatFormFieldModule
]


@NgModule({
  imports: [MaterialComponents],
  exports: [MaterialComponents]
})
export class MaterialModule { }
