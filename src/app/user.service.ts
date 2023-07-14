import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8080/api/v1/users"

  constructor(private httpclient: HttpClient) { }

  getUserList(): Observable<User[]>{
    return this.httpclient.get<User[]>(`${this.baseURL}`);
  }

  createUser(user: User): Observable<Object>{
    return this.httpclient.post(`${this.baseURL}`, user);
  }

  getUserById(id: number): Observable<User>{
    return this.httpclient.get<User>(`${this.baseURL}/${id}`);
  }

  updateUser(id: number, employee: User): Observable<Object>{
    return this.httpclient.put(`${this.baseURL}/${id}`, employee);
  }

  deleteUser(id: number): Observable<Object>{
    return this.httpclient.delete(`${this.baseURL}/${id}`);
  }






}
