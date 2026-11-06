import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly http = inject(HttpClient);
  private readonly base = environment.apiBase;

  list<T = any>(resource: string): Observable<T[]> {
    return this.http.get<T[]>(`${this.base}/${resource}`);
  }

  get<T = any>(resource: string, id: string | number): Observable<T> {
    return this.http.get<T>(`${this.base}/${resource}/${id}`);
  }

  create<T = any>(resource: string, body: unknown): Observable<T> {
    return this.http.post<T>(`${this.base}/${resource}`, body);
  }

  update<T = any>(resource: string, id: string | number, body: unknown): Observable<T> {
    return this.http.put<T>(`${this.base}/${resource}/${id}`, body);
  }

  delete(resource: string, id: string | number): Observable<void> {
    return this.http.delete<void>(`${this.base}/${resource}/${id}`);
  }

  post<T = any>(path: string, body: unknown): Observable<T> {
    return this.http.post<T>(`${this.base}/${path}`, body);
  }
}
