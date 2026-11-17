import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

export interface PageResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly http = inject(HttpClient);
  private readonly base = environment.apiBase;

  list<T = any>(resource: string, size = 1000): Observable<T[]> {
    return this.listPaged<T>(resource, 0, size).pipe(map((r) => r.content));
  }

  listPaged<T = any>(resource: string, page: number, size: number): Observable<PageResponse<T>> {
    return this.http.get<PageResponse<T>>(`${this.base}/${resource}`, { params: { page, size } });
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
