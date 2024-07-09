import { Component, OnInit, Inject } from '@angular/core';
import {FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from "rxjs";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Company } from 'src/app/model/company';
import { CompanyService } from 'src/app/service/company.service';
import { Employee } from 'src/app/model/employee';

import { EmployeeService } from 'src/app/service/employee.service';
import { Invitation } from 'src/app/model/invitation';
import { AddinvitationComponent } from '../addinvitation/addinvitation.component';
import { InvitationService } from 'src/app/service/invitation.service';

@Component({
  selector: 'app-listinvitation',
  templateUrl: './listinvitation.component.html',
  styleUrls: ['./listinvitation.component.scss']
})
export class ListinvitationComponent implements OnInit {

  
  invitation : Invitation;
  constructor(public crudApi: InvitationService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,
    private matDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef:MatDialogRef<AddinvitationComponent>,) { }

  ngOnInit() {
    this.getData();
  }


  addInvitation()
  {
 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.height="90%";
    //dialogConfig.data="gdddd";
    this.matDialog.open(AddinvitationComponent, dialogConfig);
  }
  getData() {
   
    this.crudApi.getAll().subscribe(
      response =>{this.crudApi.invitationList = response;}
     );
  }

  send(){
    this.crudApi.sendData("").
    subscribe( data => {
      console.log("salem")
    });
    
  }
  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this  ?')) {
    this.crudApi.deleteData(id)
      .subscribe(
        data => {
          console.log(data);
          this.toastr.warning(' data successfully deleted!'); 
          this.getData();
        },
        error => console.log(error));
  }

 
  }
  selectData(item : Invitation) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    dialogConfig.height="90%";
    
    this.matDialog.open(AddinvitationComponent, dialogConfig);
     
     
  }

}
